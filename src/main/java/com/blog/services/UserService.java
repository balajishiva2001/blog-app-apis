package com.blog.services;

import java.util.List;

import com.blog.dtos.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto usetDto, long userId);
	
	UserDto getUserById(long userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(long userId);

}
