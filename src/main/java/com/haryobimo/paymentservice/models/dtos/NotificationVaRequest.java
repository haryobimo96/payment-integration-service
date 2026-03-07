package com.haryobimo.paymentservice.models.dtos;

import com.haryobimo.paymentservice.models.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVaRequest {
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String virtualAccountName;
    private String trxId;
    private String paymentRequestId;
    private String virtualAccountEmail;
    private String virtualAccountPhone;
    private PaidAmount paidAmount;

    @Data
    @Builder
    public static class PaidAmount {
        private String value;
        private Currency currency;
    }
}
