package com.sipl.tracker_rest_repo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dto.CountryDto;
import com.sipl.tracker_rest_repo.dto.response.CountryApiResponse;

@Service
public interface CountryService {
	CountryApiResponse addCountryData(CountryDto countryDto);
	CountryApiResponse getAllCountryData();
	CountryApiResponse updateCountryData(CountryDto countryDto);
	CountryApiResponse getCountryById(Integer id);
	CountryApiResponse deleteCountryById(Integer id);
	CountryApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);
}
