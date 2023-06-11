package com.iauto.eschool.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.entity.User;
import com.iauto.eschool.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("{username}")
	public ResponseEntity<?> getByUsername(@PathVariable("username") String username) {
		System.out.println("my test username = "+ username);
		User user = userService.getByUsername(username);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody User user){
		User userRegister = userService.register(user);
		return ResponseEntity.ok(userRegister);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		
		 User user = userService.getById(id);
		 return ResponseEntity.ok(user);
	}
	
	
}
