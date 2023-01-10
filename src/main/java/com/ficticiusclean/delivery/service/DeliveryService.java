package com.ficticiusclean.delivery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ficticiusclean.delivery.controller.dto.DeliveryRequest;
import com.ficticiusclean.delivery.controller.dto.DeliveryResponse;
import com.ficticiusclean.delivery.entity.Vehicle;
import com.ficticiusclean.delivery.repository.VehicleRepository;

@Service
public class DeliveryService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<DeliveryResponse> getForecastCost(DeliveryRequest deliveryRequest) {
        List<DeliveryResponse> deliveryResponses = new ArrayList<DeliveryResponse>();

        List<Vehicle> vehicles = vehicleRepository.findAll();
        vehicles.stream().forEach(vehicle -> {
            deliveryResponses.add(createDeliveryResponse(vehicle, deliveryRequest));
        });
        Collections.sort(deliveryResponses, Comparator.comparing(DeliveryResponse::getCost));
        return deliveryResponses;
    }

    private DeliveryResponse createDeliveryResponse(Vehicle vehicle, DeliveryRequest deliveryRequest) {
        DeliveryResponse deliveryResponse = new DeliveryResponse();

        deliveryResponse.setName(vehicle.getName());
        deliveryResponse.setBrand(vehicle.getBrand());
        deliveryResponse.setModel(vehicle.getModel());
        deliveryResponse.setFabricationYear(vehicle.getFabricationDate().getYear());
        deliveryResponse.setFuel(calcFuel(vehicle, deliveryRequest));
        deliveryResponse.setCost(calcCost(vehicle, deliveryRequest));

        return deliveryResponse;
    }

    private BigDecimal calcCost(Vehicle vehicle, DeliveryRequest deliveryRequest) {
        return vehicle.cityFuelConsume(deliveryRequest.getCityMiles()).multiply(deliveryRequest.getPrice())
                                .add(vehicle.highwayFuelConsume(deliveryRequest.getHighwayMiles()).multiply(deliveryRequest.getPrice()));
    }

    private BigDecimal calcFuel(Vehicle vehicle, DeliveryRequest deliveryRequest) {
        return vehicle.cityFuelConsume(deliveryRequest.getCityMiles())
                                .add(vehicle.highwayFuelConsume(deliveryRequest.getHighwayMiles()));
    }
    
}
