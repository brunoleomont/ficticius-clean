package com.ficticiusclean.delivery.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ficticiusclean.delivery.controller.dto.DeliveryRequest;
import com.ficticiusclean.delivery.controller.dto.DeliveryResponse;
import com.ficticiusclean.delivery.service.DeliveryService;


@RestController
public class DeliveryController {

    Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    private DeliveryService deliveryService;

    public DeliveryController(
        DeliveryService deliveryService){
            this.deliveryService = deliveryService;
        };

    @GetMapping("/cost-forecast/{price}/{cityMiles}/{highwayMiles}")
    private ResponseEntity<List<DeliveryResponse>> costForecast(@PathVariable BigDecimal price, 
                @PathVariable BigDecimal cityMiles, @PathVariable BigDecimal highwayMiles) throws IOException{
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getForecastCost(new DeliveryRequest(price, cityMiles, highwayMiles)));
    }    
}
