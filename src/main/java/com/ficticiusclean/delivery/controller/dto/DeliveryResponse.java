package com.ficticiusclean.delivery.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponse implements Serializable{
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("fabricationYear")
    private Integer fabricationYear;

    @JsonProperty("fuel")
    private BigDecimal fuel;

    @JsonProperty("cost")
    private BigDecimal cost;
}
