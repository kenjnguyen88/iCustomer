SELECT * from customers_roles cr ;
SELECT * from customers c ;

-------------------- customers -----------------------
INSERT INTO esoft_customer.customers
(created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES('2024-06-02 08:52:29.529477', 'sys', 'cms_admin@gmail.com', 'super_admin', '$2a$10$tm9D3fZdvct9Q9n9CsCjbuKnkQjlU00uEmsrh7XQwRp5EZiSs45HC', NULL, NULL);
INSERT INTO esoft_customer.customers
(created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES('2024-06-02 08:52:43.550251', 'sys', 'ken.nguyen@gmail.com', 'admin', '$2a$10$jJ.S9e/MmZpWwrokJrdyS.mQDIjJgy60wzrx0Pun4oxTL7RCBBGNu', NULL, NULL);
INSERT INTO esoft_customer.customers
(created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES('2024-06-02 08:52:58.954784', 'sys', 'chi.nguyen@gmail.com', 'Thuy Chi', '$2a$10$FKrzptuPx62yDp2nUsXCGOZXTRtnFS0Q31/Snnk9jHUHy1IZO0zA.', NULL, NULL);
INSERT INTO esoft_customer.customers
(created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES('2024-06-02 08:53:07.240301', 'sys', 'an.nguyen@gmail.com', 'Hoai An', '$2a$10$u03TUzY.p6IAfy9zLDH7o.9DQq9HW.S5b4lgqz0od4kyUtQfleWay', NULL, NULL);

-- roles
-------------------- roles -----------------------

INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('SUPER-ADMIN', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('ADMIN', '2024-06-02 01:36:36.862904', 'sys');


INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('GUEST', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('EDITOR', '2024-06-02 01:36:36.862904', 'sys');

SELECT * from roles;


-- permissions
-------------------- permissions -----------------------
INSERT INTO esoft_customer.permissions
(name, created_at, created_by)
VALUES('PUT', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.permissions
(name, created_at, created_by)
VALUES('POST', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.permissions
(name, created_at, created_by)
VALUES('GET', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.permissions
(name, created_at, created_by)
VALUES('DELETE', '2024-06-02 01:36:36.862904', 'sys');

SELECT * from permissions;


-- resources
-------------------- resources -----------------------
INSERT INTO esoft_customer.resources
(name, url, created_at, created_by)
VALUES('content', 'http://localhost:8080/content/*',  '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.resources
(name, url, created_at, created_by)
VALUES('customer', 'http://localhost:8080/customer/*',  '2024-06-02 01:36:36.862904', 'sys');

SELECT * from resources r ;


-------------------- customers_roles -----------------------

SELECT * from customers c ;
SELECT * from roles r ;

DROP table customers_roles;

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(1, 1, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(1, 2, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(2, 2, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(3, 3, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(4, 4, '2024-06-02 01:36:36.862904', 'sys');

SELECT * from customers c ;
SELECT * from customers_roles;

SELECT * from roles ;
SELECT * from permissions;


-- ADMIN assign role POST, PUT, GET
INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(1, 1, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(2, 1, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(3, 1, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(4, 1, '2024-06-02 01:36:36.862904', 'sys');


-- SUPER-ADMIN assign role POST, PUT, GET, DELETE
INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(1, 2, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(2, 2, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(3, 2, '2024-06-02 01:36:36.862904', 'sys');

SELECT * from roles_permissions ;

-- EDITOR assign role PUT, GET

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(1, 4, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(3, 4, '2024-06-02 01:36:36.862904', 'sys');

-- EDITOR assign role POST, GET
INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(2, 3, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(3, 3, '2024-06-02 01:36:36.862904', 'sys');

SELECT * from roles r ;
SELECT * from permissions p ;
SELECT * from roles_permissions rp ;


-------------------- roles_resources -----------------------
-- asign ADMIN to access customer resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(2, 2, '2024-06-02 01:36:36.862904', 'sys');

-- asign SUPER-ADMIN to access customer resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(2, 1, '2024-06-02 01:36:36.862904', 'sys');

-- asign EDITOR to access content resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(1, 1, '2024-06-02 01:36:36.862904', 'sys');

-- asign GUEST to access content resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(1, 3, '2024-06-02 01:36:36.862904', 'sys');

-- asign GUEST to access content resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(1, 4, '2024-06-02 01:36:36.862904', 'sys');




SELECT * from resources r ;
SELECT * FROM roles r ;
SELECT * from roles_resources rr ;