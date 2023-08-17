BEGIN TRANSACTION;

DROP TABLE IF EXISTS liked_posts, favorite_forums, moderators, replies, posts, forums, users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	profile_pic_url VARCHAR(500) DEFAULT 'https://www.nicepng.com/png/full/1011-10113425_profile-clipart-generic-user-circle.png',
	bio varchar(500),
	date_created TIMESTAMP DEFAULT NOW(),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE forums (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    date_created TIMESTAMP DEFAULT NOW(),
    created_by INT,
    active BOOLEAN DEFAULT true,
    CONSTRAINT fk_created_by FOREIGN KEY (created_by) REFERENCES users(user_id)
);
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    image_url VARCHAR(500) DEFAULT 'https://images.crunchbase.com/image/upload/c_lpad,f_auto,q_auto:eco,dpr_1/v1397762747/24805058203a2cb5894c43f863c47338.png',
    date_created TIMESTAMP DEFAULT NOW(),
    forum_id INT,
    user_id INT,
    CONSTRAINT fk_forum_id FOREIGN KEY (forum_id) REFERENCES forums(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE replies (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    date_created TIMESTAMP DEFAULT NOW(),
    post_id INT,
    user_id INT,
    CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT fk_reply_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE moderators (
    user_id INT,
    forum_id INT,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_forum_id FOREIGN KEY (forum_id) REFERENCES forums(id),
    PRIMARY KEY (user_id, forum_id)
);
CREATE TABLE favorite_forums (
    user_id INT,
    forum_id INT,
    CONSTRAINT fk_favorite_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_favorite_forum_id FOREIGN KEY (forum_id) REFERENCES forums(id),
    PRIMARY KEY (user_id, forum_id)
);
CREATE TABLE liked_posts (
    user_id INT,
    post_id INT,
    liked BOOLEAN,
    CONSTRAINT fk_liked_posts_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_liked_posts_post_id FOREIGN KEY (post_id) REFERENCES posts(id),
    PRIMARY KEY (user_id, post_id)
);

COMMIT TRANSACTION;
