package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

}
