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
import com.iauto.eschool.service.UserService;

@Mapper( componentModel = "spring", uses = {CategoryService.class,UserService.class})
public interface CourseMapper {

	CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
	
	@Mapping(target = "category", source = "categoryId")
	@Mapping(target = "user", source = "userId")
	Course toCourse(CourseDto courseDto);
	
	@Mapping(target = "categoryId", source = "category.id")
	@Mapping(target = "categoryName", source = "category.name")
	@Mapping(target = "userId", source = "user.id")
	CourseDto toCourseDto(Course course);
	
}

