package com.haryobimo.paymentservice.models.integrations;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
@Builder
public class CheckoutReqHeaders {
    private String clientId;
    private String requestId;
    @Builder.Default
    private LocalDateTime requestTimestamp = LocalDateTime.now();
    private String signature;

    public HttpHeaders toHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Client-Id", clientId);
        headers.add("Request-Id", requestId);
        headers.add("Request-Timestamp", requestTimestamp.toString());
        headers.add("Signature", signature);
        return headers;
    }
}
