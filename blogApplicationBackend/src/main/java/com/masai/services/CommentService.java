package com.masai.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CommentException;
import com.masai.exceptions.UserNotRegisteredException;
import com.masai.models.Comment;
import com.masai.models.Post;
import com.masai.models.User;
import com.masai.repos.CommentRepo;
import com.masai.repos.PostRepo;
import com.masai.repos.UserRepo;

@Service
public class CommentService implements CommentServiceIntr {
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PostRepo postRepo;

	@Override
	public Integer addComment(Comment comment,String userId, Integer postId) {
	    
		Optional<User> userOptional = userRepo.findById(userId);
		Optional<Post> postOptional = postRepo.findById(postId);
		
		
	    comment.setDateTime(LocalDateTime.now());
	    comment.setUser(userOptional.get());
	    comment.setPost(postOptional.get());
		
	    postOptional.get().getComments().add(comment);
	    commentRepo.save(comment);
		//userRepo.save(userOptional.get());
		
		return 1;
	}

	@Override
	public Set<Comment> getAllComments(Integer postId) throws CommentException {
		
		Optional<Post> postOptional = postRepo.findById(postId);
		
		Set<Comment> comments = postOptional.get().getComments();
		
		return comments;
	}

}
