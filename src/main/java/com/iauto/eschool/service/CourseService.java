package com.iauto.eschool.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.iauto.eschool.dto.CourseDto;
import com.iauto.eschool.entity.Course;

public interface CourseService {

	Course creat(Course course);
	Course getById(Long id);
	Course update(Long id , Course course);
	void delete(Long id);
	
	Page<Course> getCourses(Map<String, String> params);
	Page<CourseDto> getCoursesDto(Map<String, String> params);
}
