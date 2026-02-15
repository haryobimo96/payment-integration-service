package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentIntegrationCustomer {
    private String id;
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String postcode;
    private String state;
    private String city;
    private String country;
}

