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
	public User getUserDetails(int uid) {
		return urepo.findById(uid).
			       orElseThrow(()-> new UserIdNotFoundException("User Id is not correct"));	}

	@Override
	public User updateUserDetails(User user, int uid) {
		User updatedUser = urepo.findById(uid).
		         orElseThrow(()-> new UserIdNotFoundException("User Id is not correct"));

		//set new values
		updatedUser.setUphone(user.getUphone());
		
		urepo.save(updatedUser); //saving updated details 
			return updatedUser; 
	}

	@Override
	public void deleteUserDetail(int uid) {
		urepo.findById(uid).
		orElseThrow(()-> new UserIdNotFoundException("User Id is not correct"));
	    urepo.deleteById(uid);		
	}

	
	
		

}
