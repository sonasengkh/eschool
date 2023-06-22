package com.iauto.eschool.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.iauto.eschool.entity.Course;

import lombok.Data;

@Data
public class CourseSpec implements Specification<Course> {

	private final CourseFilter courseFilter;
	List<Predicate> predicates = new ArrayList<>();
	
	@Override
	public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (courseFilter.getId() != null) {
			Predicate id = root.get("id").in(courseFilter.getId());
			predicates.add(id);
		}
		
		if (courseFilter.getName() != null) {
			Predicate name = criteriaBuilder.like(criteriaBuilder.upper( root.get("name") ), "%" + courseFilter.getName().toUpperCase() + "%");
			predicates.add(name);
		}
		
		if(courseFilter.getCategory().getId() != null) {
			Predicate cateId = root.get("category").get("id").in(courseFilter.getCategory().getId());
			predicates.add(cateId);
		}
		if(courseFilter.getCategory().getName() != null) {
			Predicate cateName = criteriaBuilder.like( criteriaBuilder.upper( root.get("category").get("name") ) , "%" + courseFilter.getCategory().getName().toUpperCase() + "%");
			predicates.add(cateName);
		}
 
		
		if( courseFilter.getUser() != null) {
			System.out.println("track 1");
			Predicate userId = root.get("user").get("id").in(courseFilter.getUser().getId());
			predicates.add(userId);
		}
		
		return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
	}

}
