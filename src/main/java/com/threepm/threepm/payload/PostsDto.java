package com.threepm.threepm.payload;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostsDto {
    private long id;

    @NotEmpty
    @Size(min = 4, message = "Post title have atleast 4 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description have atleast 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
