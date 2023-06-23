package com.iauto.eschool.dto;

import com.iauto.eschool.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
	
	private String name;
	private Long categoryId;
	private String categoryName;
	private Long userId;
	
}
