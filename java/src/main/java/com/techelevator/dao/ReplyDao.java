package com.techelevator.dao;

import com.techelevator.model.Reply;

import java.util.List;

public interface ReplyDao {
    List<Reply> getAllRepliesByPostId(int postId);
    Reply getReplyById(int replyId);
    Reply createReply(Reply reply);
    boolean updateReply(int replyId, Reply reply);
    boolean deleteReply(int replyId);
}
