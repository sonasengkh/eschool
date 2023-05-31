package com.iauto.eschool.service;

import com.iauto.eschool.entity.Role;

public interface RoleService {
	Role create(Role role);
	Role getById(Long id);
	
}
