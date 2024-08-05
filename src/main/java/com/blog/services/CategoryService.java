package com.blog.services;

import java.util.List;

import com.blog.dtos.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updatecategory(CategoryDto categoryDto, long categoryId);
	
	CategoryDto getCategoryById(long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(long categoryId);

}
