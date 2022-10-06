package com.masai.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class LoginInfo {
	
	private String uuid;
	private LocalDateTime localDateTime;
	
	public LoginInfo() {
		this.localDateTime=LocalDateTime.now();
	}

}
