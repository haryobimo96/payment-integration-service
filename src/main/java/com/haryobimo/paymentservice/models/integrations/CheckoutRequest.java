package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutRequest {
    private CheckoutReqOrder order;
    private CheckoutReqPayment payment;
    private CheckoutCustomer customer;
    @JsonProperty("shipping_address")
    private CheckoutAddress shippingAddress;
    @JsonProperty("billing_address")
    private CheckoutAddress billingAddress;
    @JsonProperty("additional_info")
    private CheckoutReqAdditionalInfo additionalInfo;
}

