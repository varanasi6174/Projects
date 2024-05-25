package com.jpa.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.hibernate.entity.User;
import com.jpa.hibernate.repository.UserRepository;
import com.jpa.hibernate.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User updateUser(Integer userId, User updatedUser) {
		User existingUser = userRepository.findById(userId).orElse(null);
		if (existingUser != null) {
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setEmail(updatedUser.getEmail());

			return userRepository.save(existingUser);
		}
		return null;
	}

	@Override
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);
	}
}
