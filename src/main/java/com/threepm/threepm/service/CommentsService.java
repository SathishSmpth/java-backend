package com.threepm.threepm.service;

import com.threepm.threepm.payload.CommentsDto;
import java.util.List;

public interface CommentsService {
    CommentsDto createComments(long postId,CommentsDto commentsDto);
    List<CommentsDto> getCommentsByPostId(long postId);

    CommentsDto getCommentsById(Long postId,long commentsId);
    CommentsDto updateComment(Long postId,long commentsId,CommentsDto commentRequest);

    void deleteComment(Long postId,long commentsId);
}
