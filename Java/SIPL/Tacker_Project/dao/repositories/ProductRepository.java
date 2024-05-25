package com.sipl.tracker_rest_repo.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.tracker_rest_repo.dao.entities.ProductMaster;

@Repository
public interface ProductRepository extends JpaRepository<ProductMaster, Integer> {

	@Query("from ProductMaster p where p.isActive = true")
	Page<ProductMaster> findAllPagination(Pageable page);

	@Query("from ProductMaster p where p.isActive = true")
	List<ProductMaster> findAllActive();

	@Query("select p from ProductMaster p where p.productId = ?1 and p.isActive = true")
	Optional<ProductMaster> findById(Integer id);
}
