package com.iauto.eschool.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.Category;
import com.iauto.eschool.entity.Course;
import com.iauto.eschool.entity.Video;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.VideoRepository;
import com.iauto.eschool.service.CourseService;
import com.iauto.eschool.service.VideoService;
import com.iauto.eschool.service.util.PageUtil;
import com.iauto.eschool.spec.VideoFilter;
import com.iauto.eschool.spec.VideoSpec;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Override
	public Video create(Video video) {
		courseService.getById(video.getCourse().getId());
		return videoRepository.save(video);
	}

	@Override
	public Video getByid(Long id) {
		return videoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("video", id));
	}

	@Override
	public Video update(Long id, Video video) {
		courseService.getById(video.getCourse().getId());
		getByid(id);
		video.setId(id);
		return videoRepository.save(video);
	}

	@Override
	public void delete(Long id) {
		getByid(id);
		videoRepository.deleteById(id);
	}

	@Override
	public Page<Video> getVideos(Map<String, String> params) {
		VideoFilter videoFilter = new VideoFilter();
		Course course = new Course();
		Category category = new Category();
		
		if(params.containsKey("id")) {
			long id = Long.parseLong( params.get("id") );
			videoFilter.setId(id);
		}
		if(params.containsKey("title")) {
			String name = params.get("title");
			videoFilter.setTitle(name);
		}
		if (params.containsKey("courseid")) {
			Long coursid = Long.parseLong( params.get("courseid") );
			course.setId(coursid);
		}
		if (params.containsKey("coursename")) {
			String courseName = params.get("coursename");
			course.setName(courseName);
		}
		
		if (params.containsKey("cateid")) {
			Long cateId = Long.parseLong( params.get("cateid") );
			category.setId(cateId);
		}
		if(params.containsKey("catename")) {
			String cateName = params.get("catename");
			category.setName(cateName);
		}
		course.setCategory(category);
		videoFilter.setCourse(course);
		
		//-----------
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt( params.get(PageUtil.PAGE_NUMBER) );
		}
		
		int pageSize = PageUtil.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageSize = Integer.parseInt( params.get(PageUtil.PAGE_LIMIT) );
		}
		
		VideoSpec videoSpec = new VideoSpec(videoFilter);
		Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
		
		Page<Video> videoPage = videoRepository.findAll(videoSpec, pageable);
		
		return videoPage;
	}



}
