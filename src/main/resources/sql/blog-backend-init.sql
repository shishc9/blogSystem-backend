use blogsys;

# USER INIT
INSERT INTO user(username, password, email, avatar, post_count)
    VALUES('user1', '02571c02bcabe4bfc42be1306ae9ff9c', '123456780@126.com', 'https://i.loli.net/2021/05/25/vowpYGb6ND1I3ET.jpg', 8);
INSERT INTO user(username, password, email, avatar)
    VALUES('user2', '02571c02bcabe4bfc42be1306ae9ff9c', '123456781@126.com', 'https://i.loli.net/2021/05/25/vowpYGb6ND1I3ET.jpg');
INSERT INTO user(username, password, email, avatar)
    VALUES('user3', '02571c02bcabe4bfc42be1306ae9ff9c', '123456782@126.com', 'https://i.loli.net/2021/05/25/vowpYGb6ND1I3ET.jpg');
INSERT INTO user(username, password, email, avatar)
    VALUES('user4', '02571c02bcabe4bfc42be1306ae9ff9c', '123456783@126.com', 'https://i.loli.net/2021/05/25/vowpYGb6ND1I3ET.jpg');
INSERT INTO user(username, password, user_identity, email, user_site, avatar)
    VALUES('admin', 'db18a83510dbb5a3d38e5cec4ab1b047', 1, 'shishc9@126.com', 'https://github.com/shishc9', 'https://i.loli.net/2021/05/25/vowpYGb6ND1I3ET.jpg');

# BLOG INIT
INSERT INTO blog(user_id, title, content, comment_num) values (1, '第一篇博客', '应该能正常发出去吧', 6);
INSERT INTO blog(user_id, title, content) values (1, 'Java基础', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'Java进阶', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'Java高级', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'JVM', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, '高并发', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, '分布式', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'test', '应该能正常发出去吧');

# COMMENT INIT
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'user1', 'first comment', 0);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'user1', 'second comment', 0);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'user2', 'reply user1', 50001);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'user1', 'reply user2', 50001);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'user1', 'reply user2', 50003);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'user1', 'reply user2', 50004);


# PERMS INIT
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'CREATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'USER', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'USER', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'COMMENT', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'COMMENT', 'CREATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'LIKE', 'CREATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'LIKE', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'LIKE', 'READ');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'CHANGE', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'ATTENTION', 'CREATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'ATTENTION', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'ATTENTION', 'READ');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'COLLECTION', 'CREATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'COLLECTION', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'COLLECTION', 'READ');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'BLOG', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'USER', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'USER', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'COMMENT', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'CHANGE', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'MANAGE', 'READ');
