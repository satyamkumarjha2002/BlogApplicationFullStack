package com.masai.services;

import java.util.List;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.UserAlreadyRegisteredException;
import com.masai.exceptions.UserNotRegisteredException;
import com.masai.models.LoginCred;
import com.masai.models.LoginInfo;
import com.masai.models.User;

public interface UserServiceIntr {
	
	public User registerUser(User user) throws UserAlreadyRegisteredException;
	public List<User> getAllUser();
	public LoginInfo login(LoginCred loginCred) throws UserNotRegisteredException;
	public User getUser(String uuid) throws LoginException;

}
