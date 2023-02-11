package com.nordea.countries.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Value("${integration.rest-countries.base-url}")
    private String restCountriesBaseUrl;

    @Bean
    @Qualifier("rest-countries")
    public WebClient restCountriesWebClient() {
        final int maxMemorySize = 16 * 1024 * 1024; // 16 MB
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(maxMemorySize))
                .build();

        return WebClient.builder()
                .clone()
                .baseUrl(restCountriesBaseUrl)
                .exchangeStrategies(strategies)
                .build();
    }
}
