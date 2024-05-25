package com.sipl.vehicle.management.vehicleDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReaderConfigMasterDto {
	private Integer readerConfigId;
	private Boolean rssiFilterEnabled;
	private Integer rssiLowLimit;
	private Integer rssiUpperLimit;
	private Boolean isActive;
	private LocalDateTime creationTime;
	private LocalDateTime modifiedTime;
}
