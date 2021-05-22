DROP DATABASE IF EXISTS blogsys;
CREATE DATABASE blogsys;
\c blogsys

DROP TABLE IF EXISTS users;
CREATE TABLE users(
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL UNIQUE,
    email VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_identity SMALLINT NOT NULL DEFAULT 0, -- 0:TOURIST 1:BLOGGER
    gmt_create TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 用户创建时间
    gmt_last_login TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 用户上次登陆时间
    -- For compatibility
    age INTEGER NOT NULL DEFAULT 0,
    gender TEXT NOT NULL DEFAULT 'unknown'
);

DROP TABLE IF EXISTS blog;
CREATE TABLE blog(
    blog_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL, -- 标题
    content TEXT NOT NULL DEFAULT '', -- 正文
    status SMALLINT NOT NULL DEFAULT 0,
    read_num BIGINT NOT NULL DEFAULT 0, -- 博客阅读数
    like_num BIGINT NOT NULL DEFAULT 0, -- 博客点赞数
    gmt_create TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 博客创建时间
    gmt_modified TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 博客修改时间
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
    comment_id BIGSERIAL PRIMARY KEY,
    blog_id BIGINT NOT NULL,
    username VARCHAR(64) NOT NULL,
    content TEXT NOT NULL DEFAULT '',
    gmt_create TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    parent_comment_id BIGINT NOT NULL DEFAULT 0,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (blog_id) REFERENCES blog(blog_id)
);

DROP TABLE IF EXISTS message;
CREATE TABLE message (
    message_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    username VARCHAR(64) NOT NULL,
    content VARCHAR(255) NOT NULL DEFAULT '',
    gmt_create TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    parent_comment_id BIGINT NOT NULL DEFAULT 0,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

DROP TABLE IF EXISTS blike;
CREATE TABLE blike (
    blog_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (blog_id) REFERENCES blog(blog_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    UNIQUE(blog_id, user_id)
);

DROP TABLE IF EXISTS perms;
CREATE TABLE perms (
    user_identity SMALLINT NOT NULL,
    entity VARCHAR(10) NOT NULL,
    perm VARCHAR(10) NOT NULL
);
