package com.threepm.threepm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.threepm.threepm.entity.Comments;
import com.threepm.threepm.entity.Posts;
import com.threepm.threepm.exception.PostsApiException;
import com.threepm.threepm.exception.ResourceNotFoundException;
import com.threepm.threepm.payload.CommentsDto;
import com.threepm.threepm.repository.CommentsRepository;
import com.threepm.threepm.repository.PostsRepository;
import com.threepm.threepm.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {
    private CommentsRepository commentsRepository;
    private PostsRepository postsRepository;
    private ModelMapper mapper;

    public CommentsServiceImpl(CommentsRepository commentsRepository, PostsRepository postsRepository,ModelMapper mapper) {
        this.commentsRepository = commentsRepository;
        this.postsRepository = postsRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentsDto getCommentsById(Long postId, long commentsId) {

        // retrive post by id
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));

        // comments by id

        Comments comment = commentsRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentsId));

        if (!(comment.getPosts().getId() == posts.getId())) {
            throw new PostsApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to posts!");
        }
        return mapToDto(comment);
    }

    @Override
    public List<CommentsDto> getCommentsByPostId(long postId) {
        // retrive comments by post id
        List<Comments> comments = commentsRepository.findByPostsId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentsDto createComments(long postId, CommentsDto commentsDto) {
        Comments comments = mapToEntity(commentsDto);

        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));

        // set posts to comment enity
        comments.setPosts(posts);

        // save entity to DB
        Comments newComment = commentsRepository.save(comments);

        return mapToDto(newComment);
    }

    @Override
    public CommentsDto updateComment(Long postId, long commentsId, CommentsDto commentRequest) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));

        // comments by id
        Comments comment = commentsRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentsId));

        if (!(comment.getPosts().getId() == posts.getId())) {
            throw new PostsApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to posts!");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comments updatedComments = commentsRepository.save(comment);

        return mapToDto(updatedComments);
    }

    @Override
    public void deleteComment(Long postId, long commentsId) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));

        // comments by id
        Comments comment = commentsRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentsId));

        if (!(comment.getPosts().getId() == posts.getId())) {
            throw new PostsApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to posts!");
        }

        commentsRepository.delete(comment);
    }

    private CommentsDto mapToDto(Comments comments) {
        CommentsDto commentsDto = mapper.map(comments,CommentsDto.class);
        return commentsDto;
    }

    private Comments mapToEntity(CommentsDto commentsDto) {
        Comments comments =  mapper.map(commentsDto,Comments.class);
        return comments;
    }
}
