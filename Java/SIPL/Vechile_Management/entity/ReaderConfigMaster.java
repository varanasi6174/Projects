package com.sipl.vehicle.management.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name ="reader_config_master")
public class ReaderConfigMaster implements Serializable {
	private static final long serialVersionUID = -9215318782066156785L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer readerConfigId;
	
	@Column(name = "is_rssi_filter_enabled")
	private Boolean rssiFilterEnabled;
	
	@Column(name = "rssi_low_limit")
	private Integer rssiLowLimit;
	
	@Column(name = "rssi_upper_limit")
	private Integer rssiUpperLimit;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Embedded
	private AuditEntity auditEntity;
}

