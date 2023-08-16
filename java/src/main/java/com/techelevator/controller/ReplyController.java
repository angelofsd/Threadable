package com.techelevator.controller;

import com.techelevator.dao.ReplyDao;
import com.techelevator.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts/details/{postId}/replies")
public class ReplyController {

    private final ReplyDao replyDao;

    public ReplyController(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }
    @GetMapping
    public List<Reply> getAllRepliesByPostId(@PathVariable int postId) {
        return replyDao.getAllRepliesByPostId(postId);
    }

    @GetMapping("/{replyId}")
    public Reply getReplyById(@PathVariable int replyId) {
        return replyDao.getReplyById(replyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reply createReply(@PathVariable int postId, @RequestBody Reply reply) {
        reply.setPostId(postId);
        return replyDao.createReply(reply);
    }

    @PutMapping("/{replyId}")
    public boolean updateReply(@PathVariable int replyId, @RequestBody Reply reply) {
        return replyDao.updateReply(replyId, reply);
    }

    @DeleteMapping("/{replyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReply(@PathVariable int replyId) {
        return replyDao.deleteReply(replyId);
    }
}
