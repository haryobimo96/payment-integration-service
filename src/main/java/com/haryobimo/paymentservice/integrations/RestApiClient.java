package com.haryobimo.paymentservice.integrations;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Generic API Client for making HTTP requests with support for custom headers,
 * query parameters, and generic request/response bodies.
 */
public class RestApiClient {

    private final RestClient restClient;
    private final String baseUrl;

    /**
     * Constructor that accepts a base URL and RestClient.Builder
     *
     * @param baseUrl            The base URL for API requests (e.g., https://api.example.com)
     * @param restClientBuilder  The RestClient.Builder to create the RestClient instance
     */
    public RestApiClient(String baseUrl, RestClient.Builder restClientBuilder) {
        this.baseUrl = baseUrl;
        this.restClient = restClientBuilder.build();
    }

    /**
     * Performs a POST request to the specified URL path with a request body.
     *
     * @param path          The URL path (appended to baseUrl, e.g., /users or /api/v1/items)
     * @param body          The request body of generic type T
     * @param responseType  The class type of the response body
     * @param headers       Optional map of custom headers (can be null or empty)
     * @param queryParams   Optional MultiValueMap of query parameters for multi-value support (can be null or empty)
     * @param <T>           The type of the request body
     * @param <R>           The type of the response body
     * @return The response body of type R
     */
    public <T, R> R post(String path, T body, Class<R> responseType,
             Map<String, String> headers, MultiValueMap<String, String> queryParams) {
        String fullUrl = buildFullUrl(path);
        String finalUrl = buildUrlWithQueryParams(fullUrl, queryParams);

        return restClient.post()
                .uri(finalUrl)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    applyCustomHeaders(httpHeaders, headers);
                })
                .body(body)
                .retrieve()
                .body(responseType);
    }

    /**
     * Performs a POST request to the specified URL path with a request body.
     *
     * @param path         The URL path (appended to baseUrl, e.g., /users or /api/v1/items)
     * @param body         The request body of generic type T
     * @param responseType The class type of the response body
     * @param <T>          The type of the request body
     * @param <R>          The type of the response body
     * @return The response body of type R
     */
    public <T, R> R post(String path, T body, Class<R> responseType) {
        return post(path, body, responseType, null, null);
    }

    /**
     * Builds the full URL by combining baseUrl and the provided path.
     *
     * @param path The URL path (e.g., /users or /api/v1/items), expects to start with a slash
     * @return The complete URL combining baseUrl and path
     */
    private String buildFullUrl(String path) {
        if (StringUtils.isBlank(baseUrl)) {
            return path;
        }
        return baseUrl + path;
    }

    /**
     * Builds the final URL with query parameters appended.
     * MultiValueMap naturally supports multi-value parameters:
     * - Single value: params.add("key", "value")
     * - Multiple values: params.add("key", "value1"); params.add("key", "value2")
     * - Or use: params.put("key", List.of("value1", "value2"))
     *
     * @param baseUrl     The base URL
     * @param queryParams The MultiValueMap of query parameters (can be null or empty)
     * @return The complete URL with query parameters
     */
    private String buildUrlWithQueryParams(String baseUrl, MultiValueMap<String, String> queryParams) {
        if (CollectionUtils.isEmpty(queryParams)) {
            return baseUrl;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
        queryParams.forEach((key, values) -> {
            if (!CollectionUtils.isEmpty(values)) {
                values.forEach(value -> builder.queryParam(key, value));
            }
        });
        return builder.build().toUriString();
    }

    /**
     * Applies custom headers to the HTTP request.
     *
     * @param httpHeaders The HttpHeaders object to modify
     * @param headers     The map of custom headers (can be null or empty)
     */
    private void applyCustomHeaders(HttpHeaders httpHeaders, Map<String, String> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(httpHeaders::set);
        }
    }
}

