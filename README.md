# BE-CookingSite

Backend for a simple news blog application created using Spring Boot,Spring Data JPA, Spring Security on the backend, SQL for database and Angular 7 for frontend.

Link to frontend:https://github.com/BolteAdrian/FE-CookingSite

DATABASE:

CREATE TABLE `post` (

  `id` bigint(20) NOT NULL,
  
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_romanian_ci DEFAULT NULL,
  
  `short_description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_romanian_ci DEFAULT NULL,
  
  `created_on` datetime DEFAULT NULL,
  
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_romanian_ci DEFAULT NULL,
  
  `category` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_romanian_ci DEFAULT NULL,
  
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  
  `method_of_preparation`	longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_romanian_ci DEFAULT NULL,
  
  `ingredients` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_romanian_ci DEFAULT NULL,
  
  `updated_on` datetime DEFAULT NULL,
  
  `username` varchar(255) DEFAULT NULL
  
) 

CREATE TABLE `user` (

  `id` bigint(20) NOT NULL,
  
  `email` varchar(255) DEFAULT NULL,
  
  `password` varchar(255) DEFAULT NULL,
  
  `user_name` varchar(255) DEFAULT NULL
  
)
