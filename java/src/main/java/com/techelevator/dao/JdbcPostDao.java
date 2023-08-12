package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Post;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Post> searchForPosts(String search) {
        List<Post> results = new ArrayList<>();
        String sql = "SELECT id, title, body, image_url, date_created, forum_id, user_id FROM posts WHERE title ILIKE ? OR body ILIKE ?;";
        String wildSearch = "%" + search + "%";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, wildSearch, wildSearch);
            while (result.next()) {
                results.add(mapRowToPost(result));
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        catch (Exception ex) {
            throw new DaoException("Something went wrong!", ex);
        }

        return results;
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
            while (result.next()) {
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
    public List<Post> getPostsByForumIdByNew(int forumId){
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE forum_id = ? ORDER BY date_created;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, forumId);
            while (result.next()) {
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

    @Override
    public List<Post> findLikedPostsByUserId(int userId) {
        List<Post> likedPosts = new ArrayList<>();
        String sql = "SELECT p.id, p.title, p.body, p.image_url, p.date_created, p.forum_id, p.user_id FROM posts p\n" +
                "LEFT JOIN liked_posts lp\n" +
                "\tON p.id = lp.post_id\n" +
                "WHERE lp.user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                likedPosts.add(mapRowToPost(results));
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        catch (Exception ex) {
            throw new DaoException("Something went wrong!", ex);
        }

        return likedPosts;
    }

    @Override
    public boolean setLikePost(int userId, int postId, boolean isLiked) {
        String updateSql = "UPDATE liked_posts SET liked = ? WHERE user_id = ? AND post_id = ?;";
        String createSql = "INSERT INTO liked_posts(user_id, post_id, liked) VALUES (?,?,?) RETURNING post_id;";
        boolean success = false;
        try {
            int rows = jdbcTemplate.update(updateSql, isLiked, userId, postId);
            if (rows == 0) {
                int post = jdbcTemplate.queryForObject(createSql, int.class, userId, postId, isLiked);
                if (post > 0) {
                    success = true;
                } else {
                    throw new DaoException("Could not generate like on post");
                }
            } else {
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
    public boolean removeLikePost(int userId, int postId) {
        String sql = "DELETE FROM liked_posts WHERE user_id = ? AND post_id = ?;";
        boolean success = false;
        try {
            int rows = jdbcTemplate.update(sql, userId, postId);
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
    public boolean removePost(int postId) {
        String sql = "DELETE from replies where post_id = ?;\n" +
                "DELETE FROM liked_posts WHERE post_id = ?;\n" +
                "DELETE from posts where id= ?;";
        boolean success = false;
        try {
            int rows = jdbcTemplate.update(sql, postId, postId, postId);
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
    public int getVotes(int postId) {
        int votes = 0;
        String sql = "SELECT COUNT(*) as count FROM liked_posts WHERE post_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, postId);
            if (result.next()) {
                votes = result.getInt("count");
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        catch (Exception ex) {
            throw new DaoException("Something went wrong!", ex);
        }

        return votes;

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
