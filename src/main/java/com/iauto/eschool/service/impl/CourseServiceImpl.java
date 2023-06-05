package com.iauto.eschool.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iauto.eschool.entity.Category;
import com.iauto.eschool.entity.Course;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.CourseRepository;
import com.iauto.eschool.service.CategoryService;
import com.iauto.eschool.service.CourseService;
import com.iauto.eschool.service.util.PageUtil;
import com.iauto.eschool.spec.CourseFilter;
import com.iauto.eschool.spec.CourseSpec;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Course creat(Course course) {
		
		categoryService.getById(course.getCategory().getId());
		return courseRepository.save(course);
	}

	@Override
	public Course getById(Long id) {
		return courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course", id));
	}

	@Override
	public Course update(Long id, Course course) {
		getById(id);
		categoryService.getById(course.getCategory().getId());
		return courseRepository.save(course);
	}

	@Override
	public void delete(Long id) {
		getById(id);
		courseRepository.deleteById(id);
	}

	@Override
	public Page<Course> getCourses(Map<String, String> params) {
		CourseFilter courseFilter = new CourseFilter();
		Category category = new Category();
		
		if (params.containsKey("id")) {
			Long id = Long.parseLong( params.get("id") );
			courseFilter.setId(id);
		}
		
		if (params.containsKey("name")) {
			String name = params.get("name");
			courseFilter.setName(name);
		}
		
		if (params.containsKey("cateid")) {
			Long cateId = Long.parseLong( params.get("cateid") );
			category.setId(cateId);
		}
		if(params.containsKey("catename")) {
			String CateName = params.get("catename");
			category.setName(CateName);
		}
		courseFilter.setCategory(category);
		
		
		//-----------
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt( params.get(PageUtil.PAGE_NUMBER) );
		}
		
		int pageSize = PageUtil.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageSize = Integer.parseInt( params.get(PageUtil.PAGE_LIMIT) );
		}
		
		CourseSpec courseSpec = new CourseSpec(courseFilter);
		Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
		
		Page<Course> coursePage = courseRepository.findAll(courseSpec, pageable);
		return coursePage;
	}

}
