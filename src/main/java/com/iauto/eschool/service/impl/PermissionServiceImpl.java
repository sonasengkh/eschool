package com.iauto.eschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.Permission;
import com.iauto.eschool.repository.PermissionRepository;
import com.iauto.eschool.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public Permission create(Permission permission) {
		return permissionRepository.save(permission);
	}

	
}
