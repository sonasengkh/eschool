package com.iauto.eschool.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
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
public class User extends AuditEntity{
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
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
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	private String firstName;
	private String lastName;
	
	
}
