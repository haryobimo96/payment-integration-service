package com.haryobimo.paymentservice.controllers;

import static com.haryobimo.paymentservice.constants.ApiPath.NOTIFICATION_PATH;
import static com.haryobimo.paymentservice.constants.ApiPath.NOTIFICATION_VA_SUBPATH;
import static com.haryobimo.paymentservice.constants.HeaderConstants.CHANNEL_ID;
import static com.haryobimo.paymentservice.constants.HeaderConstants.X_EXTERNAL_ID;
import static com.haryobimo.paymentservice.constants.HeaderConstants.X_PARTNER_ID;
import static com.haryobimo.paymentservice.constants.HeaderConstants.X_SIGNATURE;
import static com.haryobimo.paymentservice.constants.HeaderConstants.X_TIMESTAMP;

import com.haryobimo.paymentservice.models.dtos.NotificationVaRequest;
import com.haryobimo.paymentservice.models.dtos.NotificationVaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(NOTIFICATION_PATH)
@RequiredArgsConstructor
public class NotificationController {
    @PostMapping(NOTIFICATION_VA_SUBPATH)
    public ResponseEntity<NotificationVaResponse> handleVaNotification(
            @RequestHeader(CHANNEL_ID) String channelId,
            @RequestHeader(X_EXTERNAL_ID) String externalId,
            @RequestHeader(X_PARTNER_ID) String partnerId,
            @RequestHeader(X_SIGNATURE) String signature,
            @RequestHeader(X_TIMESTAMP) String timestamp,
            @RequestBody NotificationVaRequest request) {
        return ResponseEntity.ok(new NotificationVaResponse());
    }
}
