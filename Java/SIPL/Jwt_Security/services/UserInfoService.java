package com.example.jwt.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.entities.UserInfo;
import com.example.jwt.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {
  
    @Autowired
    private UserInfoRepository repository; // Repository for managing UserInfo entities

    @Autowired
    private PasswordEncoder encoder; // Password encoder for encoding user passwords

    // Load user by username for Spring Security authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user details by username in the repository
        Optional<UserInfo> userDetail = repository.findByName(username);

        // Convert userDetail to UserDetails or throw UsernameNotFoundException if user not found
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    // Method to add a new user
    public String addUser(UserInfo userInfo) {
        // Encode user's password before saving to the database
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        // Save user info to the repository
        repository.save(userInfo);
        return "User Added Successfully"; // Return success message
    }
}
