package com.sipl.tracker_rest_repo.dto;

import java.time.LocalDateTime;

import com.sipl.tracker_rest_repo.dao.entities.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReleaseDto {
	private Integer releaseId;
	private String releaseName;
	private String releaseShortCode;
	private String releaseDesc;
	private LocalDateTime releaseStartDate;
	private LocalDateTime releaseEndDate;
	private boolean isDeleted;
	private AuditEntity auditEntity;
}
