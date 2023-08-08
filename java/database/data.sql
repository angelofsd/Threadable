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
INSERT INTO replies (text, date_created, post_id, user_id)
VALUES ('Great post! Except that I don"t like Barbie, she is such barbie. And don"t get me started on Ken. We all know what he is like', NOW(), 1, 3), ('I totally agree! Except  I have no idea how i got to this reply area. I really just want to make a long reply', NOW(), 1, 4),
  ('Thanks for sharing this information. Did you know that these replies need to be longer so that I can properly test how they look on the front end?!', NOW(), 1, 5), ('I have a question about this topic... Why is the sky blue? Wait is that the right topic, and if not does it matter?', NOW(), 1, 3), ('Interesting perspective!', NOW(), 1, 4);
INSERT INTO moderators (user_id, forum_id)
VALUES (3, 1), (1, 2);
INSERT INTO favorite_forums (user_id, forum_id)
VALUES (2, 1), (3, 2);
INSERT INTO liked_posts (user_id, post_id, liked) VALUES (2, 1, TRUE), (3, 2, FALSE);

COMMIT TRANSACTION;
