package com.iauto.eschool.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.dto.PageDTO;
import com.iauto.eschool.dto.VideoDto;
import com.iauto.eschool.entity.Video;
import com.iauto.eschool.mapper.VideoMapper;
import com.iauto.eschool.service.VideoService;

@RestController
@RequestMapping("videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private VideoMapper videoMapper;

//	@PostMapping
//	public ResponseEntity<?> create(@RequestBody Video video){
//		
//		VideoDto videoDto = videoMapper.toVideoDto(video);
//		Video video2 = videoMapper.toVideo(videoDto);
//		System.out.println(videoDto.getCourseId());
//		System.out.println(video2.getCourse().getId());
//		return ResponseEntity.ok( videoService.create(video) );
//	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody VideoDto videoDto) {
		Video video = videoMapper.toVideo(videoDto);
		return ResponseEntity.ok(videoService.create(video));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		VideoDto videoDto = videoMapper.toVideoDto(videoService.getByid(id));
		return ResponseEntity.ok(videoDto);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody VideoDto videoDto){
		Video video = videoMapper.toVideo(videoDto);
		VideoDto videoDtoResp = videoMapper.toVideoDto( videoService.update(id, video) );
		
		return ResponseEntity.ok(videoDtoResp);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		videoService.delete(id);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping()
	public ResponseEntity<?> getVideos(@RequestParam Map<String, String> params ){
		Page<Video> videosPage = videoService.getVideos(params);
		PageDTO pageDTO = new PageDTO(videosPage);
		return ResponseEntity.ok(pageDTO) ;
	}
}
