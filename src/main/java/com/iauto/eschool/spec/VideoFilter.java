package com.iauto.eschool.spec;

import com.iauto.eschool.entity.Course;

import lombok.Data;

@Data
public class VideoFilter {
	private Long id;
	private String title; 
	private String description; 
	private String videolink;
	private String image;
	
	private Course course;
}
