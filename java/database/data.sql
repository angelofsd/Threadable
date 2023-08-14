BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('stephen', '$2a$10$cwllnn.q5mt5e4P6CZqfyOOr/tEm8SEKJFdTBtZM.VmPoFTtELrvC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role)
VALUES ('bozo', 'hashed_password1', 'moderator'), ('hobo', 'hashed_password2', 'user'), ('Fender Bender', 'hashed_password3', 'moderator'),
('frankie', 'hashers', 'user'), ('sally', 'hashed_password2', 'user'), ('Johnny Cage', 'hashed_password4', 'user'),
('lauren', 'hashed_password6', 'user'), ('Hobo Jenkins', 'hashed_password12', 'user'), ('The Donald', 'hashed_password13', 'user'),
('backstreetboyfan34', 'hashed_password16', 'moderator'), ('Nickelback#1fan', 'hashed_password22', 'user'), ('sellout$', 'hashed_password33', 'user');
INSERT INTO forums (name, description, created_by, date_created)
VALUES ('Technology', 'Discussion about tech', 1, NOW()), ('Sports', 'All about sports', 2, NOW()), ('Pokemon', 'All things Pokemon', 3, NOW()), ('Programming', 'All Things Coding', 4, NOW()), ('Food', 'All about delicious Food', 5, NOW()),
('AITDB', 'Am I the Dirt Bag? Serious Questions answered by the public.', 1, NOW()), ('Aliens', 'For people who believe in everything and nothing', 2, NOW()), ('Fans of Fish Sticks', 'For People who cant talk about Fish Sticks enough', 3, NOW()), ('Art', 'Appreciation for All things Art', 6, NOW()), ('Fine Wine', 'For People that say No to cheap Merlot', 7, NOW()),
 ('Career', 'Tips and Tricks to Career Success', 8, NOW()), ('Classic Cars', 'For Fans of Classic Rides', 9, NOW());
INSERT INTO posts (title, body, forum_id, user_id)
VALUES ('Tech Revolution', 'The rise of AI and Machine Learning', 1, 3),('New Sports Game', 'Discussing the upcoming football season', 2, 5),('Pokemon Evolutions', 'The latest updates in Pokemon Go', 3, 7),
       ('JavaScript Frameworks', 'Comparing Vue, React, and Angular', 4, 2),('Delicious Recipes', 'Sharing my favorite pasta recipe', 5, 10),('Bad Behavior Analysis', 'Am I the bad guy here?', 6, 13),
       ('UFO Sightings', 'Recent UFO sightings in my area', 7, 11),('Fish Stick Lovers', 'Why are fish sticks so amazing?', 8, 9),('Modern Art', 'Exploring contemporary art exhibitions', 9, 15),
       ('Wine Tasting Event', 'Join us for an exclusive wine tasting event', 10, 4),('Job Interview Tips', 'How to ace your next job interview', 11, 6),('Classic Car Auction', 'Buying and selling classic cars', 12, 1),
       ('Tech Gadgets', 'Reviewing the latest tech gadgets', 1, 14),('Fitness in Sports', 'How to maintain peak physical fitness', 2, 8),('Pokemon Battle', 'Discussing strategies for Pokemon battles', 3, 12),
       ('Coding Best Practices', 'Clean code principles and practices', 4, 5),('World Cuisines', 'Exploring different food cultures', 5, 9),('Self Reflection', 'Understanding our own behaviors', 6, 15),
       ('Extraterrestrial Life', 'Theories about life on other planets', 7, 2),('Fish Stick Recipes', 'Delicious recipes using fish sticks', 8, 6),('Art Exhibitions', 'Upcoming art exhibitions in the city', 9, 13),
       ('Wine and Cheese', 'Pairing wine with the perfect cheese', 10, 10),('Career Growth', 'How to climb the corporate ladder', 11, 7),('Restoring Classic Cars', 'Tips for restoring classic cars', 12, 3),
       ('New Gadgets', 'Latest gadgets in the market like omg...', 1, 2), ('Football Match', 'Last nights game was the best ever.', 2, 2), ('I caught a Pikachu', 'After 10 years I caught a Pikachu', 3, 2) ,
       ('Golden Cards', 'Has anyone ever heard of catching golden pokemon cards et all i really wish i had some golden cards', 3, 2), ('Bulbasaur is cheating', 'The title says it all, this card should be illegals', 3, 2),
       ('Java rules', 'Java is way better than Javascript. What an ugly language JavaScript is.', 4, 3), ('JavaScript is amazing', 'I thought JavaScript was horrible but then i used it and like omg omg it was amazing no compiler checks and i can run anything', 4, 4),
       ('Vue 2 Deprecated', 'Can you believe that Vue 2 is going to be deprecated at the end of the year. What will I do now? I guess i"ll have to learn Vue 3', 4, 4), ('Greek Food', 'I know Greek food and let me tell you, Gustavis is the best Greek food in town, it is not even close omg omg omg.', 5, 5),
       ('Wheels and Cheese', 'My grandmother passed down a secret recipe of wheels and cheese and it is better than any high end restaurant. First you have to take it out of the box and then boil water. Here is the key, make sure you add a dash of salt to the water. It will be amazing', 5, 5),
       ('Emerging Technologies', 'Exploring the next big things in tech', 1, 8),('Olympic Highlights', 'Discussing the latest Olympic games', 2, 14),('Pokemon Collecting', 'Tips and tricks for collecting rare Pokemon', 3, 4),
       ('Open Source Development', 'Contributing to open source projects', 4, 12),('Vegan Delights', 'Exploring the world of vegan cuisine', 5, 11),('Relationship Dilemmas', 'Navigating complex personal relationships', 6, 10),
       ('Alien Encounters', 'Personal experiences with extraterrestrial beings', 7, 15);
INSERT INTO replies (text, post_id, user_id)
VALUES ('I love the new gadgets!', 1, 3), ('That was an amazing game!', 2, 1);
INSERT INTO replies (text, date_created, post_id, user_id)
VALUES ('Great post! Except that I don"t like Barbie, she is such barbie. And don"t get me started on Ken. We all know what he is like', NOW(), 1, 3), ('I totally agree! Except  I have no idea how i got to this reply area. I really just want to make a long reply', NOW(), 1, 4),
       ('Thanks for sharing this information. Did you know that these replies need to be longer so that I can properly test how they look on the front end?!', NOW(), 1, 5), ('I have a question about this topic... Why is the sky blue? Wait is that the right topic, and if not does it matter?', NOW(), 1, 3), ('Interesting perspective!', NOW(), 1, 4),
       ('Really excited about the developments in AI!', '2023-08-06', 1, 6),
       ('Cant wait for the football season to start!', '2023-08-06', 2, 3),
       ('The new Pokemon evolutions are fascinating!', '2023-08-06', 3, 8),
       ('I prefer React over Vue and Angular, but they all have their merits.', '2023-08-06', 4, 7),
       ('I tried your pasta recipe, and it was amazing!', '2023-08-06', 5, 2),
       ('We all make mistakes; its how we learn from them that counts.', '2023-08-06', 6, 12),
       ('Those UFO sightings are intriguing! Do you have pictures?', '2023-08-06', 7, 10),
       ('Fish sticks are the best comfort food ever!', '2023-08-06', 8, 14),
       ('I recently visited an art exhibition that you might like!', '2023-08-06', 9, 11),
       ('Wish I could join the wine tasting event!', '2023-08-06', 10, 5),
       ('Great tips! They helped me in my last job interview!', '2023-08-06', 11, 9),
       ('I love classic cars! Do you have pictures of the auction?', '2023-08-06', 12, 13),
       ('Just bought a new gadget, and Im in love with it!', '2023-08-06', 13, 15),
       ('Fitness is key in sports! Great insights!', '2023-08-06', 14, 4),
       ('Bulbasaur is not cheating; its just a game mechanic!', '2023-08-06', 15, 6),
       ('Java is robust, but JavaScript has its place in web development.', '2023-08-06', 16, 1),
       ('I just migrated to Vue 3, and it was a smooth transition!', '2023-08-06', 17, 8),
       ('That Greek place is amazing! Best gyros in town!', '2023-08-06', 18, 7),
       ('Thanks for the recipe! Wheels and cheese was a hit!', '2023-08-06', 19, 10),
       ('Emerging technologies like quantum computing are the future!', '2023-08-06', 20, 12),
       ('The Olympic games were thrilling! Loved the performances!', '2023-08-06', 21, 11),
       ('I just found a rare Pokemon card at a garage sale!', '2023-08-06', 22, 14),
       ('Machine learning is shaping our future in unexpected ways!', '2023-08-06', 1, 9),
       ('The new team lineup looks promising. Go team!', '2023-08-06', 2, 11),
       ('Just evolved my Charizard. Loving the new updates!', '2023-08-06', 3, 10),
       ('Vue has a gentle learning curve. Great for beginners!', '2023-08-06', 4, 14),
       ('Nothing beats a homemade meal. Thanks for sharing!', '2023-08-06', 5, 12),
       ('Sometimes its hard to see ourselves clearly. Stay strong!', '2023-08-06', 6, 4),
       ('I saw something strange in the sky last night too!', '2023-08-06', 7, 13),
       ('Fish sticks with tartar sauce are my favorite!', '2023-08-06', 8, 5),
       ('I love abstract art. Any recommendations on galleries?', '2023-08-06', 9, 3),
       ('I prefer red wine. What about you?', '2023-08-06', 10, 6),
       ('Networking is key in career growth. Great advice!', '2023-08-06', 11, 8),
       ('My dream is to own a classic Mustang one day.', '2023-08-06', 12, 7),
       ('Just got a new smartwatch. Its a game-changer!', '2023-08-06', 13, 15),
       ('Physical therapy is also crucial in sports. Great read!', '2023-08-06', 14, 1),
       ('Pokemon battles are all about strategy. Great post!', '2023-08-06', 15, 9),
       ('JavaScript allows for rapid prototyping. Its not all bad!', '2023-08-06', 16, 11),
       ('Vue 3 has some cool features. Time to upgrade!', '2023-08-06', 17, 10),
       ('Love Greek food! Can you share the restaurants address?', '2023-08-06', 18, 14),
       ('I never thought to add salt to the water. Game-changer!', '2023-08-06', 19, 12),
       ('Quantum computing is complex but so intriguing!', '2023-08-06', 20, 4);
INSERT INTO moderators (user_id, forum_id)
VALUES (3, 1), (1, 2);
INSERT INTO favorite_forums (user_id, forum_id)
VALUES (2, 1), (3, 2);
INSERT INTO liked_posts (user_id, post_id, liked) VALUES (2, 1, TRUE), (3, 2, FALSE);

COMMIT TRANSACTION;
