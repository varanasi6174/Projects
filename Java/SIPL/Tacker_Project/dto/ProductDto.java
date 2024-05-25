package com.sipl.tracker_rest_repo.dto;

import com.sipl.tracker_rest_repo.dao.entities.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Integer productId;
	private String productName;
	private Boolean isActive;
	private AuditEntity auditEntity;

}
