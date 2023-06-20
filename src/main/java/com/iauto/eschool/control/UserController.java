package com.iauto.eschool.control;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.dto.PageDTO;
import com.iauto.eschool.dto.UserDTO;
import com.iauto.eschool.entity.User;
import com.iauto.eschool.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RolesAllowed(value = "ROLE_ADMIN")
	@GetMapping("{username}")
	public ResponseEntity<?> getByUsername(@PathVariable("username") String username) {
		System.out.println("my test username = "+ username);
		User user = userService.getByUsername(username);
		return ResponseEntity.ok(user);
	}
	
	@RolesAllowed(value = "ROLE_ADMIN")
	@PutMapping("{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDto){
		User user = userService.toUser(userDto);
		User updateUser = userService.updateUser(user, id);
		
		
		return ResponseEntity.ok( userService.toUserDtoPlus(updateUser) );
	}
	
	/*
	@PostMapping
	public ResponseEntity<?> register(@RequestBody User user){
		User userRegister = userService.register(user);
		return ResponseEntity.ok(userRegister);
	}
	*/
	/*
	@PostMapping
    public ResponseEntity<?> processRegister(@RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
		userService.register_email(user, getSiteURL(request));
		
		Map<String, String> respMessage = new HashMap<>();
		respMessage.put("respMessage", "Success");
        return ResponseEntity.ok(respMessage);
    }
     
	*/

	@PostMapping
    public ResponseEntity<?> processRegister(@RequestBody UserDTO userDto, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
		
		User user = userService.toUser(userDto);
		userService.register_email(user, getSiteURL(request));
		
		Map<String, String> respMessage = new HashMap<>();
		respMessage.put("respMessage", "Success");
        return ResponseEntity.ok(respMessage);
    }
     
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        System.out.println("siteUrl:"+siteURL);
        System.out.println("request_getServletPath:"+ request.getServletPath());
        return siteURL.replace(request.getServletPath(), "");
    } 
	
    @RolesAllowed(value = "ROLE_ADMIN")
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		
		 User user = userService.getById(id);
		 UserDTO userDtoPlus = userService.toUserDtoPlus(user);
		 return ResponseEntity.ok(userDtoPlus);
	}
	
	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
	    if (userService.verify(code)) {
	        return "verify_success";
	    } else {
	        return "verify_fail";
	    }
	}
	
	
	@RolesAllowed(value = {"ROLE_ADMIN","ROLE_AUTHOR"})
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if (username == null){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		System.out.println("profile:"+ username);
		User user = userService.getByUsername(username);
		UserDTO userDtoPlus = userService.toUserDtoPlus(user);
		
		return ResponseEntity.ok(userDtoPlus);
	}
	
	@RolesAllowed(value = {"ROLE_ADMIN"})
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam Map<String, String> params, Authentication authentication){
		
		System.out.println("my username: " + authentication.getName());
		
		Page<User> userPage = userService.getUser(params);
		PageDTO pageDTO = new PageDTO(userPage);
		
		
		
		return ResponseEntity.ok(pageDTO);
	}
	
}
