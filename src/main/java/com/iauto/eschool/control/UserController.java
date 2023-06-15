package com.iauto.eschool.control;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	
	/*
	@PostMapping
	public ResponseEntity<?> register(@RequestBody User user){
		User userRegister = userService.register(user);
		return ResponseEntity.ok(userRegister);
	}
	*/
	
	@PostMapping
    public String processRegister(@RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
		userService.register_email(user, getSiteURL(request));       
        return "register_success";
    }
     
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    } 
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		
		 User user = userService.getById(id);
		 return ResponseEntity.ok(user);
	}
	
	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
	    if (userService.verify(code)) {
	        return "verify_success";
	    } else {
	        return "verify_fail";
	    }
	}
	
}
