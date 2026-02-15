package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentIntegrationPayment {
    @JsonProperty("payment_due_date")
    @Builder.Default
    private Integer paymentDueDate = 60;
    private String type;
    @JsonProperty("payment_method_types")
    private List<PaymentIntegrationMethodType> paymentMethodTypes;
}
