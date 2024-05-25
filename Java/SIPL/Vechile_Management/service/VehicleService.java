package com.sipl.vehicle.management.service;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.lowagie.text.DocumentException;
import com.sipl.vehicle.management.entity.Vehicle;
import com.sipl.vehicle.management.vehicleDTO.VehicleDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface VehicleService {

	Vehicle createVehicle(VehicleDTO vehicleDto);

	Vehicle getVehicleById(Long id);

	List<Vehicle> getAllVehicle();

	Vehicle updateVehicle(Long id, VehicleDTO vehicleDto);

	void deleteVehicle(Long id);

	Page<Vehicle> getAllVehicle(int pageNo, int pageSize);

	Page<Vehicle> getAllVehicle(int pageNo, int pageSize, String sortBy);

	String getVehiclebyTemplet();

	ResponseEntity<String> export(HttpServletResponse response) throws DocumentException, IOException;

	List<Vehicle> searchVehicles(String vehicleRegistrationNumber, String ownerName, String brand);


}
