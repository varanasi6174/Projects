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

import com.sipl.tracker_rest_repo.dto.StateDto;
import com.sipl.tracker_rest_repo.dto.response.StateApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/state")
public interface StateController {

	@GetMapping("/getById/{id}")
	ResponseEntity<StateApiResponse> findById(@PathVariable("id") Integer id);

	@GetMapping("/getAll")
	ResponseEntity<StateApiResponse> findAll();

	@PostMapping("/add")
	ResponseEntity<StateApiResponse> save(@RequestBody StateDto stateDto);

	@DeleteMapping("/deleteById/{id}")
	ResponseEntity<StateApiResponse> deleteById(@PathVariable("id") Integer id);

	@PutMapping("/update/{id}")
	ResponseEntity<StateApiResponse> update(@RequestBody StateDto stateDto);

	@GetMapping("/pagination")
	ResponseEntity<StateApiResponse> getAllPagination(
			@RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize);

	@GetMapping("/excel")
	ResponseEntity<StateApiResponse> generateAndSaveExcel(Integer stateId, String stateName);

	@GetMapping("/pdf")
	ResponseEntity<StateApiResponse> generateAndSavePDF();

	
}
