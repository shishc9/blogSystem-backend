DROP DATABASE IF EXISTS blogsys;
CREATE DATABASE blogsys;
use blogsys;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
                     user_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '博主和游客id, 数据库自增',
                     username VARCHAR(64) NOT NULL COMMENT '用户名',
                     password VARCHAR(255) DEFAULT NULL COMMENT '密码',
                     user_identity TINYINT UNSIGNED DEFAULT 0 COMMENT '是不是博主',
                     age INT(2) UNSIGNED DEFAULT NULL COMMENT '年龄',
                     gender CHAR(6) NOT NULL NULL DEFAULT 'MALE' COMMENT '性别，默认男性',
                     email VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
                     gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
                     gmt_last_login DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '用户上次登陆时间',
                     PRIMARY KEY (user_id) USING BTREE,
                     CONSTRAINT ck CHECK (gender = 'MALE' or gender = 'FEMALE')
)ENGINE = InnoDB CHARACTER SET = utf8mb4 AUTO_INCREMENT = 1;
# 保证数据库中用户名唯一
# 一个邮箱只能绑定一个用户
ALTER TABLE user ADD UNIQUE KEY (username);
ALTER TABLE user ADD UNIQUE KEY (email);

DROP TABLE IF EXISTS blog;
CREATE TABLE blog(
                     blog_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                     user_id BIGINT UNSIGNED NOT NULL COMMENT '博客所属用户id',
                     title VARCHAR(255) NOT NULL COMMENT '标题',
                     content LONGTEXT COMMENT '正文',
                     status  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '博客状态，只有0/1，0：公开，1：私有',
                     read_num INT UNSIGNED DEFAULT 0 COMMENT '博客阅读数',
                     like_num INT UNSIGNED DEFAULT 0 COMMENT '博客点赞数',
                     gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '博客创建时间',
                     gmt_modified DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '博客修改时间',
                     PRIMARY KEY (blog_id) USING BTREE,
                     FOREIGN KEY (user_id) REFERENCES user (user_id)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 AUTO_INCREMENT = 30000;
# 保证每个用户不能有两篇及以上相同标题的博客
ALTER TABLE blog ADD UNIQUE KEY (title);


DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
    comment_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论自增id',
    blog_id BIGINT UNSIGNED NOT NULL COMMENT '所属博客id',
    username VARCHAR(64) NOT NULL COMMENT '评论者用户名',
    content VARCHAR(255) NOT NULL COMMENT '评论内容',
    gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '评论发布时间',
    parent_comment_id BIGINT UNSIGNED DEFAULT 0 COMMENT '父评论id',
    PRIMARY KEY (comment_id) USING BTREE,
    FOREIGN KEY (username) REFERENCES user(username),
    FOREIGN KEY (blog_id) REFERENCES blog(blog_id)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 AUTO_INCREMENT = 50000;



DROP TABLE IF EXISTS message;
CREATE TABLE message (
    message_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论自增id',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '',
    username VARCHAR(64) NOT NULL COMMENT '',
    content VARCHAR(255) NOT NULL COMMENT '',
    gmt_create BIGINT UNSIGNED DEFAULT 0 COMMENT '父评论id',
    parent_comment_id BIGINT UNSIGNED DEFAULT 0 COMMENT '父评论id',
    PRIMARY KEY (message_id) USING BTREE,
    FOREIGN KEY (username) REFERENCES user(username),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 AUTO_INCREMENT = 100000;



DROP TABLE IF EXISTS blike;
CREATE TABLE blike (
    blog_id BIGINT UNSIGNED NOT NULL COMMENT '点赞的博客id',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '点赞的用户id',
    FOREIGN KEY (blog_id) REFERENCES blog (blog_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
)ENGINE = InnoDB CHARACTER SET = utf8mb4;
# 同一篇博客一个人只能评论一次
ALTER TABLE blike ADD UNIQUE KEY (blog_id, user_id);


