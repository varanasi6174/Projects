package com.sipl.tracker_rest_repo.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.tracker_rest_repo.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductApiResponse {
	private ProductDto productDto;
	private List<ProductDto> productDtos;
	private Page<ProductDto> productDtoPage;
	private HttpStatus status;
	private String message;
	private Boolean error;
}
