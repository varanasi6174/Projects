package com.sipl.tracker_rest_repo.controllers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.controllers.CountryController;
import com.sipl.tracker_rest_repo.dto.CountryDto;
import com.sipl.tracker_rest_repo.dto.response.CountryApiResponse;
import com.sipl.tracker_rest_repo.services.CountryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryControllerImpl implements CountryController {

	private final CountryService service;

	@Autowired
	public CountryControllerImpl(CountryService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<CountryApiResponse> save(CountryDto countryDto) {
		log.error("Exception in addCountry controller");
		ResponseEntity<CountryApiResponse> responseEntity = new ResponseEntity<>(service.addCountryData(countryDto),
				HttpStatus.OK);
		log.info("<<END>> addCountry <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CountryApiResponse> findAll() {
		log.error("Exception in getAllCountry controller");
		ResponseEntity<CountryApiResponse> responseEntity = new ResponseEntity<>(service.getAllCountryData(),
				HttpStatus.OK);
		log.info("<<END>> getAllCountry <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CountryApiResponse> update(CountryDto countryDto) {
		log.error("Exception in updateCountry controller");
		ResponseEntity<CountryApiResponse> responseEntity = new ResponseEntity<>(service.updateCountryData(countryDto),
				HttpStatus.OK);
		log.info("<<END>> updateCountry <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CountryApiResponse> findById(Integer id) {
		log.error("Exception in getCountryById controller");
		ResponseEntity<CountryApiResponse> responseEntity = new ResponseEntity<>(service.getCountryById(id),
				HttpStatus.OK);
		log.info("<<END>> getCountryById <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CountryApiResponse> deleteById(Integer id) {
		log.error("Exception in deleteCountry controller");
		ResponseEntity<CountryApiResponse> responseEntity = new ResponseEntity<>(service.deleteCountryById(id),
				HttpStatus.OK);
		log.info("<<END>> deleteCountry <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CountryApiResponse> getAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		log.error("Exception in getAllPagination controller");
		ResponseEntity<CountryApiResponse> responseEntity = new ResponseEntity<>(
				service.findAllPagination(pageNum, pageSize), HttpStatus.OK);
		log.info("<<END>> getAllPagination <<END>>");
		return responseEntity;
	}

}
