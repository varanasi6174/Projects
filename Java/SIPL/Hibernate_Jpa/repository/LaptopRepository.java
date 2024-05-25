package com.jpa.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.hibernate.entity.Laptop; 

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
   
}

