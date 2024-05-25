package com.sipl.vehicle.management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.sipl.vehicle.management.entity.Vehicle;
import com.sipl.vehicle.management.mapper.VehicleMapper;
import com.sipl.vehicle.management.service.VehicleService;
import com.sipl.vehicle.management.vehicleDTO.VehicleDTO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private VehicleMapper vehicleMapper;

	// Add Method

	@PostMapping("/addVehicle")
	public ResponseEntity<Vehicle> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
		System.out.println("Add vehicle successfully in DB");
		return new ResponseEntity<>(vehicleService.createVehicle(vehicleDTO), HttpStatus.CREATED);
	}

	// Get By Id Method

	@GetMapping("/getVehicle/{id}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") long id) {

		return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
	}

	// Get All Method

	@GetMapping("/getVehicle")
	public ResponseEntity<List<Vehicle>> getVehicle() {
		return new ResponseEntity<>(vehicleService.getAllVehicle(), HttpStatus.OK);
	}

	// Using RestTemplate

	@GetMapping("/getvehicletemp")
	public String getVehicleTemp() {

		return vehicleService.getVehiclebyTemplet();
	}

	// pagination

	@GetMapping("/getVehicle/{offset}/{pagesize}")
	public ResponseEntity<Page<Vehicle>> getVehicle(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		Page<Vehicle> vehiclesPage = vehicleService.getAllVehicle(pageNo, pageSize);

		if (vehiclesPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(vehiclesPage, HttpStatus.OK);
	}

	// pagination and sorting

	@GetMapping("/getVehicle/{offset}/{pagesize}/{feild}")
	public ResponseEntity<Page<Vehicle>> getVehicle(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "Id") String sortBy) {

		Page<Vehicle> vehiclesPage = vehicleService.getAllVehicle(pageNo, pageSize, sortBy);

		if (vehiclesPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(vehiclesPage, HttpStatus.OK);
	}

	// Update Method

	@PutMapping("/editVehicle/{id}")
	public ResponseEntity<Vehicle> editVehicle(@Valid @PathVariable("id") long id, @RequestBody VehicleDTO vehicleDTO) {
		System.out.println("Updated successfully....");
		return new ResponseEntity<>(vehicleService.updateVehicle(id, vehicleDTO), HttpStatus.OK);
	}

	// Delete Method

	@DeleteMapping("/deleteVehicle/{id}")
	public ResponseEntity<String> deleteVehicle(@PathVariable("id") long id) {
		vehicleService.deleteVehicle(id);
		return new ResponseEntity<>("Deleted successfully....", HttpStatus.OK);
	}

	// create pdf

	@GetMapping("/pdf/generate")
	public ResponseEntity<String> generatePdf(HttpServletResponse response) {
		try {
			response.setContentType("application/pdf");

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=generated.pdf";
			response.setHeader(headerKey, headerValue);

			this.vehicleService.export(response);
			return ResponseEntity.ok("PDF generated successfully");
		} catch (DocumentException | IOException e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("PDF generation failed");
		}
	}

	// create criteria

	@GetMapping("/search/criteria")
	public List<Vehicle> searchVehicles(@RequestParam(required = false) String vehicleRegistrationNumber,
			@RequestParam(required = false) String ownerName, @RequestParam(required = false) String brand) {
		return vehicleService.searchVehicles(vehicleRegistrationNumber, ownerName, brand);
	}


}