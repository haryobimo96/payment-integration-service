package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PaymentIntegrationOrder {
    private int amount;
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    @Builder.Default
    private String currency = "IDR";
    @JsonProperty("callback_url")
    private String callbackUrl;
    @JsonProperty("callback_url_cancel")
    private String callbackUrlCancel;
    @JsonProperty("callback_url_result")
    private String callbackUrlResult;
    private String language;
    @JsonProperty("auto_redirect")
    private boolean autoRedirect;
    @JsonProperty("disable_retry_payment")
    private Boolean disableRetryPayment;
    @JsonProperty("recover_abandoned_cart")
    private Boolean recoverAbandonedCart;
    @JsonProperty("expired_recovered_cart")
    private Integer expiredRecoveredCart;
    @JsonProperty("line_items")
    private List<PaymentIntegrationLineItem> lineItems;
}

