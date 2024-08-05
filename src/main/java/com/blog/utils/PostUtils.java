package com.blog.utils;

import org.springframework.stereotype.Component;

import com.blog.dtos.PostDto;
import com.blog.entities.PostEntity;

@Component
public class PostUtils {

	private final UserUtils userUtils;

	private final CategoryUtils categoryUtils;

	public PostUtils(UserUtils userUtils, CategoryUtils categoryUtils) {
		super();
		this.userUtils = userUtils;
		this.categoryUtils = categoryUtils;
	}

	public PostEntity convertToPostEntity(PostDto postDto) {
		return new PostEntity(postDto.postId(), postDto.postTitle(), postDto.content(), postDto.imageName(),
				postDto.createdDate(), categoryUtils.convertToCategoryEntity(postDto.categoryDto()),
				userUtils.convertToUserEntity(postDto.userDto()));
	}

	public PostDto convertToPostDto(PostEntity postEntity) {
		return new PostDto(postEntity.getPostId(), postEntity.getPostTitle(), postEntity.getContent(),
				postEntity.getImageName(), postEntity.getCreatedDate(),
				categoryUtils.convertToCategoryDto(postEntity.getCategoryEntity()),
				userUtils.convertToUserDto(postEntity.getUserEntity()));
	}
}
