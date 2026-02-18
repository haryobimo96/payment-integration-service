package com.haryobimo.paymentservice.models.integrations;

import static com.haryobimo.paymentservice.models.Currency.IDR;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haryobimo.paymentservice.models.Currency;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutReqOrder {
    private int amount;
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    @Builder.Default
    private Currency currency = IDR;
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
    private List<CheckoutLineItem> lineItems;
}


