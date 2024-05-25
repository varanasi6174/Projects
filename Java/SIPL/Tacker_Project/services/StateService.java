package com.sipl.tracker_rest_repo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dto.StateDto;
import com.sipl.tracker_rest_repo.dto.response.StateApiResponse;

@Service
public interface StateService {
	StateApiResponse addStateData(StateDto stateDto);

	StateApiResponse getAllStateData();

	StateApiResponse updateStateData(StateDto stateDto);

	StateApiResponse getStateById(Integer id);

	StateApiResponse deleteStateById(Integer id);

	StateApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);

	//StateApiResponse generateAndSaveExcel();

	StateApiResponse generateAndSavePDF(List<StateDto> stateDtoList);

	StateApiResponse generateAndSaveExcel(Integer stateId, String stateName);

}
