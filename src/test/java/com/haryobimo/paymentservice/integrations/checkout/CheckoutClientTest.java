package com.haryobimo.paymentservice.integrations.checkout;

import static com.haryobimo.paymentservice.integrations.checkout.CheckoutApiPath.CHECKOUT_PAYMENT_PATH;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.haryobimo.paymentservice.models.integrations.CheckoutRequest;
import com.haryobimo.paymentservice.models.integrations.CheckoutResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
public class CheckoutClientTest {
    private static final String HEADER_CLIENT_ID = "Client-Id";
    private static final String HEADER_REQUEST_ID = "Request-Id";
    private static final String HEADER_REQUEST_TIMESTAMP = "Request-Timestamp";
    private static final String HEADER_SIGNATURE = "Signature";
    private static final String KEY_DOKU_CLIENT_ID = "dokuClientId";
    private static final String KEY_DOKU_CLIENT_SECRET_KEY = "dokuClientSecretKey";
    private static final String TEST_DOKU_CLIENT_ID = "test-client-id";
    private static final String TEST_DOKU_CLIENT_SECRET_KEY = "test-client-secret-key";
    private static final CheckoutRequest TEST_CHECKOUT_REQUEST
            = CheckoutRequest.builder().build();
    private static final CheckoutResponse TEST_CHECKOUT_RESPONSE
            = CheckoutResponse.builder().build();

    @InjectMocks
    private CheckoutClient checkoutClient;
    @Mock
    private RestClient dokuApiClient;
    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private RestClient.RequestBodySpec requestBodySpec;
    @Mock
    private RestClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                checkoutClient, KEY_DOKU_CLIENT_ID, TEST_DOKU_CLIENT_ID);
        ReflectionTestUtils.setField(
                checkoutClient, KEY_DOKU_CLIENT_SECRET_KEY, TEST_DOKU_CLIENT_SECRET_KEY);
    }

    @Test
    void checkoutTest() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Consumer<HttpHeaders>> headersCaptor = ArgumentCaptor.forClass(Consumer.class);

        when(dokuApiClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(uriCaptor.capture())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(headersCaptor.capture())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(CheckoutRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(CheckoutResponse.class)).thenReturn(TEST_CHECKOUT_RESPONSE);

        CheckoutResponse checkoutResponse = checkoutClient.checkout(TEST_CHECKOUT_REQUEST);
        assertNotNull(checkoutResponse);
        assertEquals(CHECKOUT_PAYMENT_PATH, uriCaptor.getValue());

        HttpHeaders headers = new HttpHeaders();
        Consumer<HttpHeaders> httpHeadersConsumer = headersCaptor.getValue();
        httpHeadersConsumer.accept(headers);
        assertEquals(TEST_DOKU_CLIENT_ID, headers.getFirst(HEADER_CLIENT_ID));

        String headerRequestId = headers.getFirst(HEADER_REQUEST_ID);
        assertNotNull(headerRequestId);
        assertDoesNotThrow(() -> UUID.fromString(headerRequestId));

        String headerRequestTimestamp = headers.getFirst(HEADER_REQUEST_TIMESTAMP);
        assertNotNull(headerRequestTimestamp);
        assertDoesNotThrow(() -> LocalDateTime.parse(headerRequestTimestamp));

        String headerSignature = headers.getFirst(HEADER_SIGNATURE);
        assertNotNull(headerSignature);
    }
}
