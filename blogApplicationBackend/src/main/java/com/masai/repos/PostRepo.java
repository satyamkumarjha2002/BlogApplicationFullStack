package com.masai.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.models.Post;
import com.masai.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    
}
