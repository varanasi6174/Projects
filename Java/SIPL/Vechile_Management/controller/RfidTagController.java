package com.sipl.vehicle.management.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.vehicle.management.exception.RfidTagMasterApiResponse;
import com.sipl.vehicle.management.exception.RfidTagMasterPageApiResponse;
import com.sipl.vehicle.management.vehicleDTO.RfidTagMasterDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = "*")
public interface RfidTagController {
	@PostMapping("/add")
	ResponseEntity<RfidTagMasterApiResponse> addRfidTag(@RequestBody RfidTagMasterDto rfidTagMasterDto);

	@GetMapping("/getAllByPagination")
	ResponseEntity<RfidTagMasterPageApiResponse> getAllByPagination(@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size);

	@GetMapping("/findPageByTags/{id}")
	ResponseEntity<RfidTagMasterPageApiResponse> findPageByTagId(@PathVariable Integer id,
			@RequestParam Optional<String> tagId, @RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size);

	@DeleteMapping("/{id}")
	ResponseEntity<RfidTagMasterApiResponse> deleteById(@PathVariable Integer id);

	@PutMapping("/update")
	ResponseEntity<RfidTagMasterApiResponse> updateRfidTag(@RequestBody RfidTagMasterDto rfidTagMasterDto);

	@GetMapping("/checkRfidTagIsInvalid/{rfidTagNumber}")
	ResponseEntity<RfidTagMasterApiResponse> findActiveRfidTag(@PathVariable String rfidTagNumber);

	@GetMapping("/showActiveTag")
	ResponseEntity<RfidTagMasterPageApiResponse> getActiveTags(@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size);
}
