package com.iauto.eschool.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.iauto.eschool.entity.Category;

import lombok.Data;

@Data
public class CategorySpec implements Specification<Category> {

	private final CategoryFilter categoryFilter;

	List<Predicate> predicates = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		if (categoryFilter.getId() != null) {
			Predicate id = root.get("id").in(categoryFilter.getId());
			predicates.add(id);
		}
		if (categoryFilter.getName() != null) {
			Predicate name = criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
										"%"+ categoryFilter.getName().toUpperCase()+ "%");
			predicates.add(name);
		}
		
		return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
	}

}
