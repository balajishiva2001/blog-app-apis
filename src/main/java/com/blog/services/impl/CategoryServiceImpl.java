package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.CategoryDto;
import com.blog.entities.CategoryEntity;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;
import com.blog.utils.CategoryUtils;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepo categoryRepo;
	
	private final CategoryUtils categoryUtils;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryUtils categoryUtils) {
		super();
		this.categoryRepo = categoryRepo;
		this.categoryUtils = categoryUtils;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		return categoryUtils.convertToCategoryDto(categoryRepo.save(categoryUtils.convertToCategoryEntity(categoryDto)));
	}

	@Override
	public CategoryDto updatecategory(CategoryDto categoryDto, long categoryId) {
		CategoryEntity categoryEntity = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		categoryEntity.setCategoryTitle(categoryDto.categoryTitle());
		categoryEntity.setCategoryDescription(categoryDto.categoryDescription());
		return categoryUtils.convertToCategoryDto(categoryRepo.save(categoryEntity));
	}

	@Override
	public CategoryDto getCategoryById(long categoryId) {
		return categoryUtils.convertToCategoryDto(categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId)));
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryRepo.findAll().stream().map(t -> categoryUtils.convertToCategoryDto(t)).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(long categoryId) {
		CategoryEntity categoryEntity = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		categoryRepo.delete(categoryEntity);
	}

}
