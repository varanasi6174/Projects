package com.jpa.hibernate.service;

import com.jpa.hibernate.entity.User;

public interface UserService {

	User getUserById(Integer userId);

	User createUser(User user);

	User updateUser(Integer userId, User updatedUser);

	void deleteUserById(Integer userId);

}
