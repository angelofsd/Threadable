package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;
    private final ForumDao forumDao;

    public UserController(UserDao userDao, ForumDao forumDao) {
        this.userDao = userDao;
        this.forumDao = forumDao;
    }
    @RequestMapping("")
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @RequestMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable int id, @RequestBody User user) {
        return userDao.update(user);
    }

    @GetMapping("/{id}/favorite")
    public List<Forum> findFavoriteForumsByUserId(@PathVariable int id) {
        return forumDao.findFavoriteForumsByUserId(id);
    }

    @GetMapping("/mods/forums/{forumId}")
    public List<User> getModsByForumId(@PathVariable int forumId) {
        return userDao.getModsByForumId(forumId);
    }

    @GetMapping("/favorited/forums/{forumId}")
    public List<User> getUsersByFavorited(@PathVariable int forumId) {
        return userDao.getUsersByFavorited(forumId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean makeModerator(@PathVariable int userId, int forumId) {
        return userDao.makeModerator(userId, forumId);
    }
}
