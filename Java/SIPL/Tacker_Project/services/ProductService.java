package com.sipl.tracker_rest_repo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dto.ProductDto;
import com.sipl.tracker_rest_repo.dto.response.ProductApiResponse;

@Service
public interface ProductService {

	ProductApiResponse findById(Integer id);

	ProductApiResponse findAll();

	ProductApiResponse save(ProductDto productDto);

	ProductApiResponse update(ProductDto productDto);

	ProductApiResponse deleteById(Integer id);

	ProductApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);
}
