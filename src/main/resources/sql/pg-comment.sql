DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
    comment_id SERIAL,
    blog_id BIGINT DEFAULT 0,
    username VARCHAR(64) NOT NULL,
    email VARCHAR(64) DEFAULT NULL,
    content VARCHAR(255) NOT NULL,
    gmt_create TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
    parent_comment_id BIGINT DEFAULT 0,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (username) REFERENCES "user"(username)
);
