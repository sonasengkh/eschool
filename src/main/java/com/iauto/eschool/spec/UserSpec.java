package com.iauto.eschool.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.iauto.eschool.entity.User;

import lombok.Data;

@Data
public class UserSpec implements Specification<User>{
	
	private final UserFilter userFilter;
	List<Predicate> predicates = new ArrayList<>();
	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (userFilter.getId() != null) {
			Predicate id = root.get("id").in(userFilter.getId());
			predicates.add(id);
		}
		
		if (userFilter.getUsername() != null) {
			Predicate name = criteriaBuilder.like(criteriaBuilder.upper( root.get("username") ), "%" + userFilter.getUsername().toUpperCase() + "%");
			predicates.add(name);
		}
		
		return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
	}
	
	
	
	
	
}
