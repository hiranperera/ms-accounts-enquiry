use `account-enquiry-db`;

select * from account;
select * from transaction;
select * from currency;
select * from account_type;

INSERT INTO `account-enquiry-db`.`currency` (`name`) VALUES ('AUD');
INSERT INTO `account-enquiry-db`.`currency` (`name`) VALUES ('SGD');


INSERT INTO `account-enquiry-db`.`account` (`currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`) VALUES (1, 'hiran', '123455', 1, '2023-03-03', 3500.78);
INSERT INTO `account-enquiry-db`.`account` (`currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`) VALUES (1, 'hashini', '123456', 2, '2023-03-02', 6800.57);
INSERT INTO `account-enquiry-db`.`account` (`currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`) VALUES (1, 'mihiran', '123457', 1, '2023-03-01', 9000.33);

INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`) VALUES (100.35, 1, 'test transaction', 1, '2023-03-01', 3);
INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`) VALUES (700.50, 1, 'test transaction', 1, '2023-03-02', 3);
INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`) VALUES (158.45, 1, 'test transaction', 1, '2023-03-03', 3);
