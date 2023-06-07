package com.iauto.eschool.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.Category;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.CategoryRepository;
import com.iauto.eschool.service.CategoryService;
import com.iauto.eschool.service.util.PageUtil;
import com.iauto.eschool.spec.CategoryFilter;
import com.iauto.eschool.spec.CategorySpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private final CategoryRepository categoryRepository;
	
	@Override
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("category", id));
	}

	@Override
	public Category update(Long id, Category categoryUpdate) {
		Category category = getById(id);
		category.setName(categoryUpdate.getName());
		return categoryRepository.save(category);
		
	}

	@Override
	public void delete(Long id) {
		getById(id);
		categoryRepository.deleteById(id);
	}

	@Override
	public Page<Category> getAll(Map<String, String> params) {
		CategoryFilter categoryFilter = new CategoryFilter();
		
		if (params.containsKey("id")) {
			categoryFilter.setId( Long.parseLong(params.get("id")) );
		}
		if (params.containsKey("name")) {
			categoryFilter.setName( params.get("name") );
		}
		
		
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt( params.get(PageUtil.PAGE_NUMBER) );
		}
		
		int pageSize = PageUtil.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageSize = Integer.parseInt( params.get(PageUtil.PAGE_LIMIT) );
		}
		
		CategorySpec categorySpec = new CategorySpec(categoryFilter);
		
		Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
		
		Page<Category> categoryPage = categoryRepository.findAll(categorySpec, pageable);
		
		return categoryPage;
	}

	

}
