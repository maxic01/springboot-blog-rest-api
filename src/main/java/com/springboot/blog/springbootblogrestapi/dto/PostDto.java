package com.springboot.blog.springbootblogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(description = "PostDto Model Information")
public class PostDto {
    private Long id;
    @Schema(description = "Blog post title")
    @NotEmpty(message = "Title is required")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @Schema(description = "Blog post description")
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @Schema(description = "Blog post content")
    @NotEmpty(message = "Content is required")
    private String content;
    private Set<CommentDto> comments;
    @Schema(description = "Blog post category")
    private Long categoryId;
}
