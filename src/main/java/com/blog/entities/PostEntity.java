package com.blog.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long postId;

	private String postTitle;

	private String content;

	private String imageName;

	private LocalDate createdDate;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryEntity categoryEntity;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity userEntity;

	public PostEntity(long postId, String postTitle, String content, String imageName, LocalDate createdDate,
			CategoryEntity categoryEntity, UserEntity userEntity) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.content = content;
		this.imageName = imageName;
		this.createdDate = createdDate;
		this.categoryEntity = categoryEntity;
		this.userEntity = userEntity;
	}

	public PostEntity() {
		super();
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
