package com.haryobimo.paymentservice.models.integrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutLineItem {
    private String id;
    private String name;
    private Integer quantity;
    private Integer price;
    private String sku;
    private String category;
    private String url;
    @JsonProperty("image_url")
    private String imageUrl;
    private String type;
}

