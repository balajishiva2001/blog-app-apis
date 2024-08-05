package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dtos.PostDto;
import com.blog.services.PostService;
import com.blog.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable long userId,
			@PathVariable long categoryId) {
		return new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable long userId) {
		return new ResponseEntity<List<PostDto>>(postService.getPostsByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategoryById(@PathVariable long categoryId) {
		return new ResponseEntity<List<PostDto>>(postService.getPostsByCategoryId(categoryId), HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		return new ResponseEntity<List<PostDto>>(postService.getAllPosts(), HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable long postId) {
		return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable long postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(String.format("Post with id: %l deleted successfully.", postId), true), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long postId) {
		return new ResponseEntity<PostDto>(postService.updatePost(postDto, postId), HttpStatus.OK);
	}

	@GetMapping("/posts/search/{title}")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String title) {
		return new ResponseEntity<List<PostDto>>(postService.searchPostsByTitle(new StringBuilder("%").append(title).append("%").toString()), HttpStatus.OK);
	}

}
