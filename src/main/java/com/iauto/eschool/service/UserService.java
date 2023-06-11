package com.iauto.eschool.service;

import java.util.Optional;

import com.iauto.eschool.config.security.AuthUser;
import com.iauto.eschool.entity.User;

public interface UserService {
	User getById(Long id);
	User getByUsername(String userName);
	
	Optional<AuthUser> findAuthUserByUsername(String username);
	
	User register(User user);
	
	
}
