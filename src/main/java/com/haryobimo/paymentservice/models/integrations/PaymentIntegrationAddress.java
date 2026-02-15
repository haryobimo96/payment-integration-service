package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentIntegrationAddress {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    private String city;
    @JsonProperty("postal_code")
    private String postalCode;
    private String phone;
    @JsonProperty("country_code")
    private String countryCode;
}

