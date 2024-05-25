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

import com.sipl.tracker_rest_repo.dto.ReleaseDto;
import com.sipl.tracker_rest_repo.dto.response.ReleaseApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/release")
public interface ReleaseController {

	@GetMapping("/getById/{id}")
	ResponseEntity<ReleaseApiResponse> findById(@PathVariable("id") Integer id);

	@GetMapping("/getAll")
	ResponseEntity<ReleaseApiResponse> findAll();

	@PostMapping("/add")
	ResponseEntity<ReleaseApiResponse> save(@RequestBody ReleaseDto releaseDto);

	@DeleteMapping("/deleteById/{id}")
	ResponseEntity<ReleaseApiResponse> deleteById(@PathVariable("id") Integer id);

	@PutMapping("/update/{id}")
	ResponseEntity<ReleaseApiResponse> update(@RequestBody ReleaseDto releaseDto);

	@GetMapping("/pagination")
	ResponseEntity<ReleaseApiResponse> getAllPagination(
			@RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize);

}
