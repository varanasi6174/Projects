package com.sipl.vehicle.management.vehicleDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class VehicleDTO {
	@Id
	private Long id;
	private String vehicleRegistrationNumber;
	private String ownerName;
	private String brand;
	private LocalDate registrationExpires;
	private boolean isActive;
	private String createdBy;
	private LocalDateTime creationTime;
	private String modifiedBy;
	private LocalDateTime modifiedTime;
    private String qrCode;
    private byte[] qrImage;
}
