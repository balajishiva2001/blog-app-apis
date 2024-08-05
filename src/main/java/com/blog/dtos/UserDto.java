package com.blog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDto(long id, @NotEmpty @Size(min = 4, message = "UserName must have min 4 characters") String name, @Email(message = "Email is invalid!") String email, @NotEmpty @Size(min = 4, message = "Password must have min 3 or greater characters") String password, @NotEmpty String about) {

}
