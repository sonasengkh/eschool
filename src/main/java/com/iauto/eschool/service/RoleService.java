package com.iauto.eschool.service;

import java.util.Set;

import com.iauto.eschool.entity.Role;

public interface RoleService {
	Role create(Role role);
	Role getById(Long id);
	
	//Set<Long> getIdsRole(Set<Role> roles);
	
	
}
