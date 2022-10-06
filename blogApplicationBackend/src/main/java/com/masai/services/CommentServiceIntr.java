package com.masai.services;

import java.util.Set;

import com.masai.exceptions.CommentException;
import com.masai.models.Comment;

public interface CommentServiceIntr {
  public Integer addComment(Comment comment, String userId,Integer postId);
  public Set<Comment> getAllComments(Integer postId) throws CommentException;
}
