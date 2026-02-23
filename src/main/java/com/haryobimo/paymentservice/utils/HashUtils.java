package com.haryobimo.paymentservice.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtils {

    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * Hashes a string using Base64 HMAC-SHA256 with the given key.
     *
     * @param key the hashing key
     * @param data the string to be hashed
     * @return the hashed string in hexadecimal format
     * @throws RuntimeException if the hashing algorithm is not available or the key is invalid
     */
    public static String hashSha256(String key, String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    0,
                    key.getBytes(StandardCharsets.UTF_8).length,
                    HMAC_SHA256
            );
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] hashedBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to hash string using SHA-256", e);
        }
    }
}
