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
    @GetMapping("/hot")
    public List<Post> getHotPosts(@RequestParam(defaultValue = "") String search) {
        if (search.equals("")) {
            return postDao.getHotPosts();
        } else {
            return postDao.searchForPosts(search);
        }
    }

    @GetMapping("")
    public List<Post> getAllPosts(@RequestParam String search) {
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
        return postDao.getPostsByUserId(userId);
    }

    @GetMapping("/forums/{forumId}")
    public List<Post> getPostsByForumId(@PathVariable int forumId) {
        return postDao.getPostsByForumId(forumId);
    }

    @GetMapping("/forums/{forumId}/new")
    public List<Post> getPostsByForumIdByNew(@PathVariable int forumId) {
        return postDao.getPostsByForumIdByNew(forumId);
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

    @GetMapping("/likes/user/{userId}")
    public List<Post>getLikedPostsByUserId(@PathVariable int userId) {
        return postDao.findLikedPostsByUserId(userId, true);
    }

    @GetMapping("/dislikes/user/{userId}")
    public List<Post>getDislikedPostsByUserId(@PathVariable int userId) {
        return postDao.findLikedPostsByUserId(userId, false);
    }

    @PostMapping("/{postId}/{userId}/true")
    public boolean likePost(@PathVariable int postId, @PathVariable int userId) {
        return postDao.setLikePost(userId, postId, true);
    }

    @PostMapping("/{postId}/{userId}/false")
    public boolean dislikePost(@PathVariable int postId, @PathVariable int userId) {
        return postDao.setLikePost(userId, postId, false);
    }

    @DeleteMapping("/{postId}/delete_like/{userId}")
    public boolean deleteLikeOnPost(@PathVariable int postId, @PathVariable int userId) {
        return postDao.removeLikePost(userId, postId);
    }

    @GetMapping("/details/{postId}/likes")
    public int numberOfLikes(@PathVariable int postId) {
        return postDao.getNumberOfLikes(postId, true);
    }

    @GetMapping("/details/{postId}/dislikes")
    public int numberOfDislikes(@PathVariable int postId) {
        return postDao.getNumberOfLikes(postId, false);
    }


}
