package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class CurrentSession {
	
   
   @Id
   private String userName;
   private String name;
   private LocalDateTime dateTime;
   private String uuid;
}
