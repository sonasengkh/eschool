package com.iauto.eschool.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.iauto.eschool.entity.Video;

public interface VideoService {
	Video create(Video video);
	Video getByid(Long id);
	Video update(Long id, Video video);
	void delete(Long id);
	
	Page<Video> getVideos(Map<String, String> params);
	
}
