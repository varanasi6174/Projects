package com.sipl.tracker_rest_repo.controllers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sipl.tracker_rest_repo.controllers.LocationController;
import com.sipl.tracker_rest_repo.dto.LocationDto;
import com.sipl.tracker_rest_repo.dto.response.LocationApiResponse;
import com.sipl.tracker_rest_repo.services.LocationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationControllerImpl implements LocationController {

	private final LocationService service;

	@Autowired
	public LocationControllerImpl(LocationService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<LocationApiResponse> save(@RequestBody LocationDto locationDto) {
		log.error("Exception in addLocation controller");
		ResponseEntity<LocationApiResponse> responseEntity = new ResponseEntity<>(service.addLocationData(locationDto),
				HttpStatus.OK);
		log.info("<<END>> addLocation <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<LocationApiResponse> findAll() {
		log.error("Exception in getAllLocation controller");
		ResponseEntity<LocationApiResponse> responseEntity = new ResponseEntity<>(service.getAllLocationData(),
				HttpStatus.OK);
		log.info("<<END>> getAllLocation <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<LocationApiResponse> update(@RequestBody LocationDto locationDto) {
		log.error("Exception in updateLocation controller");
		ResponseEntity<LocationApiResponse> responseEntity = new ResponseEntity<>(
				service.updateLocationData(locationDto), HttpStatus.OK);
		log.info("<<END>> updateLocation <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<LocationApiResponse> findById(@PathVariable Integer id) {
		log.error("Exception in getLocationById controller");
		ResponseEntity<LocationApiResponse> responseEntity = new ResponseEntity<>(service.getLocationById(id),
				HttpStatus.OK);
		log.info("<<END>> getLocationById <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<LocationApiResponse> deleteById(@PathVariable Integer id) {
		log.error("Exception in deleteLocation controller");
		ResponseEntity<LocationApiResponse> responseEntity = new ResponseEntity<>(service.deleteLocationById(id),
				HttpStatus.OK);
		log.info("<<END>> deleteLocation <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<LocationApiResponse> getAllPagination(
			@RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize) {
		log.error("Exception in getAllPagination controller");
		ResponseEntity<LocationApiResponse> responseEntity = new ResponseEntity<>(
				service.findAllPagination(pageNum, pageSize), HttpStatus.OK);
		log.info("<<END>> getAllPagination <<END>>");
		return responseEntity;
	}
}
