package com.threepm.threepm.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.threepm.threepm.payload.CommentsDto;
import com.threepm.threepm.service.CommentsService;

@RestController
@RequestMapping("/api/")
public class CommentsController {
    private CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentsDto> createComments(@PathVariable(value = "id") long postId,
            @RequestBody CommentsDto commentsDto) {
        return new ResponseEntity<CommentsDto>(commentsService.createComments(postId, commentsDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}/comments")
    public List<CommentsDto> getCommentsByPostId(@PathVariable(value = "id") Long postId) {
        return commentsService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{id}/comments/{commentId}")
    public ResponseEntity<CommentsDto> getCommentsById(@PathVariable(value = "id") Long postId, @PathVariable(value = "commentId") Long commentId) {

        CommentsDto commentsDto = commentsService.getCommentsById(postId, commentId);
        return new ResponseEntity<CommentsDto>(commentsDto,HttpStatus.OK);
    }

    @PutMapping("/posts/{id}/comments/{commentId}")
    public ResponseEntity<CommentsDto> updateComments(@PathVariable(value = "id") Long postId, @PathVariable(value = "commentId") Long commentId,@RequestBody CommentsDto commentsDto) {

        CommentsDto updateComments = commentsService.updateComment(postId, commentId, commentsDto);
        return new ResponseEntity<CommentsDto>(updateComments,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}/comments/{commentId}")
    public ResponseEntity<String> deleteComments(@PathVariable(value = "id") Long postId, @PathVariable(value = "commentId") Long commentId,@RequestBody CommentsDto commentsDto) {

        commentsService.deleteComment(postId, commentId);
        return new ResponseEntity<String>("Comment deleted successfully!",HttpStatus.OK);
    }
}
