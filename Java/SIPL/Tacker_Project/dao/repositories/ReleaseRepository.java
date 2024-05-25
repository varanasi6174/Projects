package com.sipl.tracker_rest_repo.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.tracker_rest_repo.dao.entities.ReleaseMaster;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseMaster, Integer> {

	@Query("from ReleaseMaster r where r.isDeleted=false")
	Page<ReleaseMaster> findAllpagination(Pageable page);

	@Query("from ReleaseMaster r where r.isDeleted=false ORDER BY r.releaseId DESC")
	List<ReleaseMaster> findAll();

	@Query("select r from ReleaseMaster r where r.releaseId=?1 and r.isDeleted =false")
	Optional<ReleaseMaster> findById(Integer id);

}
