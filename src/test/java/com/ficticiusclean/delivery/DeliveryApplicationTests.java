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
	void deliveryServiceResponseCheaperTest() {
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(10.0), new BigDecimal(100.0), new BigDecimal(100.0));
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicleA = Utils.createVehicle();
		vehicleA.setCityFuelConsumption(new BigDecimal(10));
		vehicleA.setHighwayFuelConsumption(new BigDecimal(10));
		vehicles.add(vehicleA);
		Vehicle vehicleB = Utils.createVehicle();
		vehicleB.setCityFuelConsumption(new BigDecimal(20));
		vehicleB.setHighwayFuelConsumption(new BigDecimal(20));
		vehicles.add(vehicleB);
		Vehicle vehicleC = Utils.createVehicle();
		vehicleC.setCityFuelConsumption(new BigDecimal(30));
		vehicleC.setHighwayFuelConsumption(new BigDecimal(30));
		vehicles.add(vehicleC);

		when(vehicleRepository.findAll()).thenReturn(vehicles);
		List<DeliveryResponse> deliveryResponse = deliveryService.getForecastCost(deliveryRequest);
		assertEquals(vehicleC.getName(), deliveryResponse.get(0).getName());
	}

	@Test
	void deliveryServiceResponseMoreExpensiveTest() {
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(10.0), new BigDecimal(100.0), new BigDecimal(100.0));
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicleA = Utils.createVehicle();
		vehicleA.setCityFuelConsumption(new BigDecimal(10));
		vehicleA.setHighwayFuelConsumption(new BigDecimal(10));
		vehicles.add(vehicleA);
		Vehicle vehicleB = Utils.createVehicle();
		vehicleB.setCityFuelConsumption(new BigDecimal(20));
		vehicleB.setHighwayFuelConsumption(new BigDecimal(20));
		vehicles.add(vehicleB);
		Vehicle vehicleC = Utils.createVehicle();
		vehicleC.setCityFuelConsumption(new BigDecimal(30));
		vehicleC.setHighwayFuelConsumption(new BigDecimal(30));
		vehicles.add(vehicleC);

		when(vehicleRepository.findAll()).thenReturn(vehicles);
		List<DeliveryResponse> deliveryResponse = deliveryService.getForecastCost(deliveryRequest);
		assertEquals(vehicleA.getName(), deliveryResponse.get(2).getName());
	}

	@Test
	void deliveryServiceResponseCostTest() {
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(10.0), new BigDecimal(100.0), new BigDecimal(100.0));
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
		DeliveryRequest deliveryRequest = new DeliveryRequest(new BigDecimal(10.0), new BigDecimal(100.0), new BigDecimal(100.0));
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
