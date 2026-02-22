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
     * Bean for DOKU client ID
     * Configure in application.properties as: api.doku.client-id
     */
    @Bean(name = "dokuClientId")
    public String dokuClientId(@Value("${api.doku.client-id:}") String clientId) {
        return clientId;
    }

    /**
     * Bean for DOKU client secret key (retrieved from
     * <a href="https://sandbox.doku.com/bo/developer/api-keys">DOKU Dashboard</a>).
     * Configure in application.properties as: api.doku.client-id
     */
    @Bean(name = "dokuClientSecretKey")
    public String dokuClientSecretKey(@Value("${api.doku.client-secret-key:}") String clientSecretKey) {
        return clientSecretKey;
    }

    /**
     * Helper method to create RestApiClient with a base URL
     */
    private RestApiClient createRestApiClient(
            String baseUrl, RestClient.Builder restClientBuilder) {
        return new RestApiClient(baseUrl, restClientBuilder);
    }
}
