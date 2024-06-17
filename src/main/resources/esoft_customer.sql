INSERT INTO esoft_authent.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by, auth_type, enabled, username)
VALUES(1, '2024-06-14 17:09:22.359574', 'sys', 'supper.admin@gmail.com', 'supper admin', '$2a$10$ClUxiRR7cELqnx6KKcgy7esgYlx8TBz23WDQp3bGnR67pnFw0HZLm', NULL, NULL, NULL, 0, NULL);
INSERT INTO esoft_authent.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by, auth_type, enabled, username)
VALUES(2, '2024-06-14 17:10:04.224889', 'sys', 'ken.nguyen@gmail.com', 'ken Nguyen', '$2a$10$j84G2gC0biFhxt3J4qJv9eRuUs7tDtbc/amVsLmG0kODf1eJVydWC', NULL, NULL, NULL, 0, NULL);
INSERT INTO esoft_authent.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by, auth_type, enabled, username)
VALUES(3, '2024-06-14 17:10:13.968038', 'sys', 'chi.nguyen@gmail.com', 'Thuy Chi Nguyen', '$2a$10$RUFhds5o0BcwB1QAWspQVezUh/3aDSMk2KuZ4AabdPZB/FiVxoQXu', NULL, NULL, NULL, 0, NULL);
INSERT INTO esoft_authent.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by, auth_type, enabled, username)
VALUES(4, '2024-06-14 17:10:24.401111', 'sys', 'an.nguyen@gmail.com', 'Hoai An Nguyen', '$2a$10$MMT5u4XZecpiAZI0uRsOX.CQSd/kUvaOWTE0dN2uCHdwIaJKhIYAG', NULL, NULL, NULL, 0, NULL);
INSERT INTO esoft_authent.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by, auth_type, enabled, username)
VALUES(5, '2024-06-14 17:10:39.006375', 'sys', 'thu.nguyen@gmail.com', 'Minh Thu Nguyen', '$2a$10$sGiX93X9QnfSuONQnWh1qOjqFHzPP5puBnulSBFNRV5RrJxvCXVWW', NULL, NULL, NULL, 0, NULL);
INSERT INTO esoft_authent.customers
(id, created_at, created_by, email, full_name, password, updated_at, updated_by, auth_type, enabled, username)
VALUES(6, '2024-06-14 17:10:46.831858', 'sys', 'quan.nguyen@gmail.com', 'Minh Quan Nguyen', '$2a$10$UNXdMSToyfYVH4Y.BsyTSekmbK5zrbfO1M1W1IrfJN75OyMih9Wt6', NULL, NULL, NULL, 0, NULL);


INSERT INTO esoft_authent.roles
(id, created_at, created_by, name, updated_at, updated_by, role_id)
VALUES(1, '2024-06-12 14:47:50.100398', 'sys', 'SUPER_ADMIN', NULL, NULL, 0);
INSERT INTO esoft_authent.roles
(id, created_at, created_by, name, updated_at, updated_by, role_id)
VALUES(2, '2024-06-12 14:47:50.100398', 'sys', 'ADMIN', NULL, NULL, 0);
INSERT INTO esoft_authent.roles
(id, created_at, created_by, name, updated_at, updated_by, role_id)
VALUES(3, '2024-06-12 14:47:50.100398', 'sys', 'EDITOR', NULL, NULL, 0);
INSERT INTO esoft_authent.roles
(id, created_at, created_by, name, updated_at, updated_by, role_id)
VALUES(4, '2024-06-12 14:47:50.100398', 'sys', 'GUEST', NULL, NULL, 0);


-- esoft_authent.permissions definition

CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO esoft_authent.permissions
(id, created_at, created_by, name, updated_at, updated_by)
VALUES(1, '2024-06-12 14:47:50.100398', 'sys', 'POST', NULL, NULL);
INSERT INTO esoft_authent.permissions
(id, created_at, created_by, name, updated_at, updated_by)
VALUES(2, '2024-06-12 14:47:50.100398', 'sys', 'PUT', NULL, NULL);
INSERT INTO esoft_authent.permissions
(id, created_at, created_by, name, updated_at, updated_by)
VALUES(3, '2024-06-12 14:47:50.100398', 'sys', 'GET', NULL, NULL);
INSERT INTO esoft_authent.permissions
(id, created_at, created_by, name, updated_at, updated_by)
VALUES(4, '2024-06-12 14:47:50.100398', 'sys', 'DELETE', NULL, NULL);


INSERT INTO esoft_authent.customers_roles
(id, created_at, created_by, customer_id, role_id, updated_at, updated_by)
VALUES(1, '2024-06-12 14:47:50.100398', 'sys', 1, 1, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.customers_roles
(id, created_at, created_by, customer_id, role_id, updated_at, updated_by)
VALUES(2, '2024-06-16 09:01:59.332176', 'sys', 3, 3, NULL, NULL);
INSERT INTO esoft_authent.customers_roles
(id, created_at, created_by, customer_id, role_id, updated_at, updated_by)
VALUES(3, '2024-06-16 09:06:36.698345', 'sys', 2, 2, NULL, NULL);
INSERT INTO esoft_authent.customers_roles
(id, created_at, created_by, customer_id, role_id, updated_at, updated_by)
VALUES(4, '2024-06-16 14:20:18.565594', 'sys', 4, 4, NULL, NULL);
INSERT INTO esoft_authent.customers_roles
(id, created_at, created_by, customer_id, role_id, updated_at, updated_by)
VALUES(5, '2024-06-16 14:20:41.310506', 'sys', 5, 4, NULL, NULL);
INSERT INTO esoft_authent.customers_roles
(id, created_at, created_by, customer_id, role_id, updated_at, updated_by)
VALUES(6, '2024-06-16 14:20:45.962631', 'sys', 6, 4, NULL, NULL);


INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(1, '2024-06-12 14:47:50.100398', 'sys', 1, 1, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(2, '2024-06-12 14:47:50.100398', 'sys', 2, 1, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(3, '2024-06-12 14:47:50.100398', 'sys', 3, 1, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(4, '2024-06-12 14:47:50.100398', 'sys', 4, 1, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(5, '2024-06-12 14:47:50.100398', 'sys', 1, 2, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(6, '2024-06-12 14:47:50.100398', 'sys', 2, 2, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(7, '2024-06-12 14:47:50.100398', 'sys', 3, 2, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(8, '2024-06-12 14:47:50.100398', 'sys', 1, 3, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(9, '2024-06-12 14:47:50.100398', 'sys', 2, 3, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(10, '2024-06-12 14:47:50.100398', 'sys', 3, 3, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(11, '2024-06-12 14:47:50.100398', 'sys', 1, 4, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(12, '2024-06-12 14:47:50.100398', 'sys', 2, 4, '2024-06-12 14:47:50.100398', 'sys');
INSERT INTO esoft_authent.roles_permissions
(id, created_at, created_by, permission_id, role_id, updated_at, updated_by)
VALUES(13, '2024-06-12 14:47:50.100398', 'sys', 3, 4, '2024-06-12 14:47:50.100398', 'sys');


