package com.portfolioprojects.rest.webservices.restfulwebservices.repository;

import com.portfolioprojects.rest.webservices.restfulwebservices.entity.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
