package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.UserDto;
import com.blog.entities.UserEntity;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
import com.blog.utils.UserUtils;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;

	private final UserUtils userUtils;

	@Autowired
	public UserServiceImpl(UserRepo userRepo, UserUtils userUtils) {
		super();
		this.userRepo = userRepo;
		this.userUtils = userUtils;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		return userUtils.convertToUserDto(userRepo.save(userUtils.convertToUserEntity(userDto)));
	}

	@Override
	public UserDto updateUser(UserDto userDto, long userId) {
		UserEntity userEntity = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		userEntity.setName(userDto.name());
		userEntity.setPassword(userDto.password());
		userEntity.setAbout(userDto.about());
		userEntity.setEmail(userDto.email());
		return userUtils.convertToUserDto(userRepo.save(userEntity));
	}

	@Override
	public UserDto getUserById(long userId) {
		return userUtils.convertToUserDto(userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId)));
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepo.findAll().stream().map(e -> userUtils.convertToUserDto(e)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(long userId) {
		UserEntity userEntity = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		userRepo.delete(userEntity);
	}

}
