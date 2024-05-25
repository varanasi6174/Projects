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

import com.sipl.tracker_rest_repo.dto.ProductDto;
import com.sipl.tracker_rest_repo.dto.response.ProductApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public interface ProductController {

	@GetMapping("/getById/{id}")
	ResponseEntity<ProductApiResponse> findById(@PathVariable("id") Integer id);

	@GetMapping("/getAll")
	ResponseEntity<ProductApiResponse> findAll();

	@PostMapping("/add")
	ResponseEntity<ProductApiResponse> save(@RequestBody ProductDto productDto);

	@DeleteMapping("/deleteById/{id}")
	ResponseEntity<ProductApiResponse> deleteById(@PathVariable("id") Integer id);

	@PutMapping("/update/{id}")
	ResponseEntity<ProductApiResponse> update(@RequestBody ProductDto productDto);

	@GetMapping("/pagination")
	ResponseEntity<ProductApiResponse> getAllPagination(
			@RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize);

}
