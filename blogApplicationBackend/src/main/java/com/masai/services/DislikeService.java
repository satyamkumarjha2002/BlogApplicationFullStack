package com.masai.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LikeException;
import com.masai.models.Dislike;
import com.masai.models.Post;
import com.masai.models.User;
import com.masai.repos.DislikeRepo;
import com.masai.repos.PostRepo;
import com.masai.repos.UserRepo;

@Service
public class DislikeService implements DislikeServiceIntr {

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	DislikeRepo dislikeRepo;
	
	@Override
	public Integer addDislike(Integer postId, String userId) {
		

		Dislike dislike = new Dislike();
		
		Optional<User> userOptional = userRepo.findById(userId);
		Optional<Post> postOptional = postRepo.findById(postId);
		
		Set<Dislike> totalDislikes = postOptional.get().getDislikes();
		
		for(Dislike  ele:totalDislikes) {
			if(ele.getUser().equals(userOptional.get())) {
				totalDislikes.remove(ele);
				postRepo.save(postOptional.get());
				dislikeRepo.delete(ele);
				throw new LikeException("dislike has been removed");
			}
		}
		
		//User user = dislikeRepo.getUserOfDislike(userId);
		
		dislike.setDateTime(LocalDateTime.now());
		
		dislike.setUser(userOptional.get());
		dislike.setPost(postOptional.get());
		
		postOptional.get().getDislikes().add(dislike);
      
		postRepo.save(postOptional.get());
		
		return 1;
		
	}

}
