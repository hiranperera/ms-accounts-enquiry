CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL UNIQUE,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `account` ADD COLUMN `user_id` int(11) AFTER `account_id`;
ALTER TABLE `account` ADD CONSTRAINT FK_ACCOUNT_USER FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);