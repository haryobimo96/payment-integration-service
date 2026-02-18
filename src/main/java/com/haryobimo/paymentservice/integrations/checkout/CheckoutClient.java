package com.haryobimo.paymentservice.integrations.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CheckoutClient {
    private final RestClient dokuApiClient;
}
