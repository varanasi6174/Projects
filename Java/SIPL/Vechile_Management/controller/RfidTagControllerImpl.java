package com.sipl.vehicle.management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sipl.vehicle.management.exception.RfidTagMasterApiResponse;
import com.sipl.vehicle.management.exception.RfidTagMasterPageApiResponse;
import com.sipl.vehicle.management.service.RfidTagMasterService;
import com.sipl.vehicle.management.vehicleDTO.RfidTagMasterDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RfidTagControllerImpl implements RfidTagController {

    @Autowired
    private RfidTagMasterService rfidTagMasterService;

    @Override
    public ResponseEntity<RfidTagMasterApiResponse> addRfidTag(RfidTagMasterDto rfidTagMasterDto) {
        log.info("Adding RFID tag: {}", rfidTagMasterDto);
        ResponseEntity<RfidTagMasterApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.addRfidTag(rfidTagMasterDto), HttpStatus.OK);
        log.info("End of addRfidTag method");
        return responseEntity;
    }

    @Override
    public ResponseEntity<RfidTagMasterPageApiResponse> getAllByPagination(Optional<Integer> page, Optional<Integer> size) {
        log.info("Getting all RFID tags by pagination with page number {} and page size {}", page.orElse(0), size.orElse(0));
        ResponseEntity<RfidTagMasterPageApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.getAllByPagination(page, size), HttpStatus.OK);
        log.info("End of getAllByPagination method");
        return responseEntity;
    }

    @Override
    public ResponseEntity<RfidTagMasterPageApiResponse> findPageByTagId(Integer id, Optional<String> tagId, Optional<Integer> page, Optional<Integer> size) {
        log.info("Finding RFID tags by tag ID: {}, page number {}, page size {}", id, page.orElse(0), size.orElse(0));
        ResponseEntity<RfidTagMasterPageApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.findPageByTagId(id, tagId, page, size), HttpStatus.OK);
        log.info("End of findPageByTagId method");
        return responseEntity;
    }

    @Override
    public ResponseEntity<RfidTagMasterApiResponse> deleteById(Integer id) {
        log.info("Deleting RFID tag by ID: {}", id);
        ResponseEntity<RfidTagMasterApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.deleteById(id), HttpStatus.OK);
        log.info("End of deleteById method");
        return responseEntity;
    }

    @Override
    public ResponseEntity<RfidTagMasterApiResponse> updateRfidTag(RfidTagMasterDto rfidTagMasterDto) {
        log.info("Updating RFID tag: {}", rfidTagMasterDto);
        ResponseEntity<RfidTagMasterApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.updateRfidTag(rfidTagMasterDto), HttpStatus.OK);
        log.info("End of updateRfidTag method");
        return responseEntity;
    }

    @Override
    public ResponseEntity<RfidTagMasterApiResponse> findActiveRfidTag(String rfidTagNumber) {
        log.info("Finding active RFID tag by tag number: {}", rfidTagNumber);
        ResponseEntity<RfidTagMasterApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.findActiveRfidTag(rfidTagNumber), HttpStatus.OK);
        log.info("End of findActiveRfidTag method");
        return responseEntity;
    }

    @Override
    public ResponseEntity<RfidTagMasterPageApiResponse> getActiveTags(Optional<Integer> page, Optional<Integer> size) {
        log.info("Getting active RFID tags by pagination with page number {} and page size {}", page.orElse(0), size.orElse(0));
        ResponseEntity<RfidTagMasterPageApiResponse> responseEntity = new ResponseEntity<>(rfidTagMasterService.getActiveTags(page, size), HttpStatus.OK);
        log.info("End of getActiveTags method");
        return responseEntity;
    }
}