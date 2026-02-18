package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutResponse {
    private List<String> message;
    private ResponseData response;

    @Data
    @Builder
    public static class ResponseData {
        private CheckoutResOrder order;
        private CheckoutResPayment payment;
        private CheckoutCustomer customer;
        private BigInteger uuid;
        private CheckoutResHeaders headers;
        @JsonProperty("shipping_address")
        private CheckoutAddress shippingAddress;
        @JsonProperty("billing_address")
        private CheckoutAddress billingAddress;
        @JsonProperty("additional_info")
        private CheckoutResAdditionalInfo additionalInfo;
    }
}
