package com.blog.utils;

import org.springframework.stereotype.Component;

import com.blog.dtos.UserDto;
import com.blog.entities.UserEntity;

@Component
public class UserUtils {

	public UserEntity convertToUserEntity(UserDto userDto) {
		return new UserEntity(userDto.id(),userDto.name(),userDto.email(),userDto.password(),userDto.about());
	}
	
	public UserDto convertToUserDto(UserEntity userEntity) {
		return new UserDto(userEntity.getId(),userEntity.getName(),userEntity.getEmail(),userEntity.getPassword(),userEntity.getAbout());
	}
}
