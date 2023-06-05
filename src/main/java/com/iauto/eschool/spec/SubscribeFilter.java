package com.iauto.eschool.spec;

import com.iauto.eschool.entity.Course;
import com.iauto.eschool.entity.SubscribeStatus;
import com.iauto.eschool.entity.User;

import lombok.Data;

@Data
public class SubscribeFilter {
	private Long id;
	private Course course; 
	private User user;
	private SubscribeStatus status;
}
