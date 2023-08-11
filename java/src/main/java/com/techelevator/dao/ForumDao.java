package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {

    List<Forum> getTrendingForums();
    List<Forum> getAllForums();
    Forum createForum(Forum forum);
    Forum updateForum(Forum forum);
    Forum getForumById(int forumId);
    List<Forum> findFavoriteForumsByUserId(int userId);
    boolean setFavoriteOnForum(int userId, int forumId);
    boolean removeFavoriteOnForum(int userId, int forumId);

}
