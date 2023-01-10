package com.ficticiusclean.delivery.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ficticiusclean.delivery.controller.dto.VehicleRequest;
import com.ficticiusclean.delivery.entity.Vehicle;
import com.ficticiusclean.delivery.repository.VehicleRepository;

@RestController
public class VehicleController {

    Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleController(){};

    /**
	 * Find all Vehicle
	 * @param 
	 * @return List<Vehicle>
	 * @throws IOException
	 */
    @GetMapping("/vehicle")
    private ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleRepository.findAll());
    }

    /**
	 * Find Vehicle by id
	 * @param id
	 * @return Vehicle
	 * @throws IOException
	 */
    @GetMapping("/vehicle/{id}")
    private ResponseEntity<Vehicle> findById(@PathVariable String id) throws IOException{
        return ResponseEntity.status(HttpStatus.OK).body(
            vehicleRepository.findById(id).orElseThrow());
    }
    
    /**
	 * Create Vehicle
	 * @param VehicleRequest
	 * @return Vehicle
	 * @throws IOException
	 */
    @PostMapping("/vehicle")
    private ResponseEntity<Vehicle> create(@RequestBody VehicleRequest vehicleRequest) throws IOException{
        return ResponseEntity.status(HttpStatus.OK).body(
            vehicleRepository.saveAndFlush(vehicleRequest.toEntity()));
    }

    /**
	 * Update Vehicle by id
	 * @param VehicleRequest
	 * @return Vehicle
	 * @throws IOException
	 */
    @PutMapping("/vehicle/{id}")
    private ResponseEntity<Vehicle> update(@PathVariable String id, @RequestBody VehicleRequest vehicleRequest) throws IOException{
            Vehicle vehicleUpdate = vehicleRepository.findById(id).map(vehicle -> {
                vehicle.setName(vehicleRequest.getName());
                vehicle.setBrand(vehicleRequest.getBrand()); 
                vehicle.setModel(vehicleRequest.getModel());
                vehicle.setFabricationDate(vehicleRequest.getFabricationDate());
                vehicle.setCityFuelConsumption(vehicleRequest.getCityFuelConsumption());
                vehicle.setHighwayFuelConsumption(vehicleRequest.getHighwayFuelConsumption());
                return vehicle;
            }).get();

        return ResponseEntity.status(HttpStatus.OK).body(
            vehicleRepository.saveAndFlush(vehicleUpdate));
    }

    /**
	 * Delete Vehicle by id
	 * @param VehicleRequest
	 * @return VehicleRespose
	 * @throws IOException
	 */
    @DeleteMapping("/vehicle/{id}")
    private ResponseEntity<Void> delete(@PathVariable String id) throws IOException{
        vehicleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
