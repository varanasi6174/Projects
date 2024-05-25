package com.example.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.entities.AuthRequest;
import com.example.jwt.entities.UserInfo;
import com.example.jwt.services.JwtService;
import com.example.jwt.services.UserInfoService;

@RestController
@RequestMapping("/auth") // Base mapping for all endpoints in this controller
public class UserController {

    @Autowired
    private UserInfoService service; // Service for managing user information

    @Autowired
    private JwtService jwtService; // Service for generating JWT tokens

    @Autowired
    private AuthenticationManager authenticationManager; // Spring Security authentication manager

    // Endpoint accessible without authentication
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure"; // Return a welcome message
    }

    // Endpoint for adding a new user
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo); // Return a message indicating the result of adding a new user
    }

    // Endpoint accessible only to users with ROLE_USER authority
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile"; // Return a message indicating user profile access
    }

    // Endpoint accessible only to users with ROLE_ADMIN authority
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile"; // Return a message indicating admin profile access
    }

    // Endpoint for authenticating user and generating JWT token
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        // Authenticate user credentials using Spring Security authentication manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        // If authentication is successful, generate and return a JWT token
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            // If authentication fails, throw an exception indicating invalid user request
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
