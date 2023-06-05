package com.iauto.eschool.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of( PermissionEnum.CATEGORY_READ,
			PermissionEnum.CATEGORY_WRITE,
			PermissionEnum.CATEGORY_UPDATE,
			PermissionEnum.CATEGORY_DETELE,
			PermissionEnum.COURSE_READ,
			PermissionEnum.COURSE_WRITE,
			PermissionEnum.COURSE_UPDATE,
			PermissionEnum.COURSE_DETELE,
			PermissionEnum.VIDEO_READ,
			PermissionEnum.VIDEO_WRITE,
			PermissionEnum.VIDEO_UPDATE,
			PermissionEnum.VIDEO_DETELE,
			PermissionEnum.SUBSCRIBE_READ,
			PermissionEnum.SUBSCRIBE_WRITE,
			PermissionEnum.SUBSCRIBE_UPDATE,
			PermissionEnum.SUBSCRIBE_DETELE,
			PermissionEnum.USER_READ,
			PermissionEnum.USER_WRITE,
			PermissionEnum.USER_UPDATE,
			PermissionEnum.USER_DETELE
			)
		),
	AUTHOR(Set.of(
			PermissionEnum.CATEGORY_READ,
			PermissionEnum.COURSE_READ,
			PermissionEnum.COURSE_WRITE,
			PermissionEnum.COURSE_UPDATE,
			PermissionEnum.COURSE_DETELE,
			PermissionEnum.VIDEO_READ,
			PermissionEnum.VIDEO_WRITE,
			PermissionEnum.VIDEO_UPDATE,
			PermissionEnum.VIDEO_DETELE,
			PermissionEnum.SUBSCRIBE_READ,
			PermissionEnum.SUBSCRIBE_WRITE,
			PermissionEnum.SUBSCRIBE_UPDATE,
			PermissionEnum.SUBSCRIBE_DETELE,
			PermissionEnum.USER_READ
			)
		)
		;
	
	private Set<PermissionEnum> permissions;
	
	public Set<SimpleGrantedAuthority> getAuthorities() {
		
		Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
			.collect(Collectors.toSet());
		
		
		//Add for Role_+[Enum Role]
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
		grantedAuthorities.add(role);
		System.out.println(grantedAuthorities);
		
		return grantedAuthorities;
	}
}
