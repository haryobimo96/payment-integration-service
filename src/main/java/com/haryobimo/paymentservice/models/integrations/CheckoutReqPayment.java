package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haryobimo.paymentservice.models.PaymentMethodType;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutReqPayment {
    @JsonProperty("payment_due_date")
    @Builder.Default
    private Integer paymentDueDate = 60;
    private String type;
    @JsonProperty("payment_method_types")
    private List<PaymentMethodType> paymentMethodTypes;
}


