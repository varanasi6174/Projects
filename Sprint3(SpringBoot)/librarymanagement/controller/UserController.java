package com.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.entity.User;
import com.librarymanagement.srevice.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserService us;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
		return new ResponseEntity<User>(us.addUser(user),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/getstudents/{uid}")
	public ResponseEntity<User> getUser(@PathVariable("uid") int uid){
		return new ResponseEntity<User>(us.getUserDetail(uid),HttpStatus.OK);
	}
}
