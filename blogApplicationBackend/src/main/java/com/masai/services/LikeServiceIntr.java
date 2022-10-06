package com.masai.services;

import com.masai.exceptions.PostException;
import com.masai.models.Post;

public interface LikeServiceIntr {
	
	public Integer addLike(Integer postId, String userId);
	
}
