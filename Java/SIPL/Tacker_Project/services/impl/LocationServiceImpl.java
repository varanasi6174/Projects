package com.sipl.tracker_rest_repo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dao.entities.LocationMaster;
import com.sipl.tracker_rest_repo.dao.repositories.LocationRepository;
import com.sipl.tracker_rest_repo.dto.LocationDto;
import com.sipl.tracker_rest_repo.dto.response.LocationApiResponse;
import com.sipl.tracker_rest_repo.mappers.LocationMapper;
import com.sipl.tracker_rest_repo.services.LocationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

	private final LocationRepository repository;
	private final LocationMapper mapper;

	@Autowired
	public LocationServiceImpl(LocationRepository repository, LocationMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public LocationApiResponse getLocationById(Integer id) {
		try {
			log.info("Finding LocationMaster with ID: {}", id);
			Optional<LocationMaster> locationOptional = repository.findById(id);
			if (locationOptional.isPresent()) {
				LocationMaster entity = locationOptional.get();
				LocationDto locationDto = mapper.mapLocationEntityToLocationDto(entity);
				log.info("LocationMaster found with ID: {}", id);
				return new LocationApiResponse(locationDto, null, null, HttpStatus.OK, "LocationMaster found", true);
			} else {
				log.warn("LocationMaster not found with ID: {}", id);
				return new LocationApiResponse(null, null, null, HttpStatus.NOT_FOUND, "LocationMaster not found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", id, e);
		} catch (Exception e) {
			log.error("An error occurred while finding LocationMaster with ID: {}", id, e);
		}
		return new LocationApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while finding LocationMaster", false);

	}

	@Override
	public LocationApiResponse getAllLocationData() {
		try {
			log.info("Finding all LocationMaster");
			List<LocationMaster> locationMaster = repository.findAllActive();
			if (locationMaster.isEmpty()) {
				log.warn("No LocationMaster found in the database");
				return new LocationApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No LocationMaster found in the database", false);
			} else {
				List<LocationDto> locationDtos = locationMaster.stream().map(mapper::mapLocationEntityToLocationDto)
						.collect(Collectors.toList());
				log.info("LocationMaster retrieved successfully");
				return new LocationApiResponse(null, locationDtos, null, HttpStatus.OK,
						"LocationMaster retrieved successfully", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving LocationMaster", e);
		}
		return new LocationApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving LocationMaster", false);

	}

	@Override
	public LocationApiResponse addLocationData(LocationDto locationDto) {
		try {
			log.info("Saving LocationMaster");
			LocationMaster entity = mapper.mapLocationDtoToLocationEntity(locationDto);
			entity.setIsDeleted(false);
			entity.setIsActive(true);
			entity = repository.save(entity);
			LocationDto savedDto = mapper.mapLocationEntityToLocationDto(entity);
			log.info("LocationMaster saved successfully");
			return new LocationApiResponse(savedDto, null, null, HttpStatus.CREATED,
					"LocationMaster saved successfully", true);
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while saving LocationMaster", e);
		}
		return new LocationApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while saving LocationMaster", false);

	}

	@Override
	public LocationApiResponse deleteLocationById(Integer id) {
		log.info("<<start>>In LocationMaster deleteById method<<start>>");
		try {
			log.info("Deleting LocationMaster with ID: " + id);
			final Optional<LocationMaster> locationOptional = repository.findById(id);
			log.info("LocationMaster deleteLocationById response: " + locationOptional);
			if (locationOptional.isPresent()) {
				LocationMaster locationIdFromDb = locationOptional.get();
				locationIdFromDb.setIsDeleted(true);
				locationIdFromDb.setIsActive(false);
				repository.save(locationIdFromDb);
				log.info("Updated LocationMaster with ID: " + id + ", active set to false");
				final LocationDto locationDtoToSend = mapper.mapLocationEntityToLocationDto(locationIdFromDb);
				log.info("Sending updated locationDto response: " + locationDtoToSend);
				return new LocationApiResponse(locationDtoToSend, null, null, HttpStatus.OK,
						"LocationMaster deleted successfully", true);
			} else {
				return new LocationApiResponse(null, null, null, HttpStatus.NOT_FOUND, "LocationMaster not found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", id, e);
		} catch (Exception e) {
			log.error("An error occurred while deleting LocationMaster", e);
		}
		return new LocationApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);

	}

	@Override
	public LocationApiResponse updateLocationData(LocationDto locationDto) {
		try {
			log.info("Updating LocationMaster with ID: {}", locationDto.getLocationId());
			Optional<LocationMaster> optionalEntity = repository.findById(locationDto.getLocationId());
			if (optionalEntity.isPresent()) {
				LocationMaster existingEntity = mapper.mapLocationDtoToLocationEntity(locationDto);
				existingEntity.setIsDeleted(false);
				existingEntity.setIsActive(true);
				existingEntity.setLocationId(optionalEntity.get().getLocationId());
				LocationMaster updateLocationMaster = repository.save(existingEntity);
				LocationDto updatedDto = mapper.mapLocationEntityToLocationDto(updateLocationMaster);
				log.info("LocationMaster updated successfully with ID: {}", locationDto.getLocationId());
				return new LocationApiResponse(updatedDto, null, null, HttpStatus.OK,
						"LocationMaster updated successfully", true);
			} else {
				log.warn("LocationMaster not found with ID: {}", locationDto.getLocationId());
				return new LocationApiResponse(locationDto, null, null, HttpStatus.NOT_FOUND,
						"LocationMaster not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while updating LocationMaster", e);
		}
		return new LocationApiResponse(locationDto, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while updating LocationMaster", false);

	}

	@Override
	public LocationApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		try {
			log.info("Retrieving LocationMaster with pagination");
			int page = pageNum.orElse(0);
			int size = pageSize.orElse(10);
			Pageable pageable = PageRequest.of(page, size);
			Page<LocationMaster> locationMaster = repository.findAllpagination(pageable);
			if (locationMaster.isEmpty()) {
				log.warn("No LocationMaster found in the database for the given page");
				return new LocationApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No LocationMaster found in the database for the given page", false);
			} else {
				List<LocationDto> dtos = locationMaster.getContent().stream()
						.map(mapper::mapLocationEntityToLocationDto).collect(Collectors.toList());
				log.info("LocationMaster retrieved successfully with pagination");
				return new LocationApiResponse(null, dtos, null, HttpStatus.OK,
						"LocationMaster retrieved successfully with pagination", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving LocationMaster with pagination", e);
		}
		return new LocationApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving LocationMaster with pagination", false);

	}
}
