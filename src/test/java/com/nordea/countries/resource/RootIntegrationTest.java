package com.nordea.countries.resource;

import com.nordea.countries.CountryApplication;
import com.nordea.countries.util.WireMockUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
        classes = {CountryApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class RootIntegrationTest {

    @LocalServerPort
    private int port;

    public void setup() {
        RestAssured.port = port;
    }

    @BeforeAll
    static void beforeAll() {
        WireMockUtil.start();
    }

    @BeforeEach
    public void beforeEach() {
        setup();
        WireMockUtil.resetAll();
    }
}
