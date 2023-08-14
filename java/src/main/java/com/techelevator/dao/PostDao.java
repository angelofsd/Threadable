package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getAllPosts();
    List<Post> searchForPosts(String search);
    Post getPostbyId(int postId);
    Post createPost(Post post);
    List<Post> getPostsByForumId(int forumId);
    List<Post> getPostsByForumIdByNew(int forumId);
    List<Post> getPostsByUserId(int userId);
    List<Post> findLikedPostsByUserId(int userId, boolean liked);
    boolean setLikePost(int userId, int postId, boolean isLiked);
    boolean removeLikePost(int userId, int postId);
    boolean removePost(int postId);
    int getNumberOfLikes(int postId, boolean isLiked);

}
