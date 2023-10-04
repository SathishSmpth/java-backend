package com.threepm.threepm.repository;

import com.threepm.threepm.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPostsId(long postsId);
}