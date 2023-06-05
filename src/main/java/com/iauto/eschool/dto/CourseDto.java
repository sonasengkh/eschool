package com.iauto.eschool.dto;

import com.iauto.eschool.entity.Category;

import lombok.Data;

@Data
public class CourseDto {
	
	private String name;
	private Long categoryId;
}
