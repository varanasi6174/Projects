package com.sipl.tracker_rest_repo.dto;

import com.sipl.tracker_rest_repo.dao.entities.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {
	private Integer locationId;
	private String locationName;
	private Boolean isActive;
	private Boolean isDeleted;
	private AuditEntity auditEntity;
}
