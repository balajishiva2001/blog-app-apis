package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entities.CategoryEntity;
import com.blog.entities.PostEntity;
import com.blog.entities.UserEntity;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {
	
	List<PostEntity> findByUserEntity(UserEntity userEntity);
	
	List<PostEntity> findByCategoryEntity(CategoryEntity categoryEntity);
	
	@Query("select p from Post p where p.title like :title")
	List<PostEntity> findByTitleContaining(@Param("title") String title);

}
