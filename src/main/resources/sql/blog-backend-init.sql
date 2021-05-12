use blogsys;

INSERT INTO user(username, password, user_identity, age, gender, hobby, email)
    VALUES('admin', '123456', 1, 20, 'MALE', 'coding', '123456789@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('A', '111111', 20, 'noHobby', '111111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('B', '111111', 20, 'noHobby', '121111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('C', '111111', 20, 'noHobby', '131111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('D', '111111', 20, 'noHobby', '141111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('E', '111111', 20, 'noHobby', '151111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('F', '111111', 20, 'noHobby', '161111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('G', '111111', 20, 'noHobby', '171111111@126.com');
INSERT INTO user(username, password, age, hobby, email)
    VALUES('H', '111111', 20, 'noHobby', '181111111@126.com');


INSERT INTO blog(title, content) values ('第一篇博客', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('Java基础', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('Java进阶', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('Java高级', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('JVM', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('高并发', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('分布式', '应该能正常发出去吧');
INSERT INTO blog(title, content) values ('test', '应该能正常发出去吧');


INSERT INTO comment(username, email, content, gmt_create, parent_comment_id)
    VALUES('admin', '123456789@126.com', 'first comment', NOW(), 0);
INSERT INTO comment(username, email, content, gmt_create, parent_comment_id)
    VALUES('A','111111111@126.com', 'second comment', NOW(), 0);
INSERT INTO comment(username, email, content, gmt_create, parent_comment_id)
    VALUES('B','121111111@126.com', 'reply A', NOW(), 5001);
INSERT INTO comment(username, email, content, gmt_create, parent_comment_id)
    VALUES('A','111111111@126.com', 'reply B', NOW(), 5002);

