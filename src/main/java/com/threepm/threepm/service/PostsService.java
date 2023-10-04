package com.threepm.threepm.service;

import com.threepm.threepm.payload.PostsDto;
import com.threepm.threepm.payload.PostsResponse;

public interface PostsService {
    PostsDto createPosts(PostsDto postsDto);

    PostsResponse getAllPosts(int pageNo,int pageSize,String sortBy);

    PostsDto getPostsById(long id);

    PostsDto updatePosts(PostsDto postsDto,long id);
    void deletePostsById(long id);
}
