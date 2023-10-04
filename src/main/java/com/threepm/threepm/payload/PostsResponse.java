package com.threepm.threepm.payload;

import java.util.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponse {
    private int pageNo;
    private int pageSize;
    private long totalPosts;
    private int totalPages;
    private boolean last;
    private List<PostsDto> content;
}
