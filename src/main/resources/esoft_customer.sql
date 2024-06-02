INSERT INTO esoft_customer.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES(53, '2024-06-02 01:50:02.280422', 'sys', 'chi.nt@gmail.com', 'Nguyen Thuỳ Chi', '$2a$10$FCk3aE7nU5nFUupu1zySYeAMsJCLiY6Wj3P4JcQtRDH9UqL.4Oo2q', NULL, NULL);
INSERT INTO esoft_customer.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES(54, '2024-06-02 01:50:14.865443', 'sys', 'an.nt@gmail.com', 'Nguyen Hoài An', '$2a$10$pKXi7kuAmgVaCUek7rckWeY21GTrRvV4N3LRDe.p/kc5VaLKNZ.bu', NULL, NULL);
INSERT INTO esoft_customer.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by)
VALUES(55, '2024-06-02 01:50:27.859519', 'sys', 'ken.nt@gmail.com', 'Nguyen Huy Anh', '$2a$10$d.lYOGWxIj7wVpS093NFhepJelDdsIcarOTjYxKJ4eHURuC.bhyd6', NULL, NULL);

-- roles
-------------------- roles -----------------------
INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('ADMIN', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('SUPER-ADMIN', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('GUEST', '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.roles
(name, created_at, created_by)
VALUES('EDITOR', '2024-06-02 01:36:36.862904', 'sys');


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


-- resources
-------------------- resources -----------------------
INSERT INTO esoft_customer.resources
(name, url, created_at, created_by)
VALUES('content', 'http://localhost:8080/content/*',  '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.resources
(name, url, created_at, created_by)
VALUES('customer', 'http://localhost:8080/customer/*',  '2024-06-02 01:36:36.862904', 'sys');


-------------------- customers_roles -----------------------
INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(2, 3, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(3, 4, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(1, 1, '2024-06-02 01:36:36.862904', 'sys');

INSERT INTO esoft_customer.customers_roles
(customer_id, role_id, created_at, created_by)
VALUES(1, 2, '2024-06-02 01:36:36.862904', 'sys');


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

INSERT INTO esoft_customer.roles_permissions
(permission_id, role_id, created_at, created_by)
VALUES(4, 2, '2024-06-02 01:36:36.862904', 'sys');


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
VALUES(2, 1, '2024-06-02 01:36:36.862904', 'sys');

-- asign SUPER-ADMIN to access customer resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(2, 2, '2024-06-02 01:36:36.862904', 'sys');

-- asign EDITOR to access content resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(1, 1, '2024-06-02 01:36:36.862904', 'sys');

-- asign GUEST to access content resource
INSERT INTO esoft_customer.roles_resources
(resource_id, role_id, created_at, created_by)
VALUES(1, 3, '2024-06-02 01:36:36.862904', 'sys');

SELECT * from resources r ;
SELECT * FROM roles r ;
SELECT * from roles_resources rr ;