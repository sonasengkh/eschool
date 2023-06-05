package com.iauto.eschool.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.iauto.eschool.entity.Subscribe;

public interface SubscribeService {
	Subscribe requestSubscribe(Subscribe subscribe);
	Subscribe getSubscribe(Long id);
	Subscribe updateSubscribeStatus(Long id,String status);
	
	Page<Subscribe> getSubscribes(Map<String, String> params);
	
}
