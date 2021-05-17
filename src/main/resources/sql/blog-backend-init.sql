use blogsys;

INSERT INTO user(username, password, user_identity, age, gender, email)
    VALUES('admin', '123456', 1, 20, 'MALE',  '123456789@126.com');
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
    VALUES('admin2', '123456', 1, 20, 'MALE',  '123456780@126.com');



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
    VALUES('30000', 'admin', 'first comment', 0);
INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'admin', 'second comment', 0);

INSERT INTO comment(blog_id, username, content, parent_comment_id)
    VALUES('30000', 'A', 'reply admin', 50001);

INSERT INTO comment(username, content, gmt_create, parent_comment_id)
    VALUES('B', 'leave message1', NOW(), 0);
INSERT INTO comment(username, content, gmt_create, parent_comment_id)
    VALUES('A', 'leave message2', NOW(), 0);
INSERT INTO comment(username, content, gmt_create, parent_comment_id)
    VALUES('C', 'reply B leave message', NOW(), 50002);


