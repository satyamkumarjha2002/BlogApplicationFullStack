package com.masai.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDetails {
	
	private String message;
	private LocalDateTime localDateTime;
	
	public ExceptionDetails() {
		this.localDateTime=LocalDateTime.now();
	}

}
