package com.sipl.tracker_rest_repo.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.tracker_rest_repo.dao.entities.CountryMaster;

@Repository
public interface CountryRepository extends JpaRepository<CountryMaster, Integer> {

	@Query("from CountryMaster c where c.isDeleted=false  AND c.isActive=true")
	Page<CountryMaster> findAllpagination(Pageable page);

	@Query("from CountryMaster c where c.isDeleted=false AND c.isActive=true")
	List<CountryMaster> findAllActive();

	@Query("select c from CountryMaster c where c.countryId=?1 and c.isDeleted=false  AND c.isActive=true")
	Optional<CountryMaster> findByCountryId(Integer id);
}
