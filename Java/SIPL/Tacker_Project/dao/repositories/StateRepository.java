package com.sipl.tracker_rest_repo.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.tracker_rest_repo.dao.entities.StateMaster;

@Repository
public interface StateRepository extends JpaRepository<StateMaster, Integer> {

	@Query("from StateMaster s where s.isDeleted=false AND s.isActive=true")
	Page<StateMaster> findAllPagination(Pageable page);

	@Query("from StateMaster s where s.isDeleted=false AND s.isActive=true")
	List<StateMaster> findAllActive();

	@Query("select s from StateMaster s where s.stateId=?1 and s.isDeleted=false AND s.isActive=true")
	Optional<StateMaster> findById(Integer id);
}
