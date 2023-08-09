package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getAllPosts();
    Post getPostbyId(int postId);
    Post createPost(Post post);

    List<Post> getReplyByForumId(int forumId);

}
