-- voting_system.voting_record definition

CREATE TABLE `voting_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `voter_name` varchar(100) NOT NULL,
  `item_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_record_item` (`item_id`),
  CONSTRAINT `fk_record_item` FOREIGN KEY (`item_id`) REFERENCES `voting_item` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO voting_record (voter_name, item_id) VALUES ('Leo', 1);
INSERT INTO voting_record (voter_name, item_id) VALUES ('Sandy', 1);
INSERT INTO voting_record (voter_name, item_id) VALUES ('Sandy', 2);
INSERT INTO voting_record (voter_name, item_id) VALUES ('Randy', 2);
INSERT INTO voting_record (voter_name, item_id) VALUES ('RSY', 2);
