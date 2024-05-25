package com.sipl.tracker_rest_repo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.sipl.tracker_rest_repo.dao.entities.CountryMaster;
import com.sipl.tracker_rest_repo.dto.CountryDto;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryMaster mapCountryDtoToCountryEntity(CountryDto countryDto);

    CountryDto mapCountryEntityToCountryDto(CountryMaster countryMaster);

    List<CountryDto> mapCountryDtoEntityListToCountryDtoList(List<CountryMaster> countryMasterList);

    default Page<CountryDto> mapCountryEntityPageToCountryDtoPage(Page<CountryMaster> countryMasterPage) {
        return countryMasterPage.map(this::mapCountryEntityToCountryDto);
    }
}
