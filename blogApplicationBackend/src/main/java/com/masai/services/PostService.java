package com.masai.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.PostException;
import com.masai.models.Comment;
import com.masai.models.CurrentSession;
import com.masai.models.Dislike;
import com.masai.models.Likes;
import com.masai.models.Post;
import com.masai.models.User;
import com.masai.repos.CommentRepo;
import com.masai.repos.CurrentSessionRepo;
import com.masai.repos.DislikeRepo;
import com.masai.repos.LikesRepo;
import com.masai.repos.PostRepo;
import com.masai.repos.UserRepo;

@Service
public class PostService implements PostServiceIntr {

	@Autowired
	PostRepo postRepo;

	@Autowired
	CurrentSessionRepo currentSessionRepo;

	@Autowired
	userService userService;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	LikesRepo likesRepo;
	
	@Autowired
	DislikeRepo dislikeRepo;
	
	@Override
	public String createPost(Post post, String uuid) {

		CurrentSession logedUser = currentSessionRepo.findByUuid(uuid);

		if (logedUser == null) {
			throw new LoginException("Please Login First");
		}

		post.setComments(new HashSet<Comment>());
		post.setDislikes(new HashSet<Dislike>());
		post.setLikes(new HashSet<Likes>());

		User user = userService.getUser(uuid);
		post.setUser(user);
   
		user.getPosts().add(post);
	    userRepo.save(user);

		return "done";
	}

	@Override
	public List<Post> getAllPost() throws PostException {

		List<Post> list = postRepo.findAll();

		if (list.isEmpty()) {
			throw new PostException("Currently we haven't any post");
		}
		Collections.reverse(list);
		return list;
	}

	@Override
	public List<Post> getPostOfParticularUser(String uuid) throws PostException {

		CurrentSession logedUser = currentSessionRepo.findByUuid(uuid);

		if (logedUser == null) {
			throw new LoginException("Please Login First");
		}
		
		Optional<User> userOptional = userRepo.findById(logedUser.getUserName());
		
		userOptional.get().getPosts().sort((Post p1, Post p2)->{
			if(p1.getId()<p2.getId()) {
				return 1;
			}else if(p1.getId()>p2.getId()) {
				return -1;
			}
			return 0;
		});

		return userOptional.get().getPosts();
	}

	@Override
	public User getUserByPost(Integer id) throws PostException {
		
	    Optional<Post> postOptional = postRepo.findById(id);
	    
	    if(postOptional.isEmpty()) {
	    	throw new PostException("Post is not present with this id");
	    }
		
		return postOptional.get().getUser();
	}

	@Override
	public boolean deletePost(Integer postId) throws PostException {
		
		Optional<Post> postOptional = postRepo.findById(postId);
		
		if(postOptional.isEmpty()) {
			throw new PostException("Post not found");
		}
		
		User user = postOptional.get().getUser();
		
		user.getPosts().remove(postOptional.get());
		
		
		
		userRepo.save(user);
		
		return true;
	}

}
