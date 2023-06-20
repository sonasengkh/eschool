package com.iauto.eschool.spec;

import java.time.LocalDate;
import java.util.Set;

import com.iauto.eschool.entity.Gender;
import com.iauto.eschool.entity.Role;

import lombok.Data;

@Data
public class UserFilter {

	private Long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String phonenumber;
	
	private LocalDate dateofbirth;
	
	private Gender gender;
	private String photo;
	
	private Set<Role> roles;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	private String firstName;
	private String lastName;
	
	
    private String verificationCode;
}
