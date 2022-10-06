package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.services.LikeServiceIntr;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LikeController {
	
	@Autowired
	LikeServiceIntr likeServiceIntr;
	
	@PostMapping("/like/{postId}/{userId}")
	public ResponseEntity<Integer> addLike(@PathVariable Integer postId,@PathVariable String userId) {
		return new ResponseEntity<Integer>(likeServiceIntr.addLike(postId,userId),HttpStatus.OK);
	}

}
