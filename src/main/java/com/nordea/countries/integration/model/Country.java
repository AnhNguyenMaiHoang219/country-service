package com.nordea.countries.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nordea.countries.contract.model.CountryDetailsResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @JsonProperty("name")
    private CountryName name;

    @JsonProperty("cca2")
    private String countryCode;

    @JsonProperty("capital")
    private List<String> capitals;

    @JsonProperty("population")
    private long population;

    @JsonProperty("flags")
    private CountryFlags flags;

    public com.nordea.countries.contract.model.Country convertToContractCountry() {
        return com.nordea.countries.contract.model.Country
                .builder()
                .name(this.getName().getCommon())
                .countryCode(this.getCountryCode())
                .build();
    }

    public CountryDetailsResponse convertToDetailsResponse() {
        String capital = null;

        List<String> capitals = this.getCapitals();
        if (capitals != null && !capitals.isEmpty()) {
            capital = capitals.get(0);
        }

        return CountryDetailsResponse
                .builder()
                .name(this.getName().getCommon())
                .countryCode(this.getCountryCode())
                .capital(capital)
                .population(this.getPopulation())
                .flagFileUrl(this.getFlags().getPng())
                .build();
    }
}
