package com.librarymanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.entity.User;
import com.librarymanagement.exception.UserIdNotFoundException;
import com.librarymanagement.repository.UserRepository;
import com.librarymanagement.srevice.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository urepo;
	
	@Override
	public User addUser(User user) {
		return urepo.save(user);
	}

	@Override
	public User getUserDetail(int uid) {
		return urepo.findById(uid).orElseThrow(()-> new UserIdNotFoundException("User id is incorrect"));
	}

	
}
