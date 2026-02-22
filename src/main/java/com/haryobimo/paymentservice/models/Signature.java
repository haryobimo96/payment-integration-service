package com.haryobimo.paymentservice.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haryobimo.paymentservice.utils.HashUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Signature<T> {
    private static final String CHAR_COLON = ":";
    private static final String CHAR_NEW_LINE = "\n";
    private static final String KEY_CLIENT_ID = "Client-Id";
    private static final String KEY_DIGEST = "Digest";
    private static final String KEY_REQUEST_ID = "Request-Id";
    private static final String KEY_REQUEST_TARGET = "Request-Target";
    private static final String KEY_REQUEST_TIMESTAMP = "Request-Timestamp";

    private String clientId;
    private String requestId;
    private String requestTimestamp;
    private String requestTarget;
    private String secretKey;
    private T body;

    public String generateSignature() {
        StringBuilder sb = new StringBuilder();
        appendField(sb, KEY_CLIENT_ID, clientId);
        appendField(sb, KEY_REQUEST_ID, requestId);
        appendField(sb, KEY_REQUEST_TIMESTAMP, requestTimestamp);
        appendField(sb, KEY_REQUEST_TARGET, requestTarget);
        appendField(sb, KEY_DIGEST, hashBody(secretKey, body));
        sb.setLength(sb.length() - CHAR_NEW_LINE.length());
        return sb.toString();
    }

    private void appendField(StringBuilder sb, String key, String value) {
        sb.append(key).append(CHAR_COLON).append(value).append(CHAR_NEW_LINE);
    }

    private String hashBody(String key, T body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String bodyJson = objectMapper.writeValueAsString(body);
            return HashUtils.hashSha256(key, bodyJson);
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash body", e);
        }
    }
}
