package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.PostDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/forums")
public class ForumController {

    private final ForumDao forumDao;
    private final PostDao postDao;

    public ForumController(ForumDao forumDao, PostDao postDao) {
        this.forumDao = forumDao;
        this.postDao = postDao;
    }


    @GetMapping("/{userId}/favorited")
    public List<Forum> findFavoriteForumsByUserId(@PathVariable int userId) {
        return forumDao.findFavoriteForumsByUserId(userId);
    }


    @GetMapping("/trending")
    public List<Forum> getTrendingForums() {
        return forumDao.getTrendingForums();
    }

    @GetMapping
    public List<Forum> getAllForums() {
        return forumDao.getAllForums();
    }

    @GetMapping("/{forumId}")
    public Forum getForumById(@PathVariable int forumId) {
        return forumDao.getForumById(forumId);
    }
    @GetMapping("/{forumId}/posts")
    public List<Post> getPostsByForumId(@PathVariable int forumId) {
        return postDao.getPostsByForumId(forumId);
    }

    @GetMapping("/{forumId}/new")
    public List<Post> getPostsByForumIdByNew(@PathVariable int forumId) {
        return postDao.getPostsByForumId(forumId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Forum createForum(@RequestBody Forum forum) {

        return forumDao.createForum(forum);
    }

    @PutMapping("/{forumId}")
    public Forum updateForum(@PathVariable int forumId, @RequestBody Forum forum) {
        try {
            forum.setId(forumId);
            return forumDao.updateForum(forum);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
        }
    }

    @PostMapping("/favorite/{forumId}/{userId}")
    public boolean setFavoriteOnForum(@PathVariable int forumId, @PathVariable int userId) {
        return forumDao.setFavoriteOnForum(userId, forumId);
    }

    @DeleteMapping("/favorite/{forumId}/{userId}")
    public boolean removeFavoriteOnForum(@PathVariable int forumId, @PathVariable int userId) {
        return forumDao.removeFavoriteOnForum(userId, forumId);
    }

}
