package com.masai.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
   
   @Id
   private String email;
   private String name;
   private String age;
   
   @JsonProperty(access = Access.WRITE_ONLY)
   private String password;
   @JsonProperty(access = Access.WRITE_ONLY)
   private LocalDateTime dateTime;
   
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
   @JsonIgnore
   private List<Post> posts;
}
