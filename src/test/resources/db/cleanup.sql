-- delete data
delete from `account-enquiry-db`.`transaction`;
delete from `account-enquiry-db`.`account`;
delete from `account-enquiry-db`.`user`;

alter table `account-enquiry-db`.`account` AUTO_INCREMENT = 1;