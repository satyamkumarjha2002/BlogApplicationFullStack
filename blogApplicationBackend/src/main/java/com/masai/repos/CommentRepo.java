package com.masai.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
