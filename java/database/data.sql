BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, password_hash, role)
VALUES ('bozo', 'hashed_password1', 'moderator'), ('user1', 'hashed_password2', 'user'), ('mod2', 'hashed_password3', 'moderator');
INSERT INTO forums (name, description, created_by)
VALUES ('Technology', 'Discussion about tech', 1), ('Sports', 'All about sports', 2);
INSERT INTO posts (title, body, forum_id, user_id)
VALUES ('New Gadgets', 'Latest gadgets in the market...', 1, 2), ('Football Match', 'Last nights game was...', 2, 2);
INSERT INTO replies (text, post_id, user_id)
VALUES ('I love the new gadgets!', 1, 3), ('That was an amazing game!', 2, 1);
INSERT INTO moderators (user_id, forum_id)
VALUES (3, 1), (1, 2);
INSERT INTO favorite_forums (user_id, forum_id)
VALUES (2, 1), (3, 2);
INSERT INTO liked_posts (user_id, post_id, liked) VALUES (2, 1, TRUE), (3, 2, FALSE);

COMMIT TRANSACTION;
