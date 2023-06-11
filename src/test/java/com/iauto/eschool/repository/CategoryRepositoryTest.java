package com.iauto.eschool.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.iauto.eschool.entity.Category;

@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
//	@Test
//	public void testFindByNameLike() {
//		//give
//		Category category = new Category();
//		category.setName("English");
//		categoryRepository.save(category);
//		
//		Category category2 = new Category();
//		category2.setName("KhmEr");
//		categoryRepository.save(category2);
//		
//		//when
//		List<Category> categorys = categoryRepository.findByNameLike("%E%");
//		
//		//then
//		assertEquals(2, categorys.size());
//		assertEquals("English", categorys.get(0).getName());
//		assertEquals(1, categorys.get(0).getId());
//		
//	}
	
}
