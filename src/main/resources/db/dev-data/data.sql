-- delete data
delete from `account-enquiry-db`.`transaction`;
delete from `account-enquiry-db`.`account`;
delete from `account-enquiry-db`.`user`;

-- insert users
INSERT INTO `account-enquiry-db`.`user` (`name`, `code`) VALUES ('Hiran Perera', 'U0001');
INSERT INTO `account-enquiry-db`.`user` (`name`, `code`) VALUES ('Hashini Kithmini', 'U0002');
INSERT INTO `account-enquiry-db`.`user` (`name`, `code`) VALUES ('Mihiran Rupasinghe', 'U0003');

-- insert accounts
INSERT INTO `account-enquiry-db`.`account` (`user_id`, `currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`)
VALUES (
    (select `id` from `account-enquiry-db`.`user` where `code` = 'U0001'),
    (select `id` from `account-enquiry-db`.`currency` where `name` = 'AUD'),
    'ACCNAME_OFFICE_1',
    'ACCNUMBER_123455',
    (select `id` from `account-enquiry-db`.`account_type` where `name` = 'Savings'),
    '2023-03-03',
    3500.78);

INSERT INTO `account-enquiry-db`.`account` (`user_id`, `currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`)
VALUES (
    (select `id` from `account-enquiry-db`.`user` where `code` = 'U0003'),
    (select `id` from `account-enquiry-db`.`currency` where `name` = 'AUD'),
    'ACCNAME_PERSONAL_1',
    'ACCNUMBER_123456',
    (select `id` from `account-enquiry-db`.`account_type` where `name` = 'Savings'),
    '2023-03-02',
    6800.57);

INSERT INTO `account-enquiry-db`.`account` (`user_id`, `currency_id`, `account_name`, `account_number`, `account_type_id`, `balance_date`, `opening_available_balance`)
VALUES (
    (select `id` from `account-enquiry-db`.`user` where `code` = 'U0003'),
    (select `id` from `account-enquiry-db`.`currency` where `name` = 'AUD'),
    'ACCNAME_PERSONAL_2',
    'ACCNUMBER_123457',
    (select `id` from `account-enquiry-db`.`account_type` where `name` = 'Savings'),
    '2023-03-01',
    9000.33);

-- insert transactions
INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`)
VALUES (
    100.35,
    (select `id` from `account-enquiry-db`.`currency` where `name` = 'AUD'),
    'test transaction',
    1,
    '2023-03-01',
    (select `account_id` from `account-enquiry-db`.`account` where `account_number` = 'ACCNUMBER_123457'));

INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`)
VALUES (
    700.50,
    (select `id` from `account-enquiry-db`.`currency` where `name` = 'AUD'),
    'test transaction',
    2,
    '2023-03-02',
    (select `account_id` from `account-enquiry-db`.`account` where `account_number` = 'ACCNUMBER_123457'));

INSERT INTO `account-enquiry-db`.`transaction` (`amount`, `currency_id`, `narrative`, `type`, `value_date`, `account_id`)
VALUES (
    158.45,
    (select `id` from `account-enquiry-db`.`currency` where `name` = 'SGD'),
    'test transaction',
    1,
    '2023-03-03',
    (select `account_id` from `account-enquiry-db`.`account` where `account_number` = 'ACCNUMBER_123455'));