package com.sipl.vehicle.management.exception;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.vehicle.management.vehicleDTO.RfidTagMasterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RfidTagMasterPageApiResponse {
	  private Page<RfidTagMasterDto> rfidTagMasterDto;
	   private HttpStatus status;
	    private String message;
	    private boolean error;
}