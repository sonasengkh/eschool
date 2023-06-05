package com.iauto.eschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.iauto.eschool.dto.SubscribeDto;
import com.iauto.eschool.dto.VideoDto;
import com.iauto.eschool.entity.Subscribe;
import com.iauto.eschool.entity.Video;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.service.CourseService;
import com.iauto.eschool.service.SubscribeService;
import com.iauto.eschool.service.UserService;

@Mapper(componentModel = "spring", uses = {SubscribeService.class,CourseService.class,UserService.class})
public interface SubscribeMapper {
	SubscribeMapper INSTANCE = Mappers.getMapper(SubscribeMapper.class);
	
	@Mapping(target = "course", source = "courseId")
	@Mapping(target = "user" , source="userId")
	Subscribe toSubscribe(SubscribeDto subscribeDto);
	
	@Mapping(target = "courseId", source="course.id")
	@Mapping(target = "userId", source="user.id")
	SubscribeDto tosubcribeDto (Subscribe subscribe);
	
	
	
		
}
