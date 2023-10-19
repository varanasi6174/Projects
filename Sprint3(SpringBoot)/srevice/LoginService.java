package com.librarymanagement.srevice;

import com.librarymanagement.entity.Login;

public interface LoginService {
	Login loginUser(String userName, String password);
}
