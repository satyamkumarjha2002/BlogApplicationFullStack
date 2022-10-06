package com.masai.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Comment;
import com.masai.services.CommentServiceIntr;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	CommentServiceIntr commentServiceIntr;
	
	@PostMapping("/add/{postId}/{userId}")
	public ResponseEntity<Integer> addComment(@PathVariable String userId,@PathVariable Integer postId, @RequestBody Comment comment){
		return new ResponseEntity<Integer>(commentServiceIntr.addComment(comment,userId,postId),HttpStatus.OK);
	}
	
	@GetMapping("/getComments/{postId}")
	public ResponseEntity<Set<Comment>> getAllComment(@PathVariable Integer postId){
		  return new ResponseEntity(commentServiceIntr.getAllComments(postId),HttpStatus.OK);
	}
	

}
