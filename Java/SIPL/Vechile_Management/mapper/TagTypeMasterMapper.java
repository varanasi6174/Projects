package com.sipl.vehicle.management.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.vehicle.management.entity.TagTypeMaster;
import com.sipl.vehicle.management.vehicleDTO.TagTypeMasterDto;

@Mapper(componentModel = "spring")
public interface TagTypeMasterMapper { 
   
    TagTypeMasterDto mapTagTypeMasterToTagTypeMasterDto(TagTypeMaster tagTypeMaster); 

    TagTypeMaster mapTagTypeMasterDtoToTagTypeMaster(TagTypeMasterDto tagTypeMasterDto); 
    
    default Page<TagTypeMasterDto> mapTagTypeMasterToTagTypeMasterDto(
		    Page<TagTypeMaster> tagTypeMasterFetchedFromDb) { 
		return tagTypeMasterFetchedFromDb.map(this::mapTagTypeMasterToTagTypeMasterDto);
	}
}
