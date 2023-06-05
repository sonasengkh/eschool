package com.iauto.eschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.iauto.eschool.dto.VideoDto;
import com.iauto.eschool.entity.Video;
import com.iauto.eschool.service.CourseService;

@Mapper(componentModel = "spring", uses = {CourseService.class})
public interface VideoMapper {
	
	VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);
	
	@Mapping(target = "course", source = "courseId")
	Video toVideo (VideoDto videoDto);
	
	@Mapping(target = "courseId", source="course.id")
	VideoDto toVideoDto (Video video);
	
}
