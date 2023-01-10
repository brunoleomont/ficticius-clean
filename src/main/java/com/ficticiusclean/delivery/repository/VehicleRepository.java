package com.ficticiusclean.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ficticiusclean.delivery.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String>{
    
}
