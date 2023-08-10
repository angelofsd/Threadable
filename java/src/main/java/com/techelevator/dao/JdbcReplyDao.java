package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Post;
import com.techelevator.model.Reply;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReplyDao implements ReplyDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcReplyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reply> getAllRepliesByPostId(int postId) {
        List<Reply> replies = new ArrayList<>();
        String sql = "SELECT * FROM replies WHERE post_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);
        while (results.next()) {
            replies.add(mapRowToReply(results));
        }
        return replies;
    }

    @Override
    public Reply getReplyById(int replyId) {
        String sql = "SELECT * FROM replies WHERE id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, replyId);
        if (result.next()) {
            return mapRowToReply(result);
        }
        return null; // or throw an exception if preferred
    }

    @Override
    public Reply createReply(Reply reply) {
        String sql = "INSERT INTO replies (text, date_created, post_id, user_id) VALUES (?, ?, ?, ?) RETURNING id";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class, reply.getText(), reply.getDateCreated(), reply.getPostId(), reply.getUserId());
        reply.setId(newId);
        return reply;
    }

    @Override
    public boolean updateReply(int replyId, Reply reply) {
        String sql = "UPDATE replies SET text = ?, date_created = ?, post_id = ?, user_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, reply.getText(), reply.getDateCreated(), reply.getPostId(), reply.getUserId(), replyId) > 0;
    }

    @Override
    public boolean deleteReply(int replyId) {
        String sql = "DELETE FROM replies WHERE id = ?";
        return jdbcTemplate.update(sql, replyId) > 0;
    }

    private Reply mapRowToReply(SqlRowSet row) {
        Reply reply = new Reply();
        reply.setId(row.getInt("id"));
        reply.setText(row.getString("text"));
        reply.setDateCreated(row.getTimestamp("date_created"));
        reply.setPostId(row.getInt("post_id"));
        reply.setUserId(row.getInt("user_id"));
        return reply;
    }
}
