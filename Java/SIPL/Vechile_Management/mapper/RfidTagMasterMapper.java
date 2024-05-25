package com.sipl.vehicle.management.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.vehicle.management.entity.RfidTagMaster;
import com.sipl.vehicle.management.vehicleDTO.RfidTagMasterDto;

@Mapper(componentModel = "spring")
public interface RfidTagMasterMapper {
    
    RfidTagMasterDto mapRfidTagMasterToRfidTagMasterDto(RfidTagMaster rfidTagMaster);

   
    RfidTagMaster mapRfidTagMasterDtoToRfidTagMaster(RfidTagMasterDto rfidTagMasterDto);
    
    default Page<RfidTagMasterDto> mapRfidTagMasterToRfidTagMasterDto(
		    Page<RfidTagMaster> RfidTagMasterFetchedFromDb) {
		return RfidTagMasterFetchedFromDb.map(this::mapRfidTagMasterToRfidTagMasterDto);
	    }
}


