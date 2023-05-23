package com.iauto.eschool.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.iauto.eschool.entity.Course;

import lombok.Data;

@Data
public class VideoDto {
	
	private String title; 
	private String description; 
	private String videolink;
	private String image;
	
	private Long courseId;
}
