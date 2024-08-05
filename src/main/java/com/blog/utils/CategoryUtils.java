package com.blog.utils;

import org.springframework.stereotype.Component;

import com.blog.dtos.CategoryDto;
import com.blog.entities.CategoryEntity;

@Component
public class CategoryUtils {

	public CategoryEntity convertToCategoryEntity(CategoryDto categoryDto) {
		return new CategoryEntity(categoryDto.categoryId(), categoryDto.categoryTitle(), categoryDto.categoryDescription());
	}
	
	public CategoryDto convertToCategoryDto(CategoryEntity categoryEntity) {
		return new CategoryDto(categoryEntity.getCategoryId(), categoryEntity.getCategoryTitle(), categoryEntity.getCategoryDescription());
	}
}
