package com.iauto.eschool.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.iauto.eschool.entity.Course;
import com.iauto.eschool.entity.SubscribeStatus;
import com.iauto.eschool.entity.User;

import lombok.Data;

@Data
public class SubscribeDto {

	private Long courseId; 
	private Long userId;
	private String status;
}
