package com.sipl.tracker_rest_repo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dao.entities.ProductMaster;
import com.sipl.tracker_rest_repo.dao.repositories.ProductRepository;
import com.sipl.tracker_rest_repo.dto.ProductDto;
import com.sipl.tracker_rest_repo.dto.response.ProductApiResponse;
import com.sipl.tracker_rest_repo.mappers.ProductMapper;
import com.sipl.tracker_rest_repo.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final ProductMapper mapper;

	@Autowired
	public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public ProductApiResponse findById(Integer id) {
		try {
			log.info("Finding Product with ID: {}", id);
			Optional<ProductMaster> productOptional = repository.findById(id);
			if (productOptional.isPresent()) {
				ProductMaster entity = productOptional.get();
				ProductDto productDto = mapper.mapProductMasterToProductDto(entity);
				log.info("Product found with ID: {}", id);
				return new ProductApiResponse(productDto, null, null, HttpStatus.OK, "Product found", true);
			} else {
				log.warn("Product not found with ID: {}", id);
				return new ProductApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Product not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding Product with ID: {}", id, e);
		} catch (Exception e) {
			log.error("An error occurred while finding Product with ID: {}", id, e);
		}
		return new ProductApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while finding Product", false);
	}

	@Override
	public ProductApiResponse findAll() {
		try {
			log.info("Finding all Products");
			List<ProductMaster> products = repository.findAll();
			if (products.isEmpty()) {
				log.warn("No Products found in the database");
				return new ProductApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No Products found in the database", false);
			} else {
				List<ProductDto> productDtos = products.stream().map(mapper::mapProductMasterToProductDto)
						.collect(Collectors.toList());
				log.info("Products retrieved successfully");
				return new ProductApiResponse(null, productDtos, null, HttpStatus.OK, "Products retrieved successfully",
						true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while retrieving Products", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving Products", e);
		}
		return new ProductApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving Products", false);
	}

	@Override
	public ProductApiResponse save(ProductDto productDto) {
		try {
			log.info("Saving Product");
			ProductMaster entity = mapper.mapProductDtoToProductMaster(productDto);
			entity.setIsActive(true);
			entity = repository.save(entity);
			ProductDto savedDto = mapper.mapProductMasterToProductDto(entity);
			log.info("Product saved successfully");
			return new ProductApiResponse(savedDto, null, null, HttpStatus.CREATED, "Product saved successfully", true);
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while saving Product", e);
		} catch (Exception e) {
			log.error("An error occurred while saving Product", e);
		}
		return new ProductApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while saving Product", false);
	}

	@Override
	public ProductApiResponse deleteById(Integer id) {
		log.info("<<start>>In Product deleteById method<<start>>");
		try {
			log.info("Deleting Product with ID: " + id);
			final Optional<ProductMaster> productOptional = repository.findById(id);
			log.info("Product deleteProductById response: " + productOptional);
			if (productOptional.isPresent()) {
				ProductMaster productFromDb = productOptional.get();
				productFromDb.setIsActive(false);
				repository.save(productFromDb);
				log.info("Updated Product with ID: " + id + ", active set to false");
				final ProductDto productDtoToSend = mapper.mapProductMasterToProductDto(productFromDb);
				log.info("Sending updated productDto response: " + productDtoToSend);
				return new ProductApiResponse(productDtoToSend, null, null, HttpStatus.OK,
						"Product deleted successfully", true);
			} else {
				return new ProductApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Product not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while deleting Product", e);
		} catch (final Exception e) {
			log.error("An error occurred while deleting Product", e);
		}
		return new ProductApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Override
	public ProductApiResponse update(ProductDto productDto) {
		try {
			log.info("Updating Product with ID: {}", productDto.getProductId());
			Optional<ProductMaster> optionalEntity = repository.findById(productDto.getProductId());
			if (optionalEntity.isPresent()) {
				ProductMaster existingEntity = mapper.mapProductDtoToProductMaster(productDto);
				existingEntity.setIsActive(true);
				existingEntity.setProductId(optionalEntity.get().getProductId());
				existingEntity = repository.save(existingEntity);
				ProductDto updatedDto = mapper.mapProductMasterToProductDto(existingEntity);
				log.info("Product updated successfully with ID: {}", productDto.getProductId());
				return new ProductApiResponse(updatedDto, null, null, HttpStatus.OK, "Product updated successfully",
						true);
			} else {
				log.warn("Product not found with ID: {}", productDto.getProductId());
				return new ProductApiResponse(productDto, null, null, HttpStatus.NOT_FOUND, "Product not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while updating Product", e);
		} catch (Exception e) {
			log.error("An error occurred while updating Product", e);
		}
		return new ProductApiResponse(productDto, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while updating Product", false);
	}

	@Override
	public ProductApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		try {
			log.info("Retrieving Products with pagination");
			int page = pageNum.orElse(0);
			int size = pageSize.orElse(10);
			Pageable pageable = PageRequest.of(page, size);
			Page<ProductMaster> products = repository.findAllPagination(pageable);
			if (products.isEmpty()) {
				log.warn("No Products found in the database for the given page");
				return new ProductApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No Products found in the database for the given page", false);
			} else {
				List<ProductDto> dtos = products.getContent().stream().map(mapper::mapProductMasterToProductDto)
						.collect(Collectors.toList());
				log.info("Products retrieved successfully with pagination");
				return new ProductApiResponse(null, dtos, null, HttpStatus.OK,
						"Products retrieved successfully with pagination", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while retrieving Products with pagination", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving Products with pagination", e);
		}
		return new ProductApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving Products with pagination", false);
	}
}