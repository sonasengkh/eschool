package com.iauto.eschool.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phonenumber;
	private LocalDateTime dateofbirth;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String photo;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
}
