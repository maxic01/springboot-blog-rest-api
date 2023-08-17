package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import com.springboot.blog.springbootblogrestapi.utils.AppContstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD Rest APIs for Post Resource"
)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create Post", description = "Create a post")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Posts", description = "Get all the posts")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping()
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "page", defaultValue = AppContstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(name = "size", defaultValue = AppContstants.DEFAULT_PAGE_SIZE, required = false) int size,
            @RequestParam(name = "sortBy", defaultValue = AppContstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppContstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
        return new ResponseEntity<>(postService.getAllPosts(page, size, sortBy, sortDir), HttpStatus.OK);
    }

    @Operation(summary = "Get Post By Id", description = "Get a post by the id")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update Post", description = "Update a post")
    @ApiResponse(responseCode = "200", description = "OK")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @Operation(summary = "Patch Post", description = "Update partially a post")
    @ApiResponse(responseCode = "200", description = "OK")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> patchPost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.patchPost(postDto, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Post", description = "Delete a post")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get Posts By Category", description = "Get all the posts based in category")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostByCategory(id), HttpStatus.OK);
    }
}
