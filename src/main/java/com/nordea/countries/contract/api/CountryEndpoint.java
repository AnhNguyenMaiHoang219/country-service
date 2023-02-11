package com.nordea.countries.contract.api;

import com.nordea.countries.contract.error.ErrorResponse;
import com.nordea.countries.contract.model.CountriesResponse;
import com.nordea.countries.contract.model.CountryDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(
        name = "Country service",
        description = "The country service for getting country list and country details"
)
@RestController
public interface CountryEndpoint {

    @GetMapping(value = "/countries", produces = "application/json")
    @Operation(
            summary = "Get all countries",
            description = "The endpoint provides the list of country names and codes"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of countries is retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CountriesResponse.class))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Country integration client error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    Mono<CountriesResponse> getAllCountries();

    @GetMapping(value = "/countries/{name}", produces = "application/json")
    @Operation(
            summary = "Get country details by country name",
            description = "The endpoint provides the details of the requested country name"
    )
    @Parameters(
            @Parameter(
                    name = "name",
                    required = true,
                    in = ParameterIn.PATH,
                    description = "Country name",
                    schema = @Schema(implementation = String.class)
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Country details is retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CountryDetailsResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Country not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Country integration client error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    Mono<CountryDetailsResponse> getCountryByName(@PathVariable("name") String countryName);
}
