package com.haryobimo.paymentservice.models.integrations;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutResHeaders {
    private String clientId;
    private String requestId;
    private LocalDateTime date;
    private String signature;
}
