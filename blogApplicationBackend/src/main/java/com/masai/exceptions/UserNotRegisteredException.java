package com.masai.exceptions;

public class UserNotRegisteredException extends RuntimeException {
   public UserNotRegisteredException() {
	   
   }
   
   public UserNotRegisteredException(String message) {
	   super(message);
   }
}
