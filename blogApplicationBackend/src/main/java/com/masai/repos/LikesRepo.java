package com.masai.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.models.Likes;
import com.masai.models.User;

public interface LikesRepo extends JpaRepository<Likes, Integer> {
	
	@Query("SELECT user FROM Likes WHERE id = :userId ")
     User getUserOfLike(@Param("userId") String userId);
}
