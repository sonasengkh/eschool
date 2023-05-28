package com.iauto.eschool.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.iauto.eschool.config.security.AuthUser;
import com.iauto.eschool.entity.Role;
import com.iauto.eschool.entity.User;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.UserRepository;
import com.iauto.eschool.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getById(Long id) {
		return userRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("user id", 0));
		
	}

	@Override
	public User getByUsername(String userName) {
		User user = userRepository.findByUsername(userName).stream().findFirst()
			.orElseThrow(()-> new ResourceNotFoundException("username " + userName, 0));
		return user;
	}

	@Override
	public Optional<AuthUser> findAuthUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(()-> new ResourceNotFoundException("user "+ username, 0));
		
		AuthUser authUser = AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(getAuthorities(user.getRoles()))
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		return Optional.ofNullable(authUser);
		
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		Set<SimpleGrantedAuthority> authorities1 = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
			.collect(Collectors.toSet());
		
		Set<SimpleGrantedAuthority> authorities = roles.stream()
				.flatMap(role ->toStream(role))
				.collect(Collectors.toSet());
		authorities.addAll(authorities1);
		return authorities;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}

	

}
