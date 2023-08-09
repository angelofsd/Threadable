package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.PostDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{userId}/forums")
    public List<Forum> findFavoriteForumsByUserId(@PathVariable int userId) {
        return forumDao.findFavoriteForumsByUserId(userId);
    }


    @GetMapping("/")
    public List<Forum> getForumsByLatestCreated() {
        return forumDao.getForumsByLatestCreated();
    }

    @GetMapping("/{forumId}")
    public Forum getForumById(@PathVariable int forumId) {
        return forumDao.getForumById(forumId);
    }
    @GetMapping("/{forumId}/posts")
    public List<Post> getPostsByForumId(@PathVariable int forumId) {
        return postDao.getPostsByForumId(forumId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Forum createForum(@RequestBody Forum forum) {

        return forumDao.createForum(forum);
    }



}
