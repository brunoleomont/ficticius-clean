package com.ficticiusclean.delivery.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ficticiusclean.delivery.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleRequest implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("fabricationDate")
    private LocalDate fabricationDate;

    @JsonProperty("cityFuelConsumption")
    private BigDecimal cityFuelConsumption;

    @JsonProperty("highwayFuelConsumption")
    private BigDecimal highwayFuelConsumption;

    public Vehicle toEntity(){
        return new Vehicle(
            null, 
            this.getName(),
            this.getBrand(),
            this.getModel(),
            this.getFabricationDate(),
            this.getCityFuelConsumption(),
            this.getHighwayFuelConsumption());

    }
}
