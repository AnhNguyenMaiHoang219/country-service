package com.nordea.countries.resource;

import com.nordea.countries.util.WireMockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class GetCountryByNameIntegrationTest extends RootIntegrationTest {

    private final String GET_COUNTRY_BY_NAME_PATH = "/countries/%s";

    @Test
    void givenValidCountryName_whenReceive200ResponseFromRestCountries_thenReturnCountryDetailsResponse() {
        String countryName = "finland";
        WireMockUtil.mockRestCountriesGetCountryByName(countryName, 200, "/json/integration/restcountries/get-country-by-name_200-response.json");

        given()
                .when()
                .get(String.format(GET_COUNTRY_BY_NAME_PATH, countryName))
                .then()
                .statusCode(200)
                .log().all()
                .assertThat()
                .body("name", is("Finland"))
                .body("country_code", is("FI"))
                .body("capital", is("Helsinki"))
                .body("population", is(5530719))
                .body("flag_file_url", is("https://flagcdn.com/w320/fi.png"));
    }

    @Test
    void givenInvalidCountryName_whenReceive404ResponseFromRestCountries_thenReturnCountryNotFoundError() {
        String countryName = "invalid-country-name";
        WireMockUtil.mockRestCountriesGetCountryByName(countryName, 404, "/json/integration/restcountries/get-country-by-name_404-response.json");

        int expectedStatusCode = 404;

        given()
                .when()
                .get(String.format(GET_COUNTRY_BY_NAME_PATH, countryName))
                .then()
                .statusCode(expectedStatusCode)
                .log().all()
                .assertThat()
                .body("status", is(expectedStatusCode))
                .body("message", is(String.format("Country not found with name '%s'", countryName)));
    }

    @ParameterizedTest(name = "" +
            "Given a country name, " +
            "When receive {0} response code from mock restcountries.com service, " +
            "Then return service unavailable"
    )
    @ValueSource(ints = {500, 501, 503})
    void givenCountryName_whenReceive5xxResponseFromRestCountries_thenReturnServiceUnavailableError(int responseCode) {
        String countryName = "finland";
        WireMockUtil.mockRestCountriesGetCountryByName(countryName, responseCode, null);

        int expectedStatusCode = 503;

        given()
                .when()
                .get(String.format(GET_COUNTRY_BY_NAME_PATH, countryName))
                .then()
                .statusCode(expectedStatusCode)
                .log().all()
                .assertThat()
                .body("status", is(expectedStatusCode))
                .body("message", startsWith(String.format("Country integration client error with Http response status = '%s'", responseCode)));
    }
}
