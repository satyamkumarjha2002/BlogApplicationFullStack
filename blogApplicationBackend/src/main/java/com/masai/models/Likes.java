package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@EqualsAndHashCode
public class Likes {
	
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  
  private LocalDateTime dateTime;
  
  @OneToOne
  private User user;
  
  @OneToOne
  @JsonIgnore
  private Post post;
  
}

