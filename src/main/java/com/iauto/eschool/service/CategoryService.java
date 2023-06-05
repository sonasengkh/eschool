package com.iauto.eschool.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.iauto.eschool.entity.Category;

public interface CategoryService {
	Category create(Category category);
	Category getById(Long id);
	Category update(Long id, Category category);
	void delete(Long id);
	
	Page<Category> getAll(Map<String, String> params);
	
	
}
