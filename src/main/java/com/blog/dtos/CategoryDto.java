package com.blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryDto(long categoryId,
		@NotEmpty @Size(min = 4, message = "CategoryTitle must have min 4 characters") String categoryTitle,
		@NotEmpty String categoryDescription) {

}
