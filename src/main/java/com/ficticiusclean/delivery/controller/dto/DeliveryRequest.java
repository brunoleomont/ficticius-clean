package com.ficticiusclean.delivery.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryRequest implements Serializable{
    
    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("cityMiles")
    private BigDecimal cityMiles;

    @JsonProperty("highwayMiles")
    private BigDecimal highwayMiles;
}
