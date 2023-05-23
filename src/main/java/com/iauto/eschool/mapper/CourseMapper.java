package com.iauto.eschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.iauto.eschool.dto.CourseDto;
import com.iauto.eschool.dto.VideoDto;
import com.iauto.eschool.entity.Course;
import com.iauto.eschool.entity.Video;
import com.iauto.eschool.service.CategoryService;
import com.iauto.eschool.service.CourseService;

@Mapper( componentModel = "spring", uses = {CategoryService.class})
public interface CourseMapper {

	CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
	
	@Mapping(target = "category", source = "categoryId")
	Course toCourse(CourseDto courseDto);
	
	@Mapping(target = "categoryId", source = "category.id")
	CourseDto toCourseDto(Course course);
	
}

