package com.demo.demoEA.repository;

import org.springframework.stereotype.Repository;

import com.demo.demoEA.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findByUserId(Long userId, Pageable pageable);
}//interface PostRepository
