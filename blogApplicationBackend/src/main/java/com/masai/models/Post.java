package com.masai.models;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String heading;
	private String subHeading;
	private String content;
	private String imgLink;
	private LocalDateTime dateTime;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	private Set<Likes> likes;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Dislike> dislikes;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	public Post() {
		dateTime = LocalDateTime.now();
	}
}
