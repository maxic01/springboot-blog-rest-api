package com.springboot.blog.springbootblogrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Body is required")
    @Size(min = 10, message = "Body should have at least 10 characters")
    private String body;
}
