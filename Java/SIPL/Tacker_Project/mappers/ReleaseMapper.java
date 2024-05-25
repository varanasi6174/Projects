package com.sipl.tracker_rest_repo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.tracker_rest_repo.dao.entities.ReleaseMaster;
import com.sipl.tracker_rest_repo.dto.ReleaseDto;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

	ReleaseMaster mapReleaseDtoToReleaseEntity(ReleaseDto releaseDto);

	ReleaseDto mapReleaseToReleaseDto(ReleaseMaster releaseMaster);

	List<ReleaseDto> mapReleaseEntityListToReleaseDtoList(List<ReleaseMaster> releaseMasters);

	default Page<ReleaseDto> mapReleaseEntityPageToReleaseDtoPage(Page<ReleaseMaster> releaseMasters) {
		return releaseMasters.map(this::mapReleaseToReleaseDto);
	}
}
