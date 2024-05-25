package com.sipl.tracker_rest_repo.controllers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.controllers.ReleaseController;
import com.sipl.tracker_rest_repo.dto.ReleaseDto;
import com.sipl.tracker_rest_repo.dto.response.ReleaseApiResponse;
import com.sipl.tracker_rest_repo.services.ReleaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReleaseControllerImpl implements ReleaseController {

	private final ReleaseService service;

	@Autowired
	public ReleaseControllerImpl(ReleaseService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<ReleaseApiResponse> save(ReleaseDto releaseDto) {
		log.error("Exception in addRelease controller");
		ResponseEntity<ReleaseApiResponse> responseEntity = new ResponseEntity<>(service.save(releaseDto),
				HttpStatus.OK);
		log.info("<<END>> addActivityTagMapping <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ReleaseApiResponse> findAll() {
		log.error("Exception in getAllReleases controller");
		ResponseEntity<ReleaseApiResponse> responseEntity = new ResponseEntity<>(service.findAll(), HttpStatus.OK);
		log.info("<<END>> getAllActivityTagMappings <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ReleaseApiResponse> update(ReleaseDto releaseDto) {
		log.error("Exception in updateRelease controller");
		ResponseEntity<ReleaseApiResponse> responseEntity = new ResponseEntity<>(service.update(releaseDto),
				HttpStatus.OK);
		log.info("<<END>> updateRelease <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ReleaseApiResponse> findById(Integer id) {
		log.error("Exception in getReleaseById controller");
		ResponseEntity<ReleaseApiResponse> responseEntity = new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		log.info("<<END>> getReleaseById <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ReleaseApiResponse> deleteById(Integer id) {
		log.error("Exception in deleteRelease controller");
		ResponseEntity<ReleaseApiResponse> responseEntity = new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
		log.info("<<END>> deleteRelease <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<ReleaseApiResponse> getAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		log.error("Exception in getAllPagination controller");
		ResponseEntity<ReleaseApiResponse> responseEntity = new ResponseEntity<>(
				service.findAllPagination(pageNum, pageSize), HttpStatus.OK);
		log.info("<<END>> getAllPagination <<END>>");
		return responseEntity;
	}

}
