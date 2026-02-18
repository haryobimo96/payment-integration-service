package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haryobimo.paymentservice.models.PaymentMethodType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutResPayment {
    @JsonProperty("payment_due_date")
    private Integer paymentDueDate;
    @JsonProperty("payment_method_types")
    private List<PaymentMethodType> paymentMethodTypes;
    @JsonProperty("token_id")
    private String tokenId;
    private String url;
    @JsonProperty("expired_date")
    private String expiredDate;
}
