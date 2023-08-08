package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postDao.createPost(post);
    }




}
