package com.sipl.tracker_rest_repo.dao.entities;

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
@Table(name = "states")
public class StateMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "state_name")
	private String stateName;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Embedded
	private AuditEntity auditEntity;
}
