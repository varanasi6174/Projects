package com.sipl.tracker_rest_repo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.tracker_rest_repo.dao.entities.LocationMaster;
import com.sipl.tracker_rest_repo.dto.LocationDto;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMaster mapLocationDtoToLocationEntity(LocationDto locationDto);

    LocationDto mapLocationEntityToLocationDto(LocationMaster locationMaster);

    List<LocationDto> mapLocationEntityListToLocationDtoList(List<LocationMaster> locationMasterList);

    default Page<LocationDto> mapLocationEntityPageToLocationDtoPage(Page<LocationMaster> locationMasterPage) {
        return locationMasterPage.map(this::mapLocationEntityToLocationDto);
    }
}
