package com.sipl.tracker_rest_repo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dao.entities.CountryMaster;
import com.sipl.tracker_rest_repo.dao.repositories.CountryRepository;
import com.sipl.tracker_rest_repo.dto.CountryDto;
import com.sipl.tracker_rest_repo.dto.response.CountryApiResponse;
import com.sipl.tracker_rest_repo.mappers.CountryMapper;
import com.sipl.tracker_rest_repo.services.CountryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class CountryServiceImpl implements CountryService {

	private final CountryRepository repository;
	private final CountryMapper mapper;

	@Autowired
	public CountryServiceImpl(CountryRepository repository, CountryMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public CountryApiResponse getCountryById(Integer id) {
		try {
			log.info("Finding CountryMaster with ID: {}", id);
			Optional<CountryMaster> countryOptional = repository.findById(id);
			if (countryOptional.isPresent()) {
				CountryMaster entity = countryOptional.get();
				CountryDto countryDto = mapper.mapCountryEntityToCountryDto(entity);
				log.info("CountryMaster found with ID: {}", id);
				return new CountryApiResponse(countryDto, null, null, HttpStatus.OK, "CountryMaster found", true);
			} else {
				log.warn("CountryMaster not found with ID: {}", id);
				return new CountryApiResponse(null, null, null, HttpStatus.NOT_FOUND, "CountryMaster not found", false);
			}
		} catch (Exception e) {
			log.error("An error occurred while finding CountryMaster with ID: {}", id, e);
			return new CountryApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occurred while finding CountryMaster", false);
		}
	}

	@Override
	public CountryApiResponse getAllCountryData() {
		try {
			log.info("Finding all CountryMaster");
			List<CountryMaster> countryMaster = repository.findAllActive();
			if (countryMaster.isEmpty()) {
				log.warn("No CountryMaster found in the database");
				return new CountryApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No CountryMaster found in the database", false);
			} else {
				List<CountryDto> countryDtos = countryMaster.stream().map(mapper::mapCountryEntityToCountryDto)
						.collect(Collectors.toList());
				log.info("CountryMaster retrieved successfully");
				return new CountryApiResponse(null, countryDtos, null, HttpStatus.OK,
						"CountryMaster retrieved successfully", true);
			}
		} catch (Exception e) {
			log.error("An error occurred while retrieving CountryMaster", e);
			return new CountryApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occurred while retrieving CountryMaster", false);
		}
	}

	@Override
	public CountryApiResponse addCountryData(CountryDto countryDto) {
		try {
			log.info("Saving CountryMaster");
			CountryMaster entity = mapper.mapCountryDtoToCountryEntity(countryDto);
			entity.setIsDeleted(false);
			entity.setIsActive(true);
			entity = repository.save(entity);
			CountryDto savedDto = mapper.mapCountryEntityToCountryDto(entity);
			log.info("CountryMaster saved successfully");
			return new CountryApiResponse(savedDto, null, null, HttpStatus.CREATED, "CountryMaster saved successfully",
					true);
		} catch (Exception e) {
			log.error("An error occurred while saving CountryMaster", e);
			return new CountryApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occurred while saving CountryMaster", false);
		}
	}

	@Override
	public CountryApiResponse deleteCountryById(Integer id) {
		log.info("<<start>>In CountryMaster deleteById method<<start>>");
		try {
			log.info("Deleting CountryMaster with ID: " + id);
			final Optional<CountryMaster> countryOptional = repository.findById(id);
			log.info("CountryMaster deleteCountryById response: " + countryOptional);
			if (countryOptional.isPresent()) {
				CountryMaster countryIdFromDb = countryOptional.get();
				countryIdFromDb.setIsDeleted(true);
				countryIdFromDb.setIsActive(false);
				repository.save(countryIdFromDb);
				log.info("Updated CountryMaster with ID: " + id + ", active set to false");
				final CountryDto countryDtoToSend = mapper.mapCountryEntityToCountryDto(countryIdFromDb);
				log.info("Sending updated countryDto response: " + countryDtoToSend);
				return new CountryApiResponse(countryDtoToSend, null, null, HttpStatus.OK,
						"CountryMaster deleted successfully", true);
			} else {
				return new CountryApiResponse(null, null, null, HttpStatus.NOT_FOUND, "CountryMaster not found", false);
			}
		} catch (Exception e) {
			log.error("An error occurred while deleting CountryMaster", e);
			return new CountryApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}
	}

	@Override
	public CountryApiResponse updateCountryData(CountryDto countryDto) {
		try {
			log.info("Updating CountryMaster with ID: {}", countryDto.getCountryId());
			Optional<CountryMaster> optionalEntity = repository.findByCountryId(countryDto.getCountryId());
			if (optionalEntity.isPresent()) {
				CountryMaster existingEntity = mapper.mapCountryDtoToCountryEntity(countryDto);
				existingEntity.setIsDeleted(false);
				existingEntity.setIsActive(true);
				existingEntity.setCountryId(optionalEntity.get().getCountryId());
				CountryMaster updateCountryMaster = repository
						.save(existingEntity);
				CountryDto updatedDto = mapper.mapCountryEntityToCountryDto(updateCountryMaster);
				log.info("CountryMaster updated successfully with ID: {}", countryDto.getCountryId());
				return new CountryApiResponse(updatedDto, null, null, HttpStatus.OK,
						"CountryMaster updated successfully", true);
			} else {
				log.warn("CountryMaster not found with ID: {}", countryDto.getCountryId());
				return new CountryApiResponse(countryDto, null, null, HttpStatus.NOT_FOUND, "CountryMaster not found",
						false);
			}
		} catch (Exception e) {
			log.error("An error occurred while updating ReleaseMaster", e);
			return new CountryApiResponse(countryDto, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occurred while updating ReleaseMaster", false);
		}
	}

	@Override
	public CountryApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		try {
			log.info("Retrieving CountryMaster with pagination");
			int page = pageNum.orElse(0);
			int size = pageSize.orElse(10);
			Pageable pageable = PageRequest.of(page, size);
			Page<CountryMaster> countryMaster = repository.findAllpagination(pageable);
			if (countryMaster.isEmpty()) {
				log.warn("No CountryMaster found in the database for the given page");
				return new CountryApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No CountryMaster found in the database for the given page", false);
			} else {
				List<CountryDto> dtos = countryMaster.getContent().stream().map(mapper::mapCountryEntityToCountryDto)
						.collect(Collectors.toList());
				log.info("CountryMaster retrieved successfully with pagination");
				return new CountryApiResponse(null, dtos, null, HttpStatus.OK,
						"CountryMaster retrieved successfully with pagination", true);
			}
		} catch (Exception e) {
			log.error("An error occurred while retrieving CountryMaster with pagination", e);
			return new CountryApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occurred while retrieving CountryMaster with pagination", false);
		}
	}
}