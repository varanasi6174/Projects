package com.sipl.tracker_rest_repo.dao.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "releases")
public class ReleaseMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "release_id")
	private Integer releaseId;

	@Column(name = "release_name", nullable = false)
	private String releaseName;

	@Column(name = "release_short_code", nullable = false)
	private String releaseShortCode;

	@Column(name = "release_desc", nullable = false)
	private String releaseDesc;

	@Column(name = "release_start_date", nullable = false)
	private LocalDateTime releaseStartDate;

	@Column(name = "release_end_date", nullable = false)
	private LocalDateTime releaseEndDate;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Embedded
	private AuditEntity auditEntity;

}
