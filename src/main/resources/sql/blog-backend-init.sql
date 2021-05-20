use blogsys;

INSERT INTO user(username, password, user_identity, age, gender, email)
    VALUES('blogger1', '123456', 0, 20, 'MALE',  '123456789@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('A', '111111', 20, '111111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('B', '111111', 20, '121111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('C', '111111', 20, '131111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('D', '111111', 20, '141111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('E', '111111', 20, '151111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('F', '111111', 20, '161111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('G', '111111', 20, '171111111@126.com');
INSERT INTO user(username, password, age, email)
    VALUES('H', '111111', 20, '181111111@126.com');
INSERT INTO user(username, password, user_identity, age, gender, email)
    VALUES('blogger2', '123456', 0, 20, 'MALE',  '123456780@126.com');
INSERT INTO user(username, password, user_identity, age, gender, email)
    VALUES('admin2', '123456', 1, 20, 'MALE',  'shishc9@126.com');




INSERT INTO blog(user_id, title, content) values (1, '第一篇博客', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'Java基础', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'Java进阶', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'Java高级', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'JVM', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, '高并发', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, '分布式', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (1, 'test', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (10, 'admin21', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (10, 'admin22', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (10, 'admin23', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (10, 'admin24', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (10, 'admin25', '应该能正常发出去吧');
INSERT INTO blog(user_id, title, content) values (10, 'admin26', '应该能正常发出去吧');



INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'D', 'first comment', 0);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'D', 'second comment', 0);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'A', 'reply D', 50001);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'A', 'reply D', 50001);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'A', 'reply D', 50003);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'A', 'reply D', 50004);

INSERT INTO message(user_id, username, content, gmt_create, parent_comment_id)
    VALUES(3, 'B', 'leave message1', NOW(), 0);
INSERT INTO message(user_id, username, content, gmt_create, parent_comment_id)
    VALUES(3, 'A', 'leave message2', NOW(), 0);
INSERT INTO message(user_id, username, content, gmt_create, parent_comment_id)
    VALUES(3, 'C', 'reply B leave message', NOW(), 50002);


INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'INSERT');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'BLOG', 'DELETE');


INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'USER', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'USER', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'USER', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(1, 'USER', 'DELETE');
