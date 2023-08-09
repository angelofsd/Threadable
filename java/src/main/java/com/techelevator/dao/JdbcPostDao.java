package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Post;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            posts.add(mapRowToPost(result));
        }
        return posts;
    }

    @Override
    public Post getPostbyId(int postId) {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, postId);
            if (result.next()) {
                post = mapRowToPost(result);
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        catch (Exception ex) {
            throw new DaoException("Something went wrong!", ex);
        }

        return post;
    }

    @Override
    public List<Post> getPostsByForumId(int forumId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE forum_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, forumId);
            if (result.next()) {
                posts.add(mapRowToPost(result));
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        catch (Exception ex) {
            throw new DaoException("Something went wrong!", ex);
        }

        return posts;
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            if (result.next()) {
                posts.add(mapRowToPost(result));
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        catch (Exception ex) {
            throw new DaoException("Something went wrong!", ex);
        }

        return posts;
    }

    @Override
    public Post createPost(Post post) {
        String sql = "INSERT INTO posts (title, body, image_url, date_created, forum_id, user_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        int newPostId = jdbcTemplate.queryForObject(sql,
                Integer.class,
                post.getTitle(),
                post.getBody(),
                post.getImageURL(),
                post.getDateCreated(),
                post.getForumId(),
                post.getUserId());
        post.setPostId(newPostId);
        return post;
    }



    private Post mapRowToPost(SqlRowSet rs) {
        Post post = new Post();
        post.setPostId(rs.getInt("id"));
        post.setTitle(rs.getString("title"));
        post.setBody(rs.getString("body"));
        post.setImageURL(rs.getString("image_url"));
        post.setDateCreated(rs.getDate("date_created"));
        post.setForumId(rs.getInt("forum_id"));
        post.setUserId(rs.getInt("user_id"));

        return post;
    }
}
