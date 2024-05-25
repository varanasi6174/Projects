package com.sipl.vehicle.management.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Audited
@Table(name = "vehicle_info")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String vehicleRegistrationNumber;
	private String ownerName;
	private String brand;
	private LocalDate registrationExpires;
	private boolean isActive;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime creationTime;
	private String modifiedBy;
	@UpdateTimestamp
	private LocalDateTime modifiedTime;
	private String qrCode;
	@Lob
	private byte[] qrImage;

}
