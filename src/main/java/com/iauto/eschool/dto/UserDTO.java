package com.iauto.eschool.dto;

import java.time.LocalDate;
import java.util.Set;

import com.iauto.eschool.entity.Gender;

import lombok.Data;

@Data
public class UserDTO {

	private String username;
	private String password;
	private String email;
	private String phonenumber;
	private LocalDate dateofbirth;
	private Gender gender;
	private String photo;
	
	private Set<Long> roleId;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private String firstName;
	private String lastName;

}
