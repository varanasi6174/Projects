package com.librarymanagement.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.entity.Login;
import com.librarymanagement.srevice.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
@RestController
public class LoginController {
	
	@Autowired
	LoginService ls;
	
	@PostMapping("/Login")
	public String validate(@RequestBody Login login) throws ServletException{
		String jwtToken ="";
		
		if(login.getUserName()==null || login.getPassword()==null) {
			throw new ServletException("Please fill username & password");
		}
		
		String userName = login.getUserName();
		String password = login.getPassword();
		login = ls.loginUser(userName, password);
		
		if(login==null) {
			throw new ServletException("login user not found");
		}
		
		jwtToken = Jwts.builder().setSubject(userName).claim("roles", "login").setIssuedAt(new Date())
		.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

	return jwtToken;
		
	}
}