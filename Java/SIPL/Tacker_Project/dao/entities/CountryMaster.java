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
@Table(name = "countries")
public class CountryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "country_name")
	private String countryName;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Embedded
	private AuditEntity auditEntity;

    
}
