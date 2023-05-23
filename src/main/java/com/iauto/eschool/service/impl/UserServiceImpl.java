package com.iauto.eschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.User;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.UserRepository;
import com.iauto.eschool.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getById(Long id) {
		return userRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("user", id));
		
	}

	

}
