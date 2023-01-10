package com.ficticiusclean.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ficticiusclean.delivery.controller.dto.DeliveryRequest;
import com.ficticiusclean.delivery.controller.dto.DeliveryResponse;
import com.ficticiusclean.delivery.entity.Vehicle;
import com.ficticiusclean.delivery.repository.VehicleRepository;
import com.ficticiusclean.delivery.service.DeliveryService;
import com.ficticiusclean.delivery.utils.Utils;

@SpringBootTest
class DeliveryApplicationTests {

	@Autowired
	private DeliveryService deliveryService;

	@MockBean
	private VehicleRepository vehicleRepository;

	List<DeliveryResponse> deliveryResponses;

	@Test
	void deliveryServiceResponseSizeTest() {
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(10.0), new BigDecimal(10.0), new BigDecimal(10.0));
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(Utils.createVehicle());
		vehicles.add(Utils.createVehicle());
		vehicles.add(Utils.createVehicle());

		when(vehicleRepository.findAll()).thenReturn(vehicles);
		List<DeliveryResponse> deliveryResponse = deliveryService.getForecastCost(deliveryRequest);
		assertEquals(3, deliveryResponse.size());
	}

	@Test
	void deliveryServiceResponseCostTest() {
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(100.0), new BigDecimal(100.0), new BigDecimal(100.0));
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicleA = Utils.createVehicle();
		vehicles.add(vehicleA);

		BigDecimal cost = vehicleA.cityFuelConsume(deliveryRequest.getCityMiles()).multiply(deliveryRequest.getPrice())
					.add(vehicleA.highwayFuelConsume(deliveryRequest.getHighwayMiles()).multiply(deliveryRequest.getPrice()));

		when(vehicleRepository.findAll()).thenReturn(vehicles);
		List<DeliveryResponse> deliveryResponse = deliveryService.getForecastCost(deliveryRequest);
		assertEquals(cost, deliveryResponse.get(0).getCost());
	}

	@Test
	void deliveryServiceResponseConsumeFuelTest() {
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(100.0), new BigDecimal(100.0), new BigDecimal(100.0));
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicleA = Utils.createVehicle();
		vehicles.add(vehicleA);

		BigDecimal Consume = vehicleA.cityFuelConsume(deliveryRequest.getCityMiles())
					.add(vehicleA.highwayFuelConsume(deliveryRequest.getHighwayMiles()));

		when(vehicleRepository.findAll()).thenReturn(vehicles);
		List<DeliveryResponse> deliveryResponse = deliveryService.getForecastCost(deliveryRequest);
		assertEquals(Consume, deliveryResponse.get(0).getFuel());
	}
}
