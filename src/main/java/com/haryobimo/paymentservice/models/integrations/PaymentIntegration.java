package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentIntegration {
    private PaymentIntegrationOrder order;
    private PaymentIntegrationPayment payment;
    private PaymentIntegrationCustomer customer;
    @JsonProperty("shipping_address")
    private PaymentIntegrationAddress shippingAddress;
    @JsonProperty("billing_address")
    private PaymentIntegrationAddress billingAddress;
    @JsonProperty("additional_info")
    private PaymentIntegrationAdditionalInfo additionalInfo;
}
