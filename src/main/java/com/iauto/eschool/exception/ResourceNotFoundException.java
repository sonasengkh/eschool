package com.iauto.eschool.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{

	public ResourceNotFoundException(String message, long id) {
		super(HttpStatus.NOT_FOUND, "Can't find " + message + " " + id);
	}
	
}
