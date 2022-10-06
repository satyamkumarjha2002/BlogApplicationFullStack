package com.masai.services;

import java.util.List;
import java.util.Set;

import com.masai.exceptions.PostException;
import com.masai.models.Post;
import com.masai.models.User;

public interface PostServiceIntr {
    public String createPost(Post post,String uuid);
    public List<Post> getAllPost() throws PostException;
    public List<Post> getPostOfParticularUser(String uuid) throws PostException;
    public User getUserByPost(Integer id) throws PostException;
    public boolean deletePost(Integer postId) throws PostException;
}
