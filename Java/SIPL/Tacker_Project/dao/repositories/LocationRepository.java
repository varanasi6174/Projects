package com.sipl.tracker_rest_repo.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.tracker_rest_repo.dao.entities.LocationMaster;

@Repository
public interface LocationRepository extends JpaRepository<LocationMaster, Integer> {

	@Query("from LocationMaster l where l.isDeleted=false AND l.isActive=true")
	Page<LocationMaster> findAllpagination(Pageable page);

	@Query("from LocationMaster l where l.isDeleted=false AND l.isActive=true")
	List<LocationMaster> findAllActive();

	@Query("select l from LocationMaster l where l.locationId=?1 and l.isDeleted=false AND l.isActive=true")
	Optional<LocationMaster> findById(Integer id);
}
