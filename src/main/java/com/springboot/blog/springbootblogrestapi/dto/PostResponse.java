package com.springboot.blog.springbootblogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
