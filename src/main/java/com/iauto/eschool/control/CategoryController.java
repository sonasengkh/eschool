package com.iauto.eschool.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.dto.CategoryDto;
import com.iauto.eschool.dto.PageDTO;
import com.iauto.eschool.entity.Category;
import com.iauto.eschool.mapper.CategoryMapper;
import com.iauto.eschool.service.CategoryService;

@RestController
@RequestMapping("categorys")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {
		Category category = categoryService.create(CategoryMapper.INSTANCE.toCategory(categoryDto));
		return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto( categoryService.getById(id) ));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
		Category category = categoryService.update(id, CategoryMapper.INSTANCE.toCategory(categoryDto));
		return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		categoryService.delete(id);

		return ResponseEntity.ok("{\"message\":\"successful\"}");
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam Map<String, String> params){
		Page<Category> categoryPage = categoryService.getAll(params);
		PageDTO pageDTO = new PageDTO(categoryPage);
		
		
		
		return ResponseEntity.ok(pageDTO);
	}
}
