package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.LoginCred;
import com.masai.models.User;
import com.masai.models.LoginInfo;
import com.masai.services.UserServiceIntr;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class userController {
	
	@Autowired
	UserServiceIntr userServiceIntr;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		 return new ResponseEntity<>(userServiceIntr.registerUser(user),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginInfo> login(@RequestBody LoginCred loginCred){
		return new ResponseEntity(userServiceIntr.login(loginCred),HttpStatus.OK);
	}
	
	@DeleteMapping("/logout/{uuid}")
	public ResponseEntity<String> logout(@PathVariable String uuid){
		return new ResponseEntity<String>( userServiceIntr.logout(uuid),HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{uuid}")
	public ResponseEntity<User> getUser(@PathVariable String uuid){
		 return new ResponseEntity(userServiceIntr.getUser(uuid),HttpStatus.OK);
	}
	
	
    
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(){
		return new ResponseEntity(userServiceIntr.getAllUser(),HttpStatus.OK);
	}
	
}
