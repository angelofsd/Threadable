package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Forum;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcForumDao implements ForumDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Forum> findFavoriteForumsByUserId(int userId) {
        List<Forum> favorites = new ArrayList<>();
        String sql = "SELECT f.id, f.name, f.description, f.date_created, f.created_by FROM forums f\n" +
                "LEFT JOIN favorite_forums ff\n" +
                "\tON f.id = ff.forum_id\n" +
                "LEFT JOIN users u\n" +
                "\tON ff.user_id = u.user_id\n" +
                "WHERE u.user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Forum forum = mapRowToForum(results);
                favorites.add(forum);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return favorites;
    }

    private Forum mapRowToForum(SqlRowSet results) {
        return null;
    }
}
