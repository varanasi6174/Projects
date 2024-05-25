package com.sipl.tracker_rest_repo.controllers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.controllers.ProductController;
import com.sipl.tracker_rest_repo.dto.ProductDto;
import com.sipl.tracker_rest_repo.dto.response.ProductApiResponse;
import com.sipl.tracker_rest_repo.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductControllerImpl implements ProductController {

	private final ProductService service;

	@Autowired
	public ProductControllerImpl(ProductService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<ProductApiResponse> save(ProductDto productDto) {
		log.error("Exception in addProduct controller");
		ResponseEntity<ProductApiResponse> responseEntity = new ResponseEntity<>(service.save(productDto),
				HttpStatus.OK);
		log.info("<<END>> addProduct <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ProductApiResponse> findAll() {
		log.error("Exception in getAllProducts controller");
		ResponseEntity<ProductApiResponse> responseEntity = new ResponseEntity<>(service.findAll(), HttpStatus.OK);
		log.info("<<END>> getAllProducts <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ProductApiResponse> update(ProductDto productDto) {
		log.error("Exception in updateProduct controller");
		ResponseEntity<ProductApiResponse> responseEntity = new ResponseEntity<>(service.update(productDto),
				HttpStatus.OK);
		log.info("<<END>> updateProduct <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ProductApiResponse> findById(Integer id) {
		log.error("Exception in getProductById controller");
		ResponseEntity<ProductApiResponse> responseEntity = new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		log.info("<<END>> getProductById <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ProductApiResponse> deleteById(Integer id) {
		log.error("Exception in deleteProduct controller");
		ResponseEntity<ProductApiResponse> responseEntity = new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
		log.info("<<END>> deleteProduct <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ProductApiResponse> getAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		log.error("Exception in getAllPagination controller");
		ResponseEntity<ProductApiResponse> responseEntity = new ResponseEntity<>(
				service.findAllPagination(pageNum, pageSize), HttpStatus.OK);
		log.info("<<END>> getAllPagination <<END>>");
		return responseEntity;
	}

}
