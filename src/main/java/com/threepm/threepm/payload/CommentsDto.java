package com.threepm.threepm.payload;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentsDto {
    private long id;
    private String name;
    private String email;
    private String body;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
