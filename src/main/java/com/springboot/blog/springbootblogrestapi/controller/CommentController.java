package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.CommentDto;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
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
@RequestMapping("/api/comments")
@Tag(
        name = "CRUD Rest APIs for Comment Resource"
)
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Create Comment", description = "Create a comment")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@Valid @PathVariable(value = "postId") Long postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get Comment By Post Id", description = "Get a comment by the post id")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @Operation(summary = "Get Comment By Id And Post Id", description = "Get a comment by comment id and post id")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDto> getCommentByIdAndPostId(@PathVariable(value = "postId") Long postId,
                                                              @PathVariable(value = "commentId") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentByIdAndPostId(postId, commentId), HttpStatus.OK);
    }

    @Operation(summary = "Update Comment", description = "Update a comment")
    @ApiResponse(responseCode = "200", description = "OK")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @Operation(summary = "Patch Comment", description = "Update partially a comment")
    @ApiResponse(responseCode = "200", description = "OK")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDto> patchComment(@PathVariable(value = "postId") Long postId,
                                                   @PathVariable(value = "commentId") Long commentId,
                                                   @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.patchComment(postId, commentId, commentDto), HttpStatus.OK);
    }


    @Operation(summary = "Delete Comment", description = "Delete a comment")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
