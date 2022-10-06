package com.masai.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.models.Dislike;
import com.masai.models.User;

public interface DislikeRepo extends JpaRepository<Dislike, Integer> {
	@Query("SELECT user FROM Dislike WHERE id = :userId ")
    User getUserOfDislike(@Param("userId") String userId);
}
