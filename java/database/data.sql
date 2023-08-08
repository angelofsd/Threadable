BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('stephen', '$2a$10$cwllnn.q5mt5e4P6CZqfyOOr/tEm8SEKJFdTBtZM.VmPoFTtELrvC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role)
VALUES ('bozo', 'hashed_password1', 'moderator'), ('user1', 'hashed_password2', 'user'), ('mod2', 'hashed_password3', 'moderator');
INSERT INTO forums (name, description, created_by, date_created)
VALUES ('Technology', 'Discussion about tech', 1, NOW()), ('Sports', 'All about sports', 2, NOW()), ('Pokemon', 'All about sports', 3, NOW()), ('Programming', 'All Things Coding', 4, NOW()), ('Food', 'All about delicious Food', 5, NOW());
INSERT INTO posts (title, body, forum_id, user_id)
VALUES ('New Gadgets', 'Latest gadgets in the market like omg...', 1, 2), ('Football Match', 'Last nights game was the best ever.', 2, 2), ('I caught a Pikachu', 'After 10 years I caught a Pikachu', 3, 2) ,
('Golden Cards', 'Has anyone ever heard of catching golden pokemon cards et all i really wish i had some golden cards', 3, 2), ('Bulbasaur is cheating', 'The title says it all, this card should be illegals', 3, 2),
 ('Java rules', 'Java is way better than Javascript. What an ugly language JavaScript is.', 4, 3), ('JavaScript is amazing', 'I thought JavaScript was horrible but then i used it and like omg omg it was amazing no compiler checks and i can run antyhing', 4, 4),
('Vue 2 Deprecated', 'Can you believe that Vue 2 is going to be deprecated at the end of the year. What will I do now? I guess i"ll have to learn Vue 3', 4, 4), ('Greek Food', 'I know Greek food and let me tell you, Gustavis is the best Greek food in town, it is not even close omg omg omg.', 5, 5),
 ('Wheels and Cheese', 'My grandmother passed down a secret recipe of wheels and cheese and it is better than any high end restaurant. First you have to take it out of the box and then boil water. Here is the key, make sure you add a dash of salt to the water. It will be amazing', 5, 5);
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
