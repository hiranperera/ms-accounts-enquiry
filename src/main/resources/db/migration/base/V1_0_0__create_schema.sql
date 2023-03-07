CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `account_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `account` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) NOT NULL,
  `account_number` varchar(255) NOT NULL,
  `account_type_id` int(11) NOT NULL,
  `balance_date` datetime DEFAULT NULL,
  `opening_available_balance` double DEFAULT NULL,
  `currency_id` int(11) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_ACCOUNT_NUMBER` (`account_number`),
  CONSTRAINT `FK_ACCOUNT_CURRENCY` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK_ACCOUNT_ACCOUNT_TYPE` FOREIGN KEY (`account_type_id`) REFERENCES `account_type` (`id`)
);

CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `narrative` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `value_date` datetime DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `currency_id` int(11) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  CONSTRAINT `FK_TRANSACTION_ACCOUNT` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FK_TRANSACTION_CURRENCY` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`)
);