package com.blog.services;

import java.util.List;

import com.blog.dtos.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, long userId, long categoryId);

	PostDto updatePost(PostDto postDto, long postId);

	void deletePost(long postId);

	List<PostDto> getAllPosts();

	PostDto getPostById(long postId);

	List<PostDto> getPostsByCategoryId(long categoryId);

	List<PostDto> getPostsByUserId(long userId);

	List<PostDto> searchPostsByTitle(String keyword);

}
