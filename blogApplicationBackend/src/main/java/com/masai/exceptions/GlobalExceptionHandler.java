package com.masai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<ExceptionDetails> userAlreadyRegister(UserAlreadyRegisteredException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();

		exceptionDetails.setMessage(ex.getMessage());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserNotRegisteredException.class)
	public ResponseEntity<ExceptionDetails> userAlreadyRegister(UserNotRegisteredException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();

		exceptionDetails.setMessage(ex.getMessage());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ExceptionDetails> postException(PostException ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();

		exceptionDetails.setMessage(ex.getMessage());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ExceptionDetails> userAlreadyRegister(LoginException ex, WebRequest wr) {
    
		ExceptionDetails exceptionDetails = new ExceptionDetails();

		exceptionDetails.setMessage(ex.getMessage());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> parrentException(Exception ex, WebRequest wr) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();

		exceptionDetails.setMessage(ex.getMessage());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

	}

}
