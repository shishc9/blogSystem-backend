DROP DATABASE IF EXISTS blogsys;
CREATE DATABASE blogsys;
use blogsys;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
                     user_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                     username VARCHAR(64) DEFAULT NULL,
                     password VARCHAR(255) DEFAULT NULL,
                     is_main_person TINYINT DEFAULT 0,
                     age INT(2) UNSIGNED DEFAULT NULL,
                     gender CHAR(6) NOT NULL NULL DEFAULT 'MALE',
                     hobby TEXT,
                     email VARCHAR(64) DEFAULT NULL,
                     gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP,
                     gmt_last_login DATETIME(0) DEFAULT CURRENT_TIMESTAMP,
                     PRIMARY KEY (user_id) USING BTREE,
                     CONSTRAINT ck CHECK (gender = 'MALE' or gender = 'FEMALE')
)ENGINE = InnoDB CHARACTER SET = UTF8MB3;

ALTER TABLE user ADD UNIQUE KEY (username);


DROP TABLE IF EXISTS blog;
CREATE TABLE blog(
                     blog_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                     username VARCHAR(64) DEFAULT 'admin',
                     title VARCHAR(255) NOT NULL,
                     content LONGTEXT,
                     status  TINYINT UNSIGNED NOT NULL DEFAULT 0,
                     read_num INT UNSIGNED DEFAULT 0,
                     like_num INT UNSIGNED DEFAULT 0,
                     gmt_create DATETIME(0) DEFAULT CURRENT_TIMESTAMP,
                     gmt_modified DATETIME(0) DEFAULT CURRENT_TIMESTAMP,
                     PRIMARY KEY (blog_id) USING BTREE
)ENGINE = InnoDB CHARACTER SET = UTF8MB3 AUTO_INCREMENT = 1000;

ALTER TABLE blog ADD UNIQUE KEY (username, title);

