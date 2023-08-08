package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import com.techelevator.model.Reply;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

    boolean update(User user);

    boolean changePermissions(int userId, String role);

//    List<Forum> findFavoriteForumsByUserId(int userId);
//
//    boolean setFavoriteOnForum(int userId, int forumId);
//
//    List<Post> findLikedPostsByUserId(int userId);
//
//    boolean setLikePost(int userId, int postId);
//
//    boolean removeLikeOnPost(int userId, int postId);
//
//    List<Reply> findLikedRepliesByUserId(int userId);
//
//    boolean setLikeReply(int userId, int replyId);
//
//    boolean removeLikeOnReply(int userId, int replyId);
}
