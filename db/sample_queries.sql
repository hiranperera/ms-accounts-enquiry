use `account-enquiry-db`;

select * from account;
select * from transaction;
select * from currency;
select * from account_type;
select * from user;

delete from `account-enquiry-db`.`transaction`;
delete from `account-enquiry-db`.`account`;
delete from `account-enquiry-db`.`user`;


INSERT INTO `account-enquiry-db`.`currency` (`name`) VALUES ('AUD');
INSERT INTO `account-enquiry-db`.`currency` (`name`) VALUES ('SGD');

INSERT INTO `account-enquiry-db`.`user` (`name`, `code`) VALUES ('Hiran', 'U0001');
INSERT INTO `account-enquiry-db`.`user` (`name`, `code`) VALUES ('Hashini', 'U0002');
INSERT INTO `account-enquiry-db`.`user` (`name`, `code`) VALUES ('Mihiran', 'U0003');


INSERT INTO `account-enquiry-db`.`account` (`user_id`, `currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`) VALUES (1, 1, 'Savings', '123455', 1, '2023-03-03', 3500.78);
INSERT INTO `account-enquiry-db`.`account` (`user_id`, `currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`) VALUES (3, 1, 'Savings', '123456', 2, '2023-03-02', 6800.57);
INSERT INTO `account-enquiry-db`.`account` (`user_id`, `currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`) VALUES (3, 1, 'Current', '123457', 1, '2023-03-01', 9000.33);

INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`) VALUES (100.35, 1, 'test transaction', 1, '2023-03-01', 3);
INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`) VALUES (700.50, 1, 'test transaction', 2, '2023-03-02', 3);
INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`) VALUES (158.45, 1, 'test transaction', 1, '2023-03-03', 1);
