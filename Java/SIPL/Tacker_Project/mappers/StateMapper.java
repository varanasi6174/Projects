package com.sipl.tracker_rest_repo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.tracker_rest_repo.dao.entities.StateMaster;
import com.sipl.tracker_rest_repo.dto.StateDto;

@Mapper(componentModel = "spring")
public interface StateMapper {

	StateMaster mapStateDtoToStateEntity(StateDto stateDto);

	StateDto mapStateEntityToStateDto(StateMaster stateMaster);

	List<StateDto> mapStateEntityListToStateDtoList(List<StateMaster> stateMasterList);

	default Page<StateDto> mapStateEntityPageToStateDtoPage(Page<StateMaster> stateMasterPage) {
		return stateMasterPage.map(this::mapStateEntityToStateDto);
	}
}
