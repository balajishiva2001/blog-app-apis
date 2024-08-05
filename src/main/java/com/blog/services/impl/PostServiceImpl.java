package com.blog.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.PostDto;
import com.blog.entities.CategoryEntity;
import com.blog.entities.PostEntity;
import com.blog.entities.UserEntity;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;
import com.blog.utils.CategoryUtils;
import com.blog.utils.PostUtils;
import com.blog.utils.UserUtils;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepo postRepo;

	private final UserRepo userRepo;

	private final CategoryRepo categoryRepo;

	private final PostUtils postUtils;

	private final UserUtils userUtils;

	private final CategoryUtils categoryUtils;

	@Autowired
	public PostServiceImpl(PostRepo postRepo, UserRepo userRepo, CategoryRepo categoryRepo, PostUtils postUtils,
			UserUtils userUtils, CategoryUtils categoryUtils) {
		super();
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.categoryRepo = categoryRepo;
		this.postUtils = postUtils;
		this.userUtils = userUtils;
		this.categoryUtils = categoryUtils;
	}

	@Override
	public PostDto createPost(PostDto postDto, long userId, long categoryId) {
		UserEntity userEntity = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		CategoryEntity categoryEntity = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		PostEntity postEntity = new PostEntity(postDto.postId(), postDto.postTitle(), postDto.content(), "default.png",
				LocalDate.now(), categoryEntity, userEntity);
		return postUtils.convertToPostDto(postRepo.save(postEntity));
	}

	@Override
	public PostDto updatePost(PostDto postDto, long postId) {
		PostEntity postEntity = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		postEntity.setPostTitle(postDto.postTitle());
		postEntity.setContent(postDto.content());
		postEntity.setImageName(postDto.imageName());
		return postUtils.convertToPostDto(postRepo.save(postEntity));
	}

	@Override
	public void deletePost(long postId) {
		PostEntity postEntity = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		postRepo.delete(postEntity);
	}

	@Override
	public List<PostDto> getAllPosts() {
		return postRepo.findAll().stream().map(t -> postUtils.convertToPostDto(t)).collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(long postId) {
		return postUtils.convertToPostDto(
				postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId)));
	}

	@Override
	public List<PostDto> getPostsByCategoryId(long categoryId) {
		CategoryEntity categoryEntity = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return postRepo.findByCategoryEntity(categoryEntity).stream().map(t -> postUtils.convertToPostDto(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByUserId(long userId) {
		UserEntity userEntity = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		return postRepo.findByUserEntity(userEntity).stream().map(t -> postUtils.convertToPostDto(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPostsByTitle(String keyword) {
		return postRepo.findByTitleContaining(keyword).stream().map(t -> postUtils.convertToPostDto(t))
				.collect(Collectors.toList());
	}

}
