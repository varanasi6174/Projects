package com.sipl.tracker_rest_repo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sipl.tracker_rest_repo.dto.ReleaseDto;
import com.sipl.tracker_rest_repo.dto.response.ReleaseApiResponse;

@Service
public interface ReleaseService {

	ReleaseApiResponse findById(Integer id);

	ReleaseApiResponse findAll();

	ReleaseApiResponse save(ReleaseDto releaseDto);

	ReleaseApiResponse update(ReleaseDto releaseDto);

	ReleaseApiResponse deleteById(Integer id);

	ReleaseApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);

}
