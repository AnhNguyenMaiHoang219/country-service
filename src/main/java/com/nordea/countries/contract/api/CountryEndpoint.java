package com.nordea.countries.contract.api;

import com.nordea.countries.contract.model.CountriesResponse;
import com.nordea.countries.contract.model.CountryDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface CountryEndpoint {

    @GetMapping("/countries")
    Mono<CountriesResponse> getAllCountries();

    @GetMapping("/countries/{name}")
    Mono<CountryDetailsResponse> getCountryByName(@PathVariable("name") String countryName);
}
