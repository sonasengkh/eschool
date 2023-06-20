package com.iauto.eschool.control;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.entity.Permission;
import com.iauto.eschool.service.PermissionService;

@RestController
@RequestMapping("permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@RolesAllowed(value = "ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Permission permission) {
		Permission permission2 = permissionService.create(permission);
		return ResponseEntity.ok(permission2);
	}
}
