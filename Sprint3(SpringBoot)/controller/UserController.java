package com.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.entity.User;
import com.librarymanagement.srevice.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService us;
	
	//use postmapping to insert data
		@PostMapping("/addUser")
		public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
			return new ResponseEntity<User>(us.addUser(user),HttpStatus.CREATED);
		}
		//use getmapping to fetch data from db table
		@GetMapping("/getUser/{uid}")
		public ResponseEntity<User> getuser(@PathVariable("uid") int uid){
		return new ResponseEntity<User>(us.getUserDetails(uid),HttpStatus.OK);
		}
		
		//use getmapping to remove existing data from db table
		@PutMapping("/editUser/{uid}")
		public ResponseEntity<User> editUser(@Valid @PathVariable("uid") int uid, @RequestBody User user){
		return new ResponseEntity<User>(us.updateUserDetails(user, uid),HttpStatus.OK);
		}
		@DeleteMapping("/deleteUser/{uid}")
		public ResponseEntity<String> deleteUser(@PathVariable("uid") int uid){
			us.deleteUserDetail(uid);
		return new ResponseEntity<String>("Deleted successfully....",HttpStatus.OK);
		
		}
}
