package com.nordea.countries.resource;

import com.nordea.countries.util.JsonUtil;
import com.nordea.countries.util.WireMockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class GetAllCountriesIntegrationTest extends RootIntegrationTest {

    private final String GET_ALL_COUNTRIES_PATH = "/countries";

    @Test
    void givenValidRequest_whenReceive200ResponseFromRestCountries_thenReturnCountryListResponse() {
        WireMockUtil.mockRestCountriesGetAllCountry(200, "/json/integration/restcountries/get-all-countries_200-response.json");

        String actualResponse = given()
                .when()
                .get(GET_ALL_COUNTRIES_PATH)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .asString();

        Object expectedResponseObject = JsonUtil.readJsonFile("/__files/json/contract/get-all-countries-response.json", Object.class);
        Object actualResponseObject = JsonUtil.readJsonString(actualResponse, Object.class);

        assertThat(actualResponseObject)
                .usingRecursiveComparison()
                .isEqualTo(expectedResponseObject);
    }

    @ParameterizedTest(name = "" +
            "Given valid request, " +
            "When receive {0} response code from restcountries.com service, " +
            "Then return service unavailable"
    )
    @ValueSource(ints = {500, 501, 503})
    void givenValidRequest_whenReceive5xxResponseFromRestCountries_thenReturnServiceUnavailableError(int responseCode) {
        WireMockUtil.mockRestCountriesGetAllCountry(responseCode, null);

        int expectedStatusCode = 503;

        given()
                .when()
                .get(GET_ALL_COUNTRIES_PATH)
                .then()
                .statusCode(expectedStatusCode)
                .log().all()
                .assertThat()
                .body("status", is(expectedStatusCode))
                .body("message", startsWith(String.format("Country integration client error with Http response status = '%s'", responseCode)));
    }
}
