package com.iauto.eschool.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.dto.PageDTO;
import com.iauto.eschool.dto.SubscribeDto;
import com.iauto.eschool.entity.Subscribe;
import com.iauto.eschool.mapper.SubscribeMapper;
import com.iauto.eschool.service.SubscribeService;
import com.iauto.eschool.service.util.PageUtil;

@RestController
@RequestMapping("subscribes")
public class SubscribeController {

	@Autowired
	private SubscribeService subscribeService;
	
	@Autowired
	private SubscribeMapper subscribeMapper;
	
//	@PostMapping
//	public ResponseEntity<?> requestSubscribe(@RequestBody Subscribe subscribe){
//		return ResponseEntity.ok( subscribeService.requestSubscribe(subscribe) );
//	}
	
	@PostMapping
	public ResponseEntity<?> requestSubscribe(@RequestBody SubscribeDto SubscribeDto){
		Subscribe subscribe = subscribeMapper.toSubscribe(SubscribeDto);
		Subscribe subscribeResp = subscribeService.requestSubscribe(subscribe); 
		SubscribeDto subscribeDtoResp = subscribeMapper.tosubcribeDto(subscribeResp);
		return ResponseEntity.ok( subscribeDtoResp);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getSubscribe(@PathVariable("id") Long id){
		
		return ResponseEntity.ok( subscribeMapper.tosubcribeDto( subscribeService.getSubscribe(id) ) );
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateSubscribeStatus(@PathVariable("id") Long id, @RequestBody SubscribeDto subscribeDto){
		Subscribe subscribe = subscribeService.updateSubscribeStatus(id, subscribeDto.getStatus());
		SubscribeDto subcribeDtoResp = subscribeMapper.tosubcribeDto(subscribe);
		return ResponseEntity.ok(subcribeDtoResp);
	}
	
	@GetMapping
	public ResponseEntity<?> getSubscribes(@RequestParam Map<String, String> params){
		
		
		Page<Subscribe> subscribesPage = subscribeService.getSubscribes(params);
		PageDTO pageDTO = new PageDTO(subscribesPage);
		return ResponseEntity.ok(pageDTO);
	}
}
