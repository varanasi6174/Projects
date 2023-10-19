package com.librarymanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.entity.Login;
import com.librarymanagement.repository.LoginRepository;
import com.librarymanagement.srevice.LoginService;


@Service
public class LoginServiceImpl implements LoginService{
	

	@Autowired
	LoginRepository lrepo;

	@Override
	public Login loginUser(String userName, String password) {
	Login login = lrepo.findByUserNameAndPassword(userName, password);	
		return login;
	}
}