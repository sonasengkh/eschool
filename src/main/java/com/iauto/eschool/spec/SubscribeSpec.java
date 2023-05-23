package com.iauto.eschool.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.iauto.eschool.entity.Subscribe;

import lombok.Data;

@Data
public class SubscribeSpec implements Specification<Subscribe> {
	
	private final SubscribeFilter subscribeFilter;
	
	List<Predicate> predicates = new ArrayList<>();
	
	@Override
	public Predicate toPredicate(Root<Subscribe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (subscribeFilter.getId() != null) {
			Predicate id = root.get("id").in(subscribeFilter.getId());
			predicates.add(id);
		}
		if (subscribeFilter.getStatus() !=null) {
			Predicate status = root.get("status").in(subscribeFilter.getStatus());
			predicates.add(status);
		}
		
		return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
	}
	
	

}
