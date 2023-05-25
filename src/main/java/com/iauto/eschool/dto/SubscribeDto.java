package com.iauto.eschool.dto;

import lombok.Data;

@Data
public class SubscribeDto {

	private Long courseId; 
	private Long userId;
	private String status;
}
