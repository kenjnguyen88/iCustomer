CREATE DATABASE `esoft_customer` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- esoft_customer.customers definition

CREATE TABLE `customers` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- esoft_customer.customers_seq definition

CREATE TABLE `customers_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- esoft_customer.security_tokens definition

CREATE TABLE `security_tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) NOT NULL,
  `access_token_expires_at` datetime(6) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  `issued_at` datetime(6) NOT NULL,
  `refresh_token` varchar(255) NOT NULL,
  `refresh_token_expires_at` datetime(6) NOT NULL,
  `status` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
    `created_at` datetime(6) DEFAULT NULL,
    `created_by` varchar(255) DEFAULT NULL,
    `updated_at` datetime(6) DEFAULT NULL,
    `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- esoft_customer.resources definition
CREATE TABLE `resources` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(2550) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `customers_roles` (
  `customer_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
      `created_at` datetime(6) DEFAULT NULL,
      `created_by` varchar(255) DEFAULT NULL,
      `updated_at` datetime(6) DEFAULT NULL,
      `updated_by` varchar(255) DEFAULT NULL,
  KEY `customer_fk_idx` (`customer_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roles_permissions` (
  `permission_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
      `created_at` datetime(6) DEFAULT NULL,
      `created_by` varchar(255) DEFAULT NULL,
      `updated_at` datetime(6) DEFAULT NULL,
      `updated_by` varchar(255) DEFAULT NULL,
  KEY `permission_fk_idx` (`permission_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `role_p_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `permission_fk` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- esoft_customer.roles_resources definition

CREATE TABLE `roles_resources` (
  `resource_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  KEY `resource_fk_idx` (`resource_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `resource_fk` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`),
  CONSTRAINT `role_resource_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;