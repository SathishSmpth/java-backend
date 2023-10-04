package com.threepm.threepm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.threepm.threepm.entity.Posts;
import com.threepm.threepm.exception.ResourceNotFoundException;
import com.threepm.threepm.payload.PostsDto;
import com.threepm.threepm.payload.PostsResponse;
import com.threepm.threepm.repository.PostsRepository;
import com.threepm.threepm.service.PostsService;

@Service
public class PostsServiceImpl implements PostsService {

    private PostsRepository postsRepository;
    private ModelMapper mapper;

    public PostsServiceImpl(PostsRepository postsRepository,ModelMapper mapper) {
        this.postsRepository = postsRepository;
        this.mapper = mapper;
    }

    @Override
    public PostsDto createPosts(PostsDto postsDto) {

        Posts newPosts = postsRepository.save(mapToEntity(postsDto));
        PostsDto postsResponse = mapToDto(newPosts);

        return postsResponse;
    }

    @Override
    public PostsResponse getAllPosts(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Posts> posts = postsRepository.findAll(pageable);

        // get content for page object
        List<Posts> listOfPosts = posts.getContent();

        List<PostsDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostsResponse postsResponse = new PostsResponse();
        postsResponse.setPageNo(posts.getNumber() + 1);
        postsResponse.setPageSize(posts.getSize());
        postsResponse.setTotalPages(posts.getTotalPages());
        postsResponse.setTotalPosts(posts.getTotalElements());
        postsResponse.setContent(content);
        postsResponse.setLast(posts.isLast());
        return postsResponse;
    }

    @Override
    public PostsDto getPostsById(long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
        return mapToDto(posts);
    }

    @Override
    public PostsDto updatePosts(PostsDto postsDto, long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        posts.setTitle(postsDto.getTitle());
        posts.setDescription(postsDto.getDescription());
        posts.setContent(postsDto.getContent());

        Posts updatedPosts = postsRepository.save(posts);

        return mapToDto(updatedPosts);
    }

    @Override
    public void deletePostsById(long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postsRepository.delete(posts);
    }

    private PostsDto mapToDto(Posts posts) {
        PostsDto postsDto = mapper.map(posts,PostsDto.class);
        return postsDto;
    }

    private Posts mapToEntity(PostsDto postsDto) {
        Posts posts = mapper.map(postsDto,Posts.class);
        return posts;
    }
}
