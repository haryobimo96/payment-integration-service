package com.haryobimo.paymentservice.models.integrations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutResOrigin {
    private String product;
    private String system;
    private String source;
    private String apiFormat;
}
