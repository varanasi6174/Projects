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

import com.sipl.tracker_rest_repo.dao.entities.ReleaseMaster;
import com.sipl.tracker_rest_repo.dao.repositories.ReleaseRepository;
import com.sipl.tracker_rest_repo.dto.ReleaseDto;
import com.sipl.tracker_rest_repo.dto.response.ReleaseApiResponse;
import com.sipl.tracker_rest_repo.mappers.ReleaseMapper;
import com.sipl.tracker_rest_repo.services.ReleaseService;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class ReleaseServiceImpl implements ReleaseService {

	private final ReleaseRepository repository;
	private final ReleaseMapper mapper;

	@Autowired
	public ReleaseServiceImpl(ReleaseRepository repository, ReleaseMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public ReleaseApiResponse findById(Integer id) {
		try {
			log.info("Finding ReleaseMaster with ID: {}", id);
			Optional<ReleaseMaster> releaseIdFetchedFromDb = repository.findById(id);
			if (releaseIdFetchedFromDb.isPresent()) {
				ReleaseMaster entity = releaseIdFetchedFromDb.get();
				ReleaseDto releaseDto = mapper.mapReleaseToReleaseDto(entity);
				log.info("ReleaseMaster found with ID: {}", id);
				return new ReleaseApiResponse(releaseDto, null, null, HttpStatus.OK, "ReleaseMaster found", true);
			} else {
				log.warn("ReleaseMaster not found with ID: {}", id);
				return new ReleaseApiResponse(null, null, null, HttpStatus.NOT_FOUND, "ReleaseMaster not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding ReleaseMaster with ID: {}", id, e);
		} catch (Exception e) {
			log.error("An error occurred while finding ReleaseMaster with ID: {}", id, e);
		}
		return new ReleaseApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while finding ReleaseMaster", false);

	}

	@Override
	public ReleaseApiResponse findAll() {
		try {
			log.info("Finding all ReleaseMasters");
			List<ReleaseMaster> releaseMasters = repository.findAll();
			if (releaseMasters.isEmpty()) {
				log.warn("No ReleaseMasters found in the database");
				return new ReleaseApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No ReleaseMasters found in the database", false);
			} else {
				List<ReleaseDto> releaseDtos = releaseMasters.stream().map(mapper::mapReleaseToReleaseDto)
						.collect(Collectors.toList());
				log.info("ReleaseMasters retrieved successfully");
				return new ReleaseApiResponse(null, releaseDtos, null, HttpStatus.OK,
						"ReleaseMasters retrieved successfully", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while retrieving ReleaseMasters", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving ReleaseMasters", e);
		}
		return new ReleaseApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving ReleaseMasters", false);

	}

	@Override
	public ReleaseApiResponse save(ReleaseDto releaseDto) {
		try {
			log.info("Saving ReleaseMaster");
			ReleaseMaster entity = mapper.mapReleaseDtoToReleaseEntity(releaseDto);
			entity.setIsDeleted(false);
			entity = repository.save(entity);
			ReleaseDto savedDto = mapper.mapReleaseToReleaseDto(entity);
			log.info("ReleaseMaster saved successfully");
			return new ReleaseApiResponse(savedDto, null, null, HttpStatus.CREATED, "ReleaseMaster saved successfully",
					true);
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while saving ReleaseMaster", e);
		} catch (Exception e) {
			log.error("An error occurred while saving ReleaseMaster", e);
		}
		return new ReleaseApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while saving ReleaseMaster", false);

	}

	@Override
	public ReleaseApiResponse deleteById(Integer id) {
		log.info("<<start>>In ReleaseMaster deleteById method<<start>>");
		try {
			log.info("Deleting ReleaseMaster with ID: " + id);
			final Optional<ReleaseMaster> releaseOptional = repository.findById(id);
			log.info("ReleaseMaster deleteReleaseById response: " + releaseOptional);
			if (releaseOptional.isPresent()) {
				ReleaseMaster releaseIdFromDb = releaseOptional.get();
				releaseIdFromDb.setIsDeleted(true);
				repository.save(releaseIdFromDb);
				log.info("Updated ReleaseMaster with ID: " + id + ", active set to false");
				final ReleaseDto releaseDtoToSend = mapper.mapReleaseToReleaseDto(releaseIdFromDb);
				log.info("Sending updated releaseDto response: " + releaseDtoToSend);
				return new ReleaseApiResponse(releaseDtoToSend, null, null, HttpStatus.OK,
						"ReleaseMaster deleted successfully", true);
			} else {
				return new ReleaseApiResponse(null, null, null, HttpStatus.NOT_FOUND, "ReleaseMaster not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while deleting ReleaseMaster", e);
		} catch (final Exception e) {
			log.error("An error occurred while deleting ReleaseMaster", e);
		}
		return new ReleaseApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Override
	public ReleaseApiResponse update(ReleaseDto releaseDto) {
		try {
			log.info("Updating ReleaseMaster with ID: {}", releaseDto.getReleaseId());
			Optional<ReleaseMaster> optionalEntity = repository.findById(releaseDto.getReleaseId());
			if (optionalEntity.isPresent()) {
				ReleaseMaster existingEntity = mapper.mapReleaseDtoToReleaseEntity(releaseDto);
				existingEntity.setIsDeleted(false);
				existingEntity.setReleaseId(optionalEntity.get().getReleaseId());
				existingEntity = repository.save(existingEntity);
				ReleaseDto updatedDto = mapper.mapReleaseToReleaseDto(existingEntity);
				log.info("ReleaseMaster updated successfully with ID: {}", releaseDto.getReleaseId());
				return new ReleaseApiResponse(updatedDto, null, null, HttpStatus.OK,
						"ReleaseMaster updated successfully", true);
			} else {
				log.warn("ReleaseMaster not found with ID: {}", releaseDto.getReleaseId());
				return new ReleaseApiResponse(releaseDto, null, null, HttpStatus.NOT_FOUND, "ReleaseMaster not found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while updating ReleaseMaster", e);
		} catch (Exception e) {
			log.error("An error occurred while updating ReleaseMaster", e);
		}
		return new ReleaseApiResponse(releaseDto, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while updating ReleaseMaster", false);

	}

	@Override
	public ReleaseApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		try {
			log.info("Retrieving ReleaseMasters with pagination");
			int page = pageNum.orElse(0);
			int size = pageSize.orElse(10);
			Pageable pageable = PageRequest.of(page, size);
			Page<ReleaseMaster> releaseMasters = repository.findAllpagination(pageable);
			if (releaseMasters.isEmpty()) {
				log.warn("No ReleaseMasters found in the database for the given page");
				return new ReleaseApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No ReleaseMasters found in the database for the given page", false);
			} else {
				List<ReleaseDto> dtos = releaseMasters.getContent().stream().map(mapper::mapReleaseToReleaseDto)
						.collect(Collectors.toList());
				log.info("ReleaseMasters retrieved successfully with pagination");
				return new ReleaseApiResponse(null, dtos, null, HttpStatus.OK,
						"ReleaseMasters retrieved successfully with pagination", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while retrieving ReleaseMasters with pagination", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving ReleaseMasters with pagination", e);
		}
		return new ReleaseApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving ReleaseMasters with pagination", false);

	}
}