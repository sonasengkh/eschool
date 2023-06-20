package com.iauto.eschool.control;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.entity.Role;
import com.iauto.eschool.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RolesAllowed(value = "ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Role role){
		
		return ResponseEntity.ok( roleService.create(role));
	}
	
	@RolesAllowed(value = "ROLE_ADMIN")
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		
		return ResponseEntity.ok( roleService.getById(id) );
	}
	
}
