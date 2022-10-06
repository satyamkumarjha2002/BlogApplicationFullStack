package com.masai.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.User;

public interface UserRepo  extends JpaRepository<User, String>{
 
}
