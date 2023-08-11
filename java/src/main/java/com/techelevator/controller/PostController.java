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
    public List<Post> getAllPosts(@RequestParam(defaultValue = "") String search) {
        if (search.equals("")) {
            return postDao.getAllPosts();
        } else {
            return postDao.searchForPosts(search);
        }

    }

    @GetMapping("/details/{id}")
    public Post getPostById(@PathVariable int id) {
        return postDao.getPostbyId(id);
    }


    @GetMapping("user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable int userId) {
        return postDao.getPostsByForumId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postDao.createPost(post);
    }

    @RequestMapping(path="/details/{id}", method=RequestMethod.DELETE)
    public void deletePost(@PathVariable int id){
        postDao.removePost(id);
    }




}
