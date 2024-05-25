package com.sipl.tracker_rest_repo.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.tracker_rest_repo.dto.StateDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateApiResponse {
	private StateDto stateDto;
	private List<StateDto> stateDtos;
	private Page<StateDto> stateDtoPage;
	private HttpStatus status;
	private String message;
	private Boolean error;
}
