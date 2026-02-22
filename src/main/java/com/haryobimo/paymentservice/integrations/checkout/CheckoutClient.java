package com.haryobimo.paymentservice.integrations.checkout;

import static com.haryobimo.paymentservice.integrations.checkout.CheckoutApiPath.CHECKOUT_PAYMENT_PATH;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import com.haryobimo.paymentservice.models.Signature;
import com.haryobimo.paymentservice.models.integrations.CheckoutReqHeaders;
import com.haryobimo.paymentservice.models.integrations.CheckoutRequest;
import com.haryobimo.paymentservice.models.integrations.CheckoutResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CheckoutClient {
    private final String dokuClientId;
    private final String dokuClientSecretKey;
    private final RestClient dokuApiClient;

    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        HttpHeaders headers = getHeaders(CHECKOUT_PAYMENT_PATH, checkoutRequest);
        return dokuApiClient.post()
                .uri(CHECKOUT_PAYMENT_PATH)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(checkoutRequest)
                .retrieve()
                .toEntity(CheckoutResponse.class)
                .getBody();
    }

    private <T> HttpHeaders getHeaders(String path, T body) {
        String requestId = UUID.randomUUID().toString();
        LocalDateTime requestTimestamp = LocalDateTime.now();

        Signature<T> signature = Signature.<T>builder()
                .clientId(dokuClientId)
                .requestId(requestId)
                .requestTimestamp(requestTimestamp.format(ISO_DATE_TIME))
                .requestTarget(path)
                .secretKey(dokuClientSecretKey)
                .body(body)
                .build();

        CheckoutReqHeaders headers = CheckoutReqHeaders.builder()
                .clientId(dokuClientId)
                .requestId(requestId)
                .requestTimestamp(requestTimestamp)
                .signature(signature.generateSignature())
                .build();

        return headers.toHttpHeaders();
    }
}
