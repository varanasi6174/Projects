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
@Table(name = "tag_type_master")
public class TagTypeMaster implements Serializable {
    private static final long serialVersionUID = 3980494633996944406L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "tag_type_name")
    private String tagTypeName;

    @Column(name = "active")
    private Boolean isActive;

	@Embedded
	private AuditEntity auditEntity;
}

