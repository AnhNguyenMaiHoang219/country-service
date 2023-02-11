package com.nordea.countries.service;

import com.nordea.countries.contract.model.CountriesResponse;
import com.nordea.countries.contract.model.CountryDetailsResponse;
import com.nordea.countries.integration.CountryClient;
import com.nordea.countries.integration.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@AllArgsConstructor
@Service
public class CountryService {

    private final CountryClient countryClient;

    public Mono<CountriesResponse> getAllCountries() {
        return countryClient.getAllCountries()
                .flatMapIterable(countries -> countries)
                .map(Country::convertToContractCountry)
                .collectSortedList(Comparator.comparing(com.nordea.countries.contract.model.Country::getName))
                .map(countries -> CountriesResponse.builder()
                        .countries(countries)
                        .build()
                );
    }

    public Mono<CountryDetailsResponse> getCountryByName(String countryName) {
        return countryClient.getCountryByName(countryName)
                .map(Country::convertToDetailsResponse);
    }
}
