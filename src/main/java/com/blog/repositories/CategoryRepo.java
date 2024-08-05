package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entities.CategoryEntity;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{

}
