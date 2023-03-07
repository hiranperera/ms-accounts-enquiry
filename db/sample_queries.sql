use `account-enquiry-db`;

select * from account;
select * from transaction;

INSERT INTO `account-enquiry-db`.`account` (`account_id`, `currency`, `account_name`, `account_number`, `account_type`, `balance_date`, `opening_available_balance`) VALUES (1,  1, 'hiran', '123455', 1, '2023-03-03', 3500.78);
INSERT INTO `account-enquiry-db`.`account` (`account_id`, `currency`, `account_name`, `account_number`, `account_type`, `balance_date`, `opening_available_balance`) VALUES (2,  1, 'hashini', '123456', 1, '2023-03-02', 6800.57);
INSERT INTO `account-enquiry-db`.`account` (`account_id`, `currency`, `account_name`, `account_number`, `account_type`, `balance_date`, `opening_available_balance`) VALUES (3,  1, 'mihiran', '123457', 1, '2023-03-01', 9000.33);

INSERT INTO `account-enquiry-db`.`transaction` (`transaction_id`, `amount`, `currency`, `narrative`, `type`, `value_date`, `account_id`) VALUES (1, 100.35, 1, 'test transaction', 1, '2023-03-01', 3);
INSERT INTO `account-enquiry-db`.`transaction` (`transaction_id`, `amount`, `currency`, `narrative`, `type`, `value_date`, `account_id`) VALUES (2, 700.50, 1, 'test transaction', 1, '2023-03-02', 3);
INSERT INTO `account-enquiry-db`.`transaction` (`transaction_id`, `amount`, `currency`, `narrative`, `type`, `value_date`, `account_id`) VALUES (3, 158.45, 1, 'test transaction', 1, '2023-03-03', 3);