package com.masai.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LikeException;
import com.masai.models.Likes;
import com.masai.models.Post;
import com.masai.models.User;
import com.masai.repos.LikesRepo;
import com.masai.repos.PostRepo;
import com.masai.repos.UserRepo;

@Service
public class LikeService implements LikeServiceIntr {
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	LikesRepo likesRepo;

	@Override
	public Integer addLike(Integer postId, String userId) {
		
		Likes likes = new Likes();
		
		
		
		Optional<User> userOptional = userRepo.findById(userId);
		Optional<Post> postOptional = postRepo.findById(postId);
		
		Set<Likes> totalLikes = postOptional.get().getLikes();
		
		for(Likes  ele:totalLikes) {
			if(ele.getUser().equals(userOptional.get())) {
				totalLikes.remove(ele);
				postRepo.save(postOptional.get());
				likesRepo.delete(ele);
				throw new LikeException("Like has been removed");
			}
		}
		
		User user = likesRepo.getUserOfLike(userId);
		
		likes.setDateTime(LocalDateTime.now());
		
		likes.setUser(userOptional.get());
		likes.setPost(postOptional.get());
		
		postOptional.get().getLikes().add(likes);
      
		postRepo.save(postOptional.get());
		
		return 1;
	}

}
