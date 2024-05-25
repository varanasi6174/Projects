package com.sipl.tracker_rest_repo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.tracker_rest_repo.dao.entities.ProductMaster;
import com.sipl.tracker_rest_repo.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductMaster mapProductDtoToProductMaster(ProductDto productDto);

	ProductDto mapProductMasterToProductDto(ProductMaster productMaster);

	List<ProductDto> mapProductMasterListToProductDtoList(List<ProductMaster> productMasterList);

	default Page<ProductDto> mapProductMasterPageToProductDtoPage(Page<ProductMaster> productMasterPage) {
		return productMasterPage.map(this::mapProductMasterToProductDto);
	}
}
