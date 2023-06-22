package com.iauto.eschool.spec;

import com.iauto.eschool.entity.Category;
import com.iauto.eschool.entity.User;

import lombok.Data;

@Data
public class CourseFilter {
	
	private Long id;
	private String name;
	private Category category;
	private User user;
}
