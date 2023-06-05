package com.iauto.eschool.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiException extends RuntimeException {
	private HttpStatus status;
	private String message;
}
