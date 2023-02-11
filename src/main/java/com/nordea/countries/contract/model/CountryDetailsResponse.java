package com.nordea.countries.contract.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CountryDetailsResponse {

    private String name;
    private String countryCode;
    private String capital;
    private long population;
    private String flagFileUrl;
}
