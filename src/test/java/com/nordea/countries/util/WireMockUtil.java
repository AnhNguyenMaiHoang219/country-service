package com.nordea.countries.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static org.springframework.http.HttpMethod.GET;

@UtilityClass
public class WireMockUtil {

    private WireMockServer wireMockServer;

    public void start() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(options().dynamicPort());
            wireMockServer.start();

            int port = wireMockServer.port();
            WireMock.configureFor(port);
            System.setProperty("integration.rest-countries.base-url", "http://localhost:" + port);
        }
    }

    public void resetAll() {
        if (wireMockServer != null) {
            wireMockServer.resetAll();
        }
    }

    public void mockRestCountriesGetAllCountry(int status, String responseFile) {
        mockBackend("/all", GET, status, responseFile);
    }

    public void mockRestCountriesGetCountryByName(String countryName, int status, String responseFile) {
        mockBackend("/name/" + countryName, GET, status, responseFile);
    }

    private void mockBackend(String path, HttpMethod method, int responseStatus, String responseFile) {
        MappingBuilder builder = request(method.name(), urlPathEqualTo(path));
        stubFor(builder.willReturn(aResponse()
                .withHeader(CONTENT_TYPE.toString(), APPLICATION_JSON.toString())
                .withStatus(responseStatus)
                .withBodyFile(responseFile)
        ));
    }
}
