package com.sipl.vehicle.management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.vehicle.management.entity.RfidTagMaster;
import com.sipl.vehicle.management.entity.TagTypeMaster;
import com.sipl.vehicle.management.exception.RfidTagMasterApiResponse;
import com.sipl.vehicle.management.exception.RfidTagMasterPageApiResponse;
import com.sipl.vehicle.management.mapper.RfidTagMasterMapper;
import com.sipl.vehicle.management.repository.RfidTagMasterRepository;
import com.sipl.vehicle.management.vehicleDTO.RfidTagMasterDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RfidTagMasterService {

    @Value("${rfid.tag.single}")
    private String singleTag;

    @Value("${rfid.tag.range}")
    private String rangeTag;

    @Value("${rfid.tag.pattern}")
    private String pattern;

    @Autowired
    private RfidTagMasterRepository rfidTagMasterRepository;

    @Autowired
    private RfidTagMasterMapper rfidTagMasterMapper;
     

    public RfidTagMasterApiResponse addRfidTag(RfidTagMasterDto rfidTagMasterDto) {
        try {
            RfidTagMaster rfidTagDetailsToSave = null;
            String tagPattern = null;
            if (rfidTagMasterDto != null) {
                List<RfidTagMaster> tagIdFetchedFromDb = rfidTagMasterRepository.findByTagId(rfidTagMasterDto.getRfidTagId());
                log.info("tagIdFetchedFromDb :" + tagIdFetchedFromDb);
                if (tagIdFetchedFromDb == null || tagIdFetchedFromDb.isEmpty()) {
                    if (rfidTagMasterDto.getRfidTagId() != null && rfidTagMasterDto.getRfidTagId().contains(singleTag)) {
                        RfidTagMaster rfidTagToMap = rfidTagMasterMapper.mapRfidTagMasterDtoToRfidTagMaster(rfidTagMasterDto);
                        TagTypeMaster tagTypeMaster = new TagTypeMaster();
                        tagTypeMaster.setId(rfidTagMasterDto.getTagTypeMasterDto().getId());
                        rfidTagToMap.setTagTypeMaster(tagTypeMaster);
                        rfidTagToMap.setRfidTagId(rfidTagMasterDto.getRfidTag());
                        rfidTagToMap.getAuditEntity().setCreatedDate(LocalDateTime.now());
                        rfidTagDetailsToSave = rfidTagMasterRepository.save(rfidTagToMap);
                        return new RfidTagMasterApiResponse(
                                rfidTagMasterMapper.mapRfidTagMasterToRfidTagMasterDto(rfidTagDetailsToSave),
                                HttpStatus.OK, "Tag Details Added Successfully", false);
                    } else {
                    	int startDigit = rfidTagMasterDto.getStart() != null ? rfidTagMasterDto.getStart().intValue() : 0;
                    	//int startDigit = 1000;
                    	log.info("startDigit is " + startDigit);
                    	int endDigit = rfidTagMasterDto.getEnd() != null ? rfidTagMasterDto.getEnd().intValue() : 0;
                    	//int endDigit = 30;
                    	log.info("endDigit is " + endDigit);

                        for (int startDigits = startDigit; startDigits <= endDigit; startDigits++) {
                            try {
                                tagPattern = pattern + startDigits;
                                log.info("tagPattern " + tagPattern);
                                RfidTagMaster rfidTagToMap = rfidTagMasterMapper
                                        .mapRfidTagMasterDtoToRfidTagMaster(rfidTagMasterDto);
                                rfidTagToMap.getAuditEntity().setCreatedDate(LocalDateTime.now());
                                rfidTagToMap.setIsActive(true);
                                rfidTagToMap.setRfidTagId(tagPattern);
                                rfidTagDetailsToSave = rfidTagMasterRepository.save(rfidTagToMap);
                            } catch (DataIntegrityViolationException ex) {
                                log.error("DataIntegrityViolationException for tag id " + tagPattern);
                                log.error("DataIntegrityViolationException ", ex);
                            }
                        }
                        return new RfidTagMasterApiResponse(
                                rfidTagMasterMapper.mapRfidTagMasterToRfidTagMasterDto(rfidTagDetailsToSave),
                                HttpStatus.OK, "Tag Details Added Successfully", false);
                    }
                } else {
                    return new RfidTagMasterApiResponse(null, HttpStatus.ALREADY_REPORTED, "Tag Details already exists",
                            false);
                }
            }
        } catch (final Exception e) {
            log.error("Database connectivity error", e);
        }
        return new RfidTagMasterApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
    }

	public RfidTagMasterPageApiResponse getAllByPagination(Optional<Integer> page, Optional<Integer> size) {
		try {
			log.info("inside getAllPagination service");
			final Page<RfidTagMaster> rfidTagMasterList = rfidTagMasterRepository
					.findAll(PageRequest.of(page.orElse(0), size.orElse(5)));
			final Page<RfidTagMasterDto> rfidTagMasterListDto = rfidTagMasterMapper
					.mapRfidTagMasterToRfidTagMasterDto(rfidTagMasterList);
			if (null != rfidTagMasterListDto) {
				return new RfidTagMasterPageApiResponse(rfidTagMasterListDto, HttpStatus.FOUND, "RfidTag list found",
						false);
			} else {
				return new RfidTagMasterPageApiResponse(null, HttpStatus.NOT_FOUND, "RfidTag list Not found", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("database connectivity error", e);
			return new RfidTagMasterPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}
	}

	public RfidTagMasterPageApiResponse findPageByTagId(Integer id, Optional<String> tagId, Optional<Integer> page,
			Optional<Integer> size) {
		log.info("inside findPageByTagId service");
		try {
			if (tagId.isPresent()) {
				final Page<RfidTagMaster> rfidTagMasterList = rfidTagMasterRepository.findPageByTypeAndTagId(id,
						tagId.orElse(null), PageRequest.of(page.orElse(0), size.orElse(5)));
				final Page<RfidTagMasterDto> rfidTagMasterListDto = rfidTagMasterMapper
						.mapRfidTagMasterToRfidTagMasterDto(rfidTagMasterList);
				return new RfidTagMasterPageApiResponse(rfidTagMasterListDto, HttpStatus.FOUND, "RfidTag list found",
						false);
			} else {
				final Page<RfidTagMaster> rfidTagMasterList = rfidTagMasterRepository.findPageByTypeOrTagId(id,
						tagId.orElse(null), PageRequest.of(page.orElse(0), size.orElse(5)));
				final Page<RfidTagMasterDto> rfidTagMasterListDto = rfidTagMasterMapper
						.mapRfidTagMasterToRfidTagMasterDto(rfidTagMasterList);
				return new RfidTagMasterPageApiResponse(rfidTagMasterListDto, HttpStatus.FOUND, "RfidTag list found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("database connectivity error", e);
			return new RfidTagMasterPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}
	}

	public RfidTagMasterApiResponse deleteById(Integer id) {
		log.info("inside deleteById service");
		try {
			Optional<RfidTagMaster> rfidTagMasterlistfromDb = rfidTagMasterRepository.findById(id);
			if (rfidTagMasterlistfromDb.isPresent()) {
				RfidTagMaster rfidTagMasterObj = rfidTagMasterlistfromDb.get();
				rfidTagMasterObj.setIsActive(false);
				rfidTagMasterRepository.save(rfidTagMasterObj);
				RfidTagMasterDto rfidTagMasterDto = rfidTagMasterMapper
						.mapRfidTagMasterToRfidTagMasterDto(rfidTagMasterObj);
				return new RfidTagMasterApiResponse(rfidTagMasterDto, HttpStatus.OK, "RfidTagId deleted successfully",
						true);
			} else {
				return new RfidTagMasterApiResponse(null, HttpStatus.NOT_FOUND, "RfidTagId doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("RfidTagMaster deleteById JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("RfidTagMaster deleteById Exception: ", e);
		}
		return new RfidTagMasterApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	public RfidTagMasterApiResponse updateRfidTag(RfidTagMasterDto rfidTagMasterDto) {
	    log.info("inside updateRfidTag service");
	    try {
	        Optional<RfidTagMaster> rfidTagMasterFetchedFromDb = rfidTagMasterRepository.findById(rfidTagMasterDto.getId());
	        if (rfidTagMasterFetchedFromDb.isPresent()) {
	            RfidTagMaster rfidTagMasterObj = rfidTagMasterFetchedFromDb.get();
	            rfidTagMasterObj.getAuditEntity().setCreatedDate(LocalDateTime.now());
	            rfidTagMasterObj.getAuditEntity().setModifiedDate(LocalDateTime.now());
	            TagTypeMaster tagTypeMaster = new TagTypeMaster();
	            tagTypeMaster.setId(rfidTagMasterDto.getTagTypeMasterDto().getId()); 
	            rfidTagMasterObj.setTagTypeMaster(tagTypeMaster);
	            rfidTagMasterObj.setIsActive(rfidTagMasterDto.getIsActive());
	            final RfidTagMaster rfidTagMasterUpdated = rfidTagMasterRepository.save(rfidTagMasterObj);
	            return new RfidTagMasterApiResponse(
	                    rfidTagMasterMapper.mapRfidTagMasterToRfidTagMasterDto(rfidTagMasterUpdated), HttpStatus.OK,
	                    "RfidTagId Updated successfully", true);
	        } else {
	            log.error("RfidTag Id doesn't exist");
	            return new RfidTagMasterApiResponse(null, HttpStatus.NOT_FOUND, "RfidTag Id doesn't exist", true);
	        }
	    } catch (final org.hibernate.exception.JDBCConnectionException e) {
	        log.error("RfidTagMaster updateRfidTag JDBCConnectionException:", e);
	    } catch (final Exception e) {
	        log.error("RfidTagMaster updateRfidTag Exception: ", e);
	    }
	    return new RfidTagMasterApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}


	public RfidTagMasterApiResponse findActiveRfidTag(String rfidTagNumber) {
		log.info("inside findActiveRfidTag service");
		try {
			Optional<RfidTagMaster> rfidTagMasterFatchedFromdb = rfidTagMasterRepository
					.getRfidMasterBasedOnRfidTagNum(rfidTagNumber);
			if (rfidTagMasterFatchedFromdb.isPresent()) {
				log.info("Rfid Tag Details is " + rfidTagMasterFatchedFromdb.get());
				RfidTagMaster rfidDetailsToSend = rfidTagMasterFatchedFromdb.get();
				return new RfidTagMasterApiResponse(
						rfidTagMasterMapper.mapRfidTagMasterToRfidTagMasterDto(rfidDetailsToSend), HttpStatus.OK,
						"RfidTagId Found successfully", false);
			} else {
				log.error("RfidTag  doesn't exists");
				return new RfidTagMasterApiResponse(null, HttpStatus.NOT_FOUND, "RfidTag  doesn't exists", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("RfidTagMaster findActiveRfidTag JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("RfidTagMaster findActiveRfidTag Exception: ", e);
		}
		return new RfidTagMasterApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	public RfidTagMasterPageApiResponse getActiveTags(Optional<Integer> page, Optional<Integer> size) {
		try {
			log.info("Inside getActiveTags service");
			final Page<RfidTagMaster> rfidTagMasterList = rfidTagMasterRepository
					.findActiveTags(PageRequest.of(page.orElse(0), size.orElse(5)));
			final Page<RfidTagMasterDto> rfidTagMasterListDto = rfidTagMasterMapper
					.mapRfidTagMasterToRfidTagMasterDto(rfidTagMasterList);
			if (null != rfidTagMasterList) {
				return new RfidTagMasterPageApiResponse(rfidTagMasterListDto, HttpStatus.FOUND, "RfidTag list found",
						false);
			} else {
				return new RfidTagMasterPageApiResponse(null, HttpStatus.NOT_FOUND, "RfidTag list Not found", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Database connectivity error", e);
			return new RfidTagMasterPageApiResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
		}
	}
}
