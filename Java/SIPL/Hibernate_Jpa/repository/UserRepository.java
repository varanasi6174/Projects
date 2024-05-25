package com.jpa.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.hibernate.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
