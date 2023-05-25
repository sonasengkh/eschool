package com.iauto.eschool.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {

	CATEGORY_READ("category:read"),
	CATEGORY_WRITE("category:write"),
	CATEGORY_UPDATE("category:update"),
	CATEGORY_DETELE("category:delete"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write"),
	COURSE_UPDATE("course:update"),
	COURSE_DETELE("course:delete"),
	VIDEO_READ("video:read"),
	VIDEO_WRITE("video:write"),
	VIDEO_UPDATE("video:update"),
	VIDEO_DETELE("video:delete"),
	SUBSCRIBE_READ("subscribe:read"),
	SUBSCRIBE_WRITE("subscribe:write"),
	SUBSCRIBE_UPDATE("subscribe:update"),
	SUBSCRIBE_DETELE("subscribe:delete"),
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	USER_UPDATE("user:update"),
	USER_DETELE("user:delete")
	;
	
	private String description;
	
}
