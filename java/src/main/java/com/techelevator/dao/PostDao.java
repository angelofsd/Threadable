package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getAllPosts();
    Post getPostbyId(int postId);
    Post createPost(Post post);
    List<Post> getPostsByForumId(int forumId);
    List<Post> getPostsByUserId(int userId);

}
