INSERT INTO esoft_authent.roles
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'SUPER_ADMIN');

INSERT INTO esoft_authent.roles
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'ADMIN');

INSERT INTO esoft_authent.roles
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'EDITOR');


INSERT INTO esoft_authent.roles
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'GUEST');


SELECT * from roles r  ;



INSERT INTO esoft_authent.permissions
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'POST');

INSERT INTO esoft_authent.permissions
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'PUT');

INSERT INTO esoft_authent.permissions
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'GET');

INSERT INTO esoft_authent.permissions
(created_at, created_by, name)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'DELETE');

SELECT * from permissions p ;



INSERT INTO esoft_authent.resources
(created_at, created_by, name, url)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'admin', "http://localhost:8080/admin");

INSERT INTO esoft_authent.resources
(created_at, created_by, name, url)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'super-admin', "http://localhost:8080/admin");

INSERT INTO esoft_authent.resources
(created_at, created_by, name, url)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'editor', "http://localhost:8080/content");

INSERT INTO esoft_authent.resources
(created_at, created_by, name, url)
VALUES('2024-06-12 14:47:50.100398', 'sys', 'guest', "http://localhost:8080/content");

SELECT * from resources r  ;