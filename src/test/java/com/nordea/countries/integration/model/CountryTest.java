package com.nordea.countries.integration.model;

import com.nordea.countries.contract.model.Country;
import com.nordea.countries.contract.model.CountryDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    private com.nordea.countries.integration.model.Country country;

    @BeforeEach
    public void setup() {
        this.country = com.nordea.countries.integration.model.Country
                .builder()
                .name(CountryName.builder()
                        .common("country-name")
                        .build()
                )
                .countryCode("country-code")
                .capitals(List.of("capital-1", "capital-2"))
                .population(2000)
                .flags(CountryFlags.builder()
                        .png("png-flag-link")
                        .svg("svg-flag-link")
                        .alt("alt text")
                        .build()
                )
                .build();
    }

    @Test
    void givenIntegrationCountry_testConvertToContractCountry() {
        Country contractCountry = this.country.convertToContractCountry();

        assertEquals(contractCountry.getName(), "country-name");
        assertEquals(contractCountry.getCountryCode(), "country-code");
    }

    @Test
    void givenIntegrationCountry_testConvertToCountryDetailsResponse() {
        CountryDetailsResponse response = this.country.convertToDetailsResponse();

        assertEquals(response.getName(), "country-name");
        assertEquals(response.getCountryCode(), "country-code");
        assertEquals(response.getCapital(), "capital-1");
        assertEquals(response.getPopulation(), 2000);
        assertEquals(response.getFlagFileUrl(), "png-flag-link");
    }
}