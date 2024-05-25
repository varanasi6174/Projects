package com.example.jwt.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.jwt.entities.UserInfo;

// UserDetails implementation for UserInfo entity
public class UserInfoDetails implements UserDetails {

    private String name; // User's name
    private String password; // User's password
    private List<GrantedAuthority> authorities; // User's authorities/roles

    // Constructor taking a UserInfo object
    public UserInfoDetails(UserInfo userInfo) {
        name = userInfo.getName();
        password = userInfo.getPassword();
        // Splitting roles string and converting them into GrantedAuthority objects
        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Return user's authorities/roles
    }

    @Override
    public String getPassword() {
        return password; // Return user's password
    }

    @Override
    public String getUsername() {
        return name; // Return user's name
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Indicate that user's account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Indicate that user's account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Indicate that user's credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Indicate that user's account is enabled
    }
}
