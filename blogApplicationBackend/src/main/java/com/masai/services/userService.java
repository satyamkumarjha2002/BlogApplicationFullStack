package com.masai.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserAlreadyRegisteredException;
import com.masai.exceptions.UserNotRegisteredException;
import com.masai.models.Comment;
import com.masai.models.CurrentSession;
import com.masai.models.Dislike;
import com.masai.models.Likes;
import com.masai.models.LoginCred;
import com.masai.models.LoginInfo;
import com.masai.models.Post;
import com.masai.models.User;
import com.masai.repos.CurrentSessionRepo;
import com.masai.repos.UserRepo;

@Service
public class userService implements UserServiceIntr {

	@Autowired
	UserRepo userRepo;

	@Autowired
	CurrentSessionRepo currentSessionRepo;

	@Override
	public User registerUser(User user) throws UserAlreadyRegisteredException {

		Optional<User> userOptional = userRepo.findById(user.getEmail());
    
		if (userOptional.isPresent()) {
			throw new UserAlreadyRegisteredException("You are already registered please Login");
		}

		user.setDateTime(LocalDateTime.now());
		user.setPosts(new ArrayList<Post>());
		
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public LoginInfo login(LoginCred loginCred) throws UserNotRegisteredException {
		String userName = loginCred.getUserName();
		String password = loginCred.getPassword();
		Optional<User> userOptional = userRepo.findById(userName);
		
		if (userOptional.isEmpty()) {
			throw new UserNotRegisteredException("You are not register");
		}
		boolean passres = userOptional.get().getPassword().equals(password);

		Optional<CurrentSession> logedUser = currentSessionRepo.findById(userName);

		if (logedUser.isPresent()&&passres) {
			throw new LoginException("You are already loged in");
		}

		if (!passres) {
			throw new LoginException("Incorrent usrname or password");
		}

		CurrentSession currentSession = new CurrentSession();

		currentSession.setUserName(userName);
		currentSession.setName(userOptional.get().getName());
		currentSession.setDateTime(LocalDateTime.now());

		UUID randomUUID = UUID.randomUUID();

		currentSession.setUuid(randomUUID.toString().replaceAll("-", ""));
        
		currentSessionRepo.save(currentSession);
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUuid(randomUUID.toString().replaceAll("-", ""));

		return loginInfo;
	}

	@Override
	public User getUser(String uuid) throws LoginException {
		
		CurrentSession userLoginStatus = currentSessionRepo.findByUuid(uuid);
		
		if(userLoginStatus == null) {
			throw new LoginException("You are not loged in");
		}
		
		
		return userRepo.findById(userLoginStatus.getUserName()).get();
	}

}
