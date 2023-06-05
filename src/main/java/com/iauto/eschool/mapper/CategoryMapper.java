package com.iauto.eschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.iauto.eschool.dto.CategoryDto;
import com.iauto.eschool.entity.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE  = Mappers.getMapper(CategoryMapper.class);
	
	Category toCategory(CategoryDto categoryDto);
	
	CategoryDto toCategoryDto(Category category);
}
