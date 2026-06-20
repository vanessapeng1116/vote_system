-- voting_system.voting_item definition

CREATE TABLE `voting_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO voting_item (id, name) VALUES (1, '電腦');
INSERT INTO voting_item (id, name) VALUES (2, '滑鼠');
