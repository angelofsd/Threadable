package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
import com.techelevator.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    private final PostDao postDao;
    public PostController(PostDao postDao) {
        this.postDao = postDao;
    }
    @GetMapping
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int postId) {
        return postDao.getPostbyId(postId);
    }


    @GetMapping("/{userId}")
    public List<Post> getPostsByUserId(@PathVariable int userId) {
        return postDao.getPostsByForumId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postDao.createPost(post);
    }




}
