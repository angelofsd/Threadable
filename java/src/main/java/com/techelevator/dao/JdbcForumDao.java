package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Forum;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcForumDao implements ForumDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Forum> getTrendingForums() {
        List<Forum> newForums = new ArrayList<>();
        String sql = "SELECT * FROM forums ORDER BY date_created LIMIT 5";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            newForums.add(mapRowToForum(results));
        }
        return newForums;
    }

    @Override
    public List<Forum> getAllForums() {
        List<Forum> allForums = new ArrayList<>();
        String sql = "SELECT * FROM forums";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            allForums.add(mapRowToForum(results));
        }
        return allForums;
    }


    @Override
    public List<Forum> findFavoriteForumsByUserId(int userId) {
        List<Forum> favorites = new ArrayList<>();
        String sql = "SELECT f.id, f.name, f.description, f.date_created, f.created_by FROM forums f\n" +
                "LEFT JOIN favorite_forums ff\n" +
                "\tON f.id = ff.forum_id\n" +
                "LEFT JOIN users u\n" +
                "\tON ff.user_id = u.user_id\n" +
                "WHERE u.user_id = ? AND f.active = true;";
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

    @Override
    public boolean setFavoriteOnForum(int userId, int forumId) {
        String sql = "INSERT INTO favorite_forums (user_id, forum_id) VALUES (?, ?) RETURNING forum_id;";
        boolean success = false;
        try {
            int returnedId = jdbcTemplate.queryForObject(sql, int.class, userId, forumId);
            if (returnedId == forumId) {
                success = true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }


        return success;
    }

    @Override
    public boolean removeFavoriteOnForum(int userId, int forumId) {
        String sql = "DELETE FROM favorite_forums WHERE user_id = ? AND forum_id = ?;";
        boolean success = false;
        try {
            int rows = jdbcTemplate.update(sql, userId, forumId);
            if (rows >= 1) {
                success = true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return success;
    }

    @Override
    public Forum createForum(Forum forum) {
        String sql = "INSERT INTO forums (name, description, created_by, date_created) VALUES (?, ?, ?, ?) RETURNING id";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class,forum.getName(), forum.getDescription(), forum.getCreatedBy(), forum.getDateCreated());
        forum.setId(newId);
        return forum;
    }

    @Override
    public Forum updateForum(Forum forum) {
        String sql = "UPDATE forums SET name = ?, description = ? WHERE id = ?;";
        Forum updatedForum = null;
        try {
            int rows = jdbcTemplate.update(sql, forum.getName(), forum.getDescription(), forum.getId());
            if (rows == 0) {
                throw new DaoException("Expected 1 updated forum, found 0");
            } else {
                updatedForum = getForumById(forum.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }

        return updatedForum;
    }

    @Override
    public Forum getForumById(int forumId) {
        String sql = "SELECT * FROM forums WHERE id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, forumId);
        if (result.next()) {
            return mapRowToForum(result);
        }
        return null;
    }

    private Forum mapRowToForum(SqlRowSet results) {
        Forum forum = new Forum();

        forum.setId(results.getInt("id"));
        forum.setName(results.getString("name"));
        forum.setCreatedBy(results.getInt("created_by"));
        forum.setDateCreated(results.getTimestamp("date_created"));
        forum.setDescription(results.getString("description"));

        return forum;
    }
}
