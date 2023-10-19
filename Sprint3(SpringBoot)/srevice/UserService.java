package com.librarymanagement.srevice;

import com.librarymanagement.entity.User;

public interface UserService {
	//method for saving User details in db table
	User addUser(User user);
	
	//method to fetch User detail based on uid from db table
	User getUserDetails(int uid);
	
	//method to modify User detail based on uid from db table
	User updateUserDetails(User user, int uid);
	
	//method to remove User detail based on uid from db table
	void deleteUserDetail(int uid);

}
