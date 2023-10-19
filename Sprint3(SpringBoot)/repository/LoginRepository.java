package com.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.entity.Login;


public interface LoginRepository extends JpaRepository<Login, Integer>{
	public Login findByUserNameAndPassword(String userName, String password);

}
