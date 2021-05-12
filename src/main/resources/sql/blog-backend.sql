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
                     hobby TEXT COMMENT '爱好',
                     email VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
                     gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
                     gmt_last_login DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '用户上次登陆时间',
                     PRIMARY KEY (user_id) USING BTREE,
                     CONSTRAINT ck CHECK (gender = 'MALE' or gender = 'FEMALE')
)ENGINE = InnoDB CHARACTER SET = utf8mb4;

# 保证数据库中用户名唯一
# 一个邮箱只能绑定一个用户 虽然邮箱好像没啥用(目前来看)
ALTER TABLE user ADD UNIQUE KEY (username);
ALTER TABLE user ADD UNIQUE KEY (email);


DROP TABLE IF EXISTS blog;
CREATE TABLE blog(
                     blog_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                     username VARCHAR(64) DEFAULT 'admin' COMMENT '发布博客的用户，目前只能是admin',
                     title VARCHAR(255) NOT NULL COMMENT '标题',
                     content LONGTEXT COMMENT '正文',
                     status  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '博客状态，只有0/1，0：公开，1：私有',
                     read_num INT UNSIGNED DEFAULT 0 COMMENT '博客阅读数',
                     like_num INT UNSIGNED DEFAULT 0 COMMENT '博客点赞数',
                     gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '博客创建时间',
                     gmt_modified DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '博客修改时间',
                     PRIMARY KEY (blog_id) USING BTREE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 AUTO_INCREMENT = 1000;

# 保证每个用户不能有两篇及以上相同标题的博客
# 其实目前规划能写博客的只有admin
ALTER TABLE blog ADD UNIQUE KEY (username, title);


DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
    comment_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    blog_id BIGINT UNSIGNED DEFAULT NULL COMMENT '',
    username VARCHAR(64) NOT NULL COMMENT '',
    email VARCHAR(64) DEFAULT NULL COMMENT '',
    content VARCHAR(255) NOT NULL COMMENT '',
    gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP COMMENT '',
    parent_comment_id BIGINT UNSIGNED DEFAULT 0,
#    parent_username VARCHAR(64) DEFAULT NULL,
    PRIMARY KEY (comment_id) USING BTREE,
    FOREIGN KEY (blog_id) REFERENCES blog(blog_id),
    FOREIGN KEY (username) REFERENCES user(username)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 AUTO_INCREMENT = 5000;


