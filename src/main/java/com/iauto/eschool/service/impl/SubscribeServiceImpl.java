package com.iauto.eschool.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.Subscribe;
import com.iauto.eschool.entity.SubscribeStatus;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.SubscribeRepository;
import com.iauto.eschool.service.CourseService;
import com.iauto.eschool.service.SubscribeService;
import com.iauto.eschool.service.UserService;
import com.iauto.eschool.service.util.PageUtil;
import com.iauto.eschool.spec.SubscribeFilter;
import com.iauto.eschool.spec.SubscribeSpec;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class SubscribeServiceImpl implements SubscribeService{

	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Override
	public Subscribe requestSubscribe(Subscribe subscribe) {
		userService.getById(subscribe.getUser().getId());
		courseService.getById(subscribe.getCourse().getId());
		subscribe.setStatus(SubscribeStatus.PENDING);
		return subscribeRepository.save(subscribe);
	}

	@Override
	public Subscribe getSubscribe(Long id) {
		return subscribeRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("subscribe", id));
	}

	@Override
	public Subscribe updateSubscribeStatus(Long id, String status) {
		Subscribe subscribe = getSubscribe(id);
		
		//convert String to subscribStatus Enum
		SubscribeStatus subscribeStatus = null;
		for (SubscribeStatus s : SubscribeStatus.values()) {
			if(s.name().equalsIgnoreCase(status)) {
				subscribeStatus = s;
			}
		}
		if (subscribeStatus == null) {
			throw new ResourceNotFoundException("subscribe status " + status, 0);
		}
		subscribe.setStatus(subscribeStatus);
		
		return subscribeRepository.save(subscribe);
	}

	@Override
	public Page<Subscribe> getSubscribes(Map<String, String> params) {

		SubscribeFilter subscribeFilter = new SubscribeFilter();
		
		if (params.containsKey("id")) {
			Long id = Long.parseLong( params.get("id") );
			subscribeFilter.setId(id);
		}
		if(params.containsKey("status")) {
			String status = params.get("status");
			
			//convert String to subscribStatus Enum
			SubscribeStatus subscribeStatus = null;
			for (SubscribeStatus s : SubscribeStatus.values()) {
				if(s.name().equalsIgnoreCase(status)) {
					subscribeStatus = s;
				}
			}
			if (subscribeStatus == null) {
				throw new ResourceNotFoundException("subscribe status " + status, 0);
			}
			subscribeFilter.setStatus(subscribeStatus);
		}
		
		//-----------
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt( params.get(PageUtil.PAGE_NUMBER) );
		}
		
		int pageSize = PageUtil.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageSize = Integer.parseInt( params.get(PageUtil.PAGE_LIMIT) );
		}
		
		SubscribeSpec subscribeSpec = new SubscribeSpec(subscribeFilter);
		Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
		Page<Subscribe> subscribePage = subscribeRepository.findAll(subscribeSpec, pageable);
		
		return subscribePage;
	}

}
