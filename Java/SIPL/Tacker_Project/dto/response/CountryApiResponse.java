package com.sipl.tracker_rest_repo.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.tracker_rest_repo.dto.CountryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryApiResponse {
	private CountryDto countryDTO;
	private List<CountryDto> countryDTOs;
	private Page<CountryDto> countryDTOPage;
	private HttpStatus status;
	private String message;
	private boolean error;
}
