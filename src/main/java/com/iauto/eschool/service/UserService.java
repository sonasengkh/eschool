package com.iauto.eschool.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;

import com.iauto.eschool.config.security.AuthUser;
import com.iauto.eschool.dto.UserDTO;
import com.iauto.eschool.entity.User;

public interface UserService {
	User getById(Long id);
	User getByUsername(String userName);
	
	Optional<AuthUser> findAuthUserByUsername(String username);
	
	//User register(User user);
	
	void register_email(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
	
	public boolean verify(String verificationCode);
	
	public UserDTO toUserDtoPlus(User user) ;
	public User toUser(UserDTO userDto) ;
}
