package com.iauto.eschool.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.iauto.eschool.entity.Video;

import lombok.Data;

@Data
public class VideoSpec implements Specification<Video> {

	private final VideoFilter videoFilter;
	
	List<Predicate> predicates = new ArrayList<>();
	
	@Override
	public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		if (videoFilter.getId() != null) {
		 	Predicate id = root.get("id").in(videoFilter.getId());
		 	predicates.add(id);
		}
		if (videoFilter.getTitle() != null) {
			Predicate title = criteriaBuilder.like( criteriaBuilder.upper( root.get("title") ), "%" + videoFilter.getTitle().toUpperCase() + "%");
			predicates.add(title);
		}
		
		if (videoFilter.getCourse().getId() != null) {
			 Predicate courseId = root.get("course").get("id").in(videoFilter.getCourse().getId());
			 predicates.add(courseId);
		}
		if(videoFilter.getCourse().getName() != null) {
			Predicate courseName = criteriaBuilder.like(criteriaBuilder.upper( root.get("course").get("name") ), "%" + videoFilter.getCourse().getName().toUpperCase() + "%"); 
			predicates.add(courseName);
		}
		
		if (videoFilter.getCourse().getCategory().getId() != null) {
			Predicate cateId = root.get("course").get("category").get("id").in(videoFilter.getCourse().getCategory().getId());
			predicates.add(cateId);
		}
		if(videoFilter.getCourse().getCategory().getName() != null) {
			Predicate cateName = criteriaBuilder.like( criteriaBuilder.upper( root.get("course").get("category").get("name") ) , "%" + videoFilter.getCourse().getCategory().getName().toUpperCase() + "%");
			predicates.add(cateName);
		}
		
		return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
	}

}
