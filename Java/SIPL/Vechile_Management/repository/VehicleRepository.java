package com.sipl.vehicle.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sipl.vehicle.management.entity.Vehicle;
import com.sipl.vehicle.management.vehicleDTO.VehicleDTO;

public interface VehicleRepository extends  JpaRepository<Vehicle, String> {

	Optional<Vehicle> findById(Long id);

	void deleteById(Long id);

	Vehicle save(VehicleDTO updatedVehicle);

	boolean existsByVehicleRegistrationNumber(String vehicleRegistrationNumber);



}
