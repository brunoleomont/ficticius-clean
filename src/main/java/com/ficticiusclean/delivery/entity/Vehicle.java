package com.ficticiusclean.delivery.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bruno L. Monteiro
 *
 */
@Data
@Entity(name = "vehicle")
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "brand", nullable = false, length = 255)
    private String brand;

    @Column(name = "model", nullable = false, length = 255)
    private String model;

    @Column(name = "fabricationDate", nullable = false)
    private LocalDate fabricationDate;

    @Column(name = "cityFuelConsumption", nullable = false)
    private BigDecimal cityFuelConsumption;

    @Column(name = "highwayFuelConsumption", nullable = false)
    private BigDecimal highwayFuelConsumption;

    public BigDecimal cityFuelConsume(BigDecimal cityMiles){
        if (cityMiles == null || cityFuelConsumption == null) {
            return new BigDecimal(0);
        }
        if (cityMiles.compareTo(new BigDecimal(0)) <= 0 ||
            cityFuelConsumption.compareTo(new BigDecimal(0)) <= 0) {
            return new BigDecimal(0);
        }
        return cityMiles.divide(cityFuelConsumption, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal highwayFuelConsume(BigDecimal highwayMiles){
        if (highwayMiles == null || cityFuelConsumption == null) {
            return new BigDecimal(0);
        }
        if (highwayMiles.compareTo(new BigDecimal(0)) <= 0 ||
            highwayFuelConsumption.compareTo(new BigDecimal(0)) <= 0) {
            return new BigDecimal(0);
        }
        return highwayMiles.divide(highwayFuelConsumption, 2, RoundingMode.HALF_UP);
    }
}
