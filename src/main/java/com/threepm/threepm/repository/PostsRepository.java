package com.threepm.threepm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.threepm.threepm.entity.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
