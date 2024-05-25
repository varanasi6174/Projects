package com.sipl.tracker_rest_repo.dto;

import com.sipl.tracker_rest_repo.dao.entities.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDto {
	private int countryId;
	private String countryName;
	private boolean isActive;
	private boolean isDeleted;
	private AuditEntity auditEntity;

}
