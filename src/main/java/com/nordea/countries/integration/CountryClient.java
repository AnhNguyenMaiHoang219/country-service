package com.nordea.countries.integration;

import com.nordea.countries.integration.api.RestCountriesEndpoint;
import com.nordea.countries.common.exception.CountryIntegrationException;
import com.nordea.countries.integration.model.Country;
import com.nordea.countries.common.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CountryClient implements RestCountriesEndpoint {

    private final WebClient restCountriesWebClient;

    public CountryClient(
            @Qualifier("rest-countries") WebClient restCountriesWebClient
    ) {
        this.restCountriesWebClient = restCountriesWebClient;
    }

    @Override
    public Mono<List<Country>> getAllCountries() {
        return restCountriesWebClient
                .get()
                .uri(ALL_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Country>>() {
                })
                .onErrorMap(throwable -> mapError(throwable, null));
    }

    @Override
    public Mono<Country> getCountryByName(String countryName) {
        return restCountriesWebClient
                .get()
                .uri(NAME_PATH, countryName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Country>>() {
                })
                .map(countries -> countries.get(0))
                .onErrorMap(throwable -> mapError(throwable, countryName));
    }

    private Exception mapError(Throwable throwable, @Nullable String countryName) {
        if (throwable instanceof WebClientResponseException error) {
            if (error.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new CountryNotFoundException(countryName);
            }

            return new CountryIntegrationException(
                    String.format(
                            "Country integration client error with Http response status = '%s', response body = '%s'",
                            error.getStatusCode().value(),
                            error.getResponseBodyAsString()
                    )
            );
        }

        return new CountryIntegrationException(String.format("Country integration client error: %s", throwable.getMessage()));
    }
}
