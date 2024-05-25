package com.sipl.tracker_rest_repo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dto.LocationDto;
import com.sipl.tracker_rest_repo.dto.response.LocationApiResponse;

@Service
public interface LocationService {
    LocationApiResponse addLocationData(LocationDto locationDto);
    LocationApiResponse getAllLocationData();
    LocationApiResponse updateLocationData(LocationDto locationDto);
    LocationApiResponse getLocationById(Integer id);
    LocationApiResponse deleteLocationById(Integer id);
    LocationApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);
}
