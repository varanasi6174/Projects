package com.sipl.tracker_rest_repo.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.tracker_rest_repo.dto.LocationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationApiResponse {
	private LocationDto locationDto;
	private List<LocationDto> locationDtos;
	private Page<LocationDto> locationDtoPage;
	private HttpStatus status;
	private String message;
	private Boolean error;
}
