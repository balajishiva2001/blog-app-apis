package com.blog.dtos;

import java.time.LocalDate;

public record PostDto(long postId, String postTitle, String content, String imageName, LocalDate createdDate, CategoryDto categoryDto, UserDto userDto) {

}
