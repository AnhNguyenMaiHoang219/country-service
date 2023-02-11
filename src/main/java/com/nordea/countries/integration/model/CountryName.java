package com.nordea.countries.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryName {

    @JsonProperty("common")
    private String common;
}
