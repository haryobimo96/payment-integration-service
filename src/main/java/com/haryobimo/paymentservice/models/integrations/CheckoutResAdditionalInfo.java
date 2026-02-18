package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutResAdditionalInfo {
    @JsonProperty("allow_tenor")
    private List<Integer> allowTenor;
    @JsonProperty("doku_wallet_notify_url")
    private String dokuWalletNotifyUrl;
    @JsonProperty("override_notification_url")
    private String overrideNotificationUrl;
    private CheckoutResOrigin origin;
}
