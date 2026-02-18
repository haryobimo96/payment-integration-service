package com.haryobimo.paymentservice.integrations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuration class for creating RestApiClient beans with different base URLs.
 * Example usage in application.properties:
 * api.doku.base-url=https://api.doku.com
 * Then inject each bean with @Qualifier
 */
@Configuration
public class RestApiClientConfig {

    /**
     * Example bean for DOKU API client
     * Configure the base URL in application.properties as: api.doku.base-url=<URL>
     */
    @Bean(name = "dokuApiClient")
    public RestApiClient dokuApiClient(
            @Value("${api.doku.base-url:}") String baseUrl,
            RestClient.Builder restClientBuilder) {
        return createRestApiClient(baseUrl, restClientBuilder);
    }

    /**
     * Helper method to create RestApiClient with a base URL
     */
    private RestApiClient createRestApiClient(
            String baseUrl, RestClient.Builder restClientBuilder) {
        return new RestApiClient(baseUrl, restClientBuilder);
    }
}

