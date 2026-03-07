package com.haryobimo.paymentservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVaResponse {
    private String responseCode;
    private String responseBody;
    private VirtualAccountData virtualAccountData;

    @Data
    @Builder
    public static class VirtualAccountData {
        private String paymentServiceId;
        private String customerNo;
        private String virtualAccountNo;
        private String virtualAccountName;
        private String trxId;
        private String paymentRequestId;
    }
}
