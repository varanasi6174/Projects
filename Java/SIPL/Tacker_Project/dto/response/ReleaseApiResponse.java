package com.sipl.tracker_rest_repo.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.sipl.tracker_rest_repo.dto.ReleaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReleaseApiResponse {
	private ReleaseDto releaseDto;
	private List<ReleaseDto> releaseDtos;
	private Page<ReleaseDto> releaseDtoPage;
	private HttpStatus status;
	private String message;
	private boolean error;
}
