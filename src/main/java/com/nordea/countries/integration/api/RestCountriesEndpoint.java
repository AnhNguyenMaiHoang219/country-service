package com.nordea.countries.integration.api;

import com.nordea.countries.integration.model.Country;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RestCountriesEndpoint {

    String ALL_PATH = "/all";
    String NAME_PATH = "/name/{name}";

    Mono<List<Country>> getAllCountries();

    Mono<Country> getCountryByName(String countryName);
}
