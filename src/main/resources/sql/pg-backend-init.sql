
INSERT INTO users(username, password, user_identity, email)
    VALUES('Admin', '75a78fe7aa80367deb601a235e0d8699', 1, 'admin@example.com');

INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'UPDATE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'DELETE');
INSERT INTO perms(user_identity, entity, perm)
    VALUES(0, 'BLOG', 'INSERT');
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
    VALUES(0, 'CHANGE', 'UPDATE');
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
