package com.nordea.countries.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryFlags {

    @JsonProperty("png")
    private String png;

    @JsonProperty("svg")
    private String svg;

    @JsonProperty("alt")
    private String alt;
}
