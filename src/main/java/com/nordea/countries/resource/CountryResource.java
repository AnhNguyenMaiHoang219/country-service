package com.nordea.countries.resource;

import com.nordea.countries.contract.api.CountryEndpoint;
import com.nordea.countries.contract.model.CountriesResponse;
import com.nordea.countries.contract.model.CountryDetailsResponse;
import com.nordea.countries.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@AllArgsConstructor
public class CountryResource implements CountryEndpoint {

    private final CountryService countryService;

    @Override
    public Mono<CountriesResponse> getAllCountries() {
        return countryService.getAllCountries();
    }

    @Override
    public Mono<CountryDetailsResponse> getCountryByName(String countryName) {
        return countryService.getCountryByName(countryName);
    }
}
