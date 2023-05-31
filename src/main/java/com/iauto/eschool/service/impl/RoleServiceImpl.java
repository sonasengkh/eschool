package com.iauto.eschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.Role;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.RoleRepository;
import com.iauto.eschool.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role create(Role role) {
		
		return roleRepository.save(role);
	}

	@Override
	public Role getById(Long id) {
		
		return roleRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("role", id));
	}

}
