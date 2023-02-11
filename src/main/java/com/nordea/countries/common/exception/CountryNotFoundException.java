package com.nordea.countries.common.exception;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String countryName) {
        super(String.format("Country not found with name '%s'", countryName));
    }
}

