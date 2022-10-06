package com.masai.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Post;
import com.masai.models.User;
import com.masai.services.PostServiceIntr;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
   
	@Autowired
	PostServiceIntr postServiceIntr;
	
    @PostMapping("/create/{uuid}")
    public ResponseEntity<String> createPost(@RequestBody Post post,@PathVariable String uuid){
    	
    	return new ResponseEntity<String>(postServiceIntr.createPost(post, uuid),HttpStatus.OK);
    }
    
    @GetMapping("/getAllPost")
    public ResponseEntity<List<Post>> getAllPost(){
    	return new ResponseEntity<List<Post>>(postServiceIntr.getAllPost(),HttpStatus.OK);
    }
    
    @GetMapping("/getPost/{uuid}")
    public ResponseEntity<List<Post>> getPost(@PathVariable String uuid){
    	return new ResponseEntity<List<Post>>(postServiceIntr.getPostOfParticularUser(uuid),HttpStatus.OK);
    }
    
    @GetMapping("/getUserByPost/{postId}")
    public ResponseEntity<User> getUserByPost(@PathVariable Integer postId){
    	return new ResponseEntity<User>(postServiceIntr.getUserByPost(postId),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Integer postId){
    	return new ResponseEntity<Boolean>( postServiceIntr.deletePost(postId),HttpStatus.OK);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Boolean> updatePost(@RequestBody Post post){
    	return new ResponseEntity<Boolean>(postServiceIntr.updatePost(post),HttpStatus.OK);
    }
    
    
}
