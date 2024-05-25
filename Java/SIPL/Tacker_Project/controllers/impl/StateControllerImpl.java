package com.sipl.tracker_rest_repo.controllers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sipl.tracker_rest_repo.controllers.StateController;
import com.sipl.tracker_rest_repo.dto.StateDto;
import com.sipl.tracker_rest_repo.dto.response.StateApiResponse;
import com.sipl.tracker_rest_repo.services.StateService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StateControllerImpl implements StateController {

	private final StateService service;

	@Autowired
	public StateControllerImpl(StateService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<StateApiResponse> save(@RequestBody StateDto stateDto) {
		log.error("Exception in addState controller");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.addStateData(stateDto),
				HttpStatus.OK);
		log.info("<<END>> addState <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> findAll() {
		log.error("Exception in getAllState controller");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.getAllStateData(),
				HttpStatus.OK);
		log.info("<<END>> getAllState <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> update(@RequestBody StateDto stateDto) {
		log.error("Exception in updateState controller");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.updateStateData(stateDto),
				HttpStatus.OK);
		log.info("<<END>> updateState <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> findById(@PathVariable Integer id) {
		log.error("Exception in getStateById controller");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.getStateById(id), HttpStatus.OK);
		log.info("<<END>> getStateById <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> deleteById(@PathVariable Integer id) {
		log.error("Exception in deleteState controller");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.deleteStateById(id),
				HttpStatus.OK);
		log.info("<<END>> deleteState <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> getAllPagination(
			@RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize) {
		log.error("Exception in getAllPagination controller");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(
				service.findAllPagination(pageNum, pageSize), HttpStatus.OK);
		log.info("<<END>> getAllPagination <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> generateAndSaveExcel(Integer stateId, String stateName) {
		log.info("Request received to generate and save Excel at path: {}");
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.generateAndSaveExcel( stateId, stateName),
				HttpStatus.CREATED);
		log.info("Response: {}", responseEntity);
		return responseEntity;
	}

	@Override
	public ResponseEntity<StateApiResponse> generateAndSavePDF() {
		log.info("Request received to generate and save Pdf at path: {}");
		List<StateDto> stateDtoList = service.getAllStateData().getStateDtos();
		ResponseEntity<StateApiResponse> responseEntity = new ResponseEntity<>(service.generateAndSavePDF(stateDtoList),
				HttpStatus.CREATED);
		log.info("Response: {}", responseEntity);
		return responseEntity;
	}

}