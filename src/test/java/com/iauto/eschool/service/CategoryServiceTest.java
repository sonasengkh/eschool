package com.iauto.eschool.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iauto.eschool.entity.Category;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.CategoryRepository;
import com.iauto.eschool.service.impl.CategoryServiceImpl;
import com.iauto.eschool.service.util.PageUtil;
import com.iauto.eschool.spec.CategoryFilter;
import com.iauto.eschool.spec.CategorySpec;

import static org.mockito.BDDMockito.willDoNothing;

import org.springframework.data.domain.Page;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	private CategoryService categoryService;
	
	
	@BeforeEach
	public void setUp () {
		categoryService = new CategoryServiceImpl(categoryRepository);
	}
	
	/*
	@Test
	public void testCreate() {
		//give
		Category category = new Category();
		category.setId(1l);
		category.setName("Khmer");
		
		Category category2 = new Category();
		
		//when
		//when(categoryRepository.save(any(Category.class))).thenReturn(category);
		when(categoryRepository.save(category)).thenReturn(category2);
		Category categoryReturn = categoryService.create(category);
		//then
		assertEquals(1, categoryReturn.getId());
	}
	*/
	
	@Test
	public void testCreate() {
		//give
		Category category = new Category();
		category.setId(1l);
		category.setName("Khmer");
		//when
		categoryService.create(category);
		//then
		verify(categoryRepository, times(1)).save(category);
	}
	
	@Test
	public void testGetByIdSuccess() {
		//give 
		Category category = new Category();
		category.setId(1l);
		category.setName("Khmer");
		
		//when
		when(categoryRepository.findById(1l)).thenReturn(Optional.of(category));
		Category categoryReturn = categoryService.getById(1l);
		
		//Then
		assertEquals(1, categoryReturn.getId());
	}
	
	@Test
	public void testGetByIdThrow() {
		//give
		
		//when
		when(categoryRepository.findById(2l)).thenReturn(Optional.empty());
		assertThatThrownBy(()->categoryService.getById(2l))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Can't find category 2");
		//then
		
	}
	
	@Test
	public void testUpdate() {
		//give 
		Category category = new Category();
		category.setId(1l);
		category.setName("Khmer");
		
		Category category2 = new Category();
		category2.setId(1l);
		category2.setName("English");
				
		//when
		when(categoryRepository.findById(1l)).thenReturn(Optional.of(category));
		Category categoryfind = categoryService.getById(1l);
		
		when(categoryRepository.save(category2)).thenReturn(category2);
		Category categoryReturn = categoryService.update(1l,category2);
				
		//Then
		assertEquals(1, categoryReturn.getId());
		assertEquals("English", categoryReturn.getName());
	}
	
	@Test
	public void testdelete() {
		//give 
		Category category = new Category();
		category.setId(1l);
		category.setName("Khmer");
		
		
		//when
		when(categoryRepository.findById(1l)).thenReturn(Optional.of(category));
		Category categoryfind = categoryService.getById(1l);
		
		categoryService.delete(1l);
				
		//Then
		verify(categoryRepository, times(1)).deleteById(1l);
	}
	
	@Test
	public void TestGetAll() {
		//give
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");
		params.put("name", "Khmer");
		params.put("_limit", "2");
		params.put("_page", "2");
		
		CategoryFilter categoryFilter = new CategoryFilter();
		categoryFilter.setId( 1l );
		categoryFilter.setName("Khmer");
		
		CategorySpec categorySpec = new CategorySpec(categoryFilter);
		Pageable pageable = PageUtil.getPageable(2, 2);
		
		//when
		categoryService.getAll(params);
		//then
		assertEquals(1, categoryFilter.getId());
		assertEquals("Khmer", categoryFilter.getName());
		verify(categoryRepository, times(1)).findAll(categorySpec, pageable);
		
	}
}
