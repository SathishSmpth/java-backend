package com.threepm.threepm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.threepm.threepm.payload.PostsDto;
import com.threepm.threepm.payload.PostsResponse;
import com.threepm.threepm.service.PostsService;
import com.threepm.threepm.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {
    private PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<PostsDto> createPost(@Valid @RequestBody PostsDto postsDto) {
        return new ResponseEntity<PostsDto>(postsService.createPosts(postsDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostsResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {
        return postsService.getAllPosts(pageNo - 1, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostsDto> getPostsById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postsService.getPostsById(id));
    }

    // update post by id
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<PostsDto> updatePostById(@RequestBody PostsDto postsDto, @PathVariable(name = "id") long id) {
        PostsDto postsResponse = postsService.updatePosts(postsDto, id);
        return new ResponseEntity<PostsDto>(postsResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostsById(@PathVariable(name = "id") long id) {
        postsService.deletePostsById(id);
        return new ResponseEntity<String>("Posts was deleted successfully", HttpStatus.OK);
    }
}
