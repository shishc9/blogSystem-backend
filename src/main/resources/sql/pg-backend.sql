DROP DATABASE IF EXISTS blogsys;
CREATE DATABASE blogsys;
\c blogsys

DROP TABLE IF EXISTS "user";
CREATE TABLE "user"(
    user_id SERIAL,
    username VARCHAR(64) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    user_identity smallint  DEFAULT 0, -- 是不是博主
    age INT DEFAULT NULL,
    gender CHAR(7) NOT NULL DEFAULT 'UNKNOWN', -- 性别，默认未知
    hobby TEXT, -- 兴趣
    email VARCHAR(64) DEFAULT NULL,
    gmt_create TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, -- 用户创建时间
    gmt_last_login TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, -- 用户上次登陆时间
    PRIMARY KEY (user_id)
);

-- 保证数据库中用户名唯一及邮箱唯一
ALTER TABLE "user" ADD CONSTRAINT username_uniq UNIQUE(username);
ALTER TABLE "user" ADD CONSTRAINT email_uniq UNIQUE(email);

DROP TABLE IF EXISTS blog;
CREATE TABLE blog(
    blog_id SERIAL,
    username VARCHAR(64) DEFAULT 'admin', -- 发布博客的用户，目前只能是admin
    title VARCHAR(255) NOT NULL, -- 标题
    content TEXT, -- 正文
    status  smallint NOT NULL DEFAULT 0, -- 博客状态，只有0/1，0：公开，1：私有
    read_num INT DEFAULT 0, -- 博客阅读数
    like_num INT DEFAULT 0, -- 博客点赞数
    gmt_create TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, -- 博客创建时间
    gmt_modified TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP, -- 博客修改时间
    PRIMARY KEY (blog_id)
);

-- 保证每个用户不能有两篇及以上相同标题的博客
-- 其实目前规划能写博客的只有admin
ALTER TABLE blog ADD CONSTRAINT username_title_uniq UNIQUE(username, title);
