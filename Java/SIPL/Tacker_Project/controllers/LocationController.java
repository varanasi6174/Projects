package com.sipl.tracker_rest_repo.controllers;

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

import com.sipl.tracker_rest_repo.dto.LocationDto;
import com.sipl.tracker_rest_repo.dto.response.LocationApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/location")
public interface LocationController {

	@GetMapping("/getById/{id}")
	ResponseEntity<LocationApiResponse> findById(@PathVariable("id") Integer id);

	@GetMapping("/getAll")
	ResponseEntity<LocationApiResponse> findAll();

	@PostMapping("/add")
	ResponseEntity<LocationApiResponse> save(@RequestBody LocationDto locationDto);

	@DeleteMapping("/deleteById/{id}")
	ResponseEntity<LocationApiResponse> deleteById(@PathVariable("id") Integer id);

	@PutMapping("/update/{id}")
	ResponseEntity<LocationApiResponse> update(@RequestBody LocationDto locationDto);

	@GetMapping("/pagination")
	ResponseEntity<LocationApiResponse> getAllPagination(
			@RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize);

}
