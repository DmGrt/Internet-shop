CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(16) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `price` DOUBLE NOT NULL,
  `is_deleted` TINYINT NOT NULL DEFAULT 0 AFTER `price`,
  PRIMARY KEY (`product_id`));

INSERT INTO `internet_shop`.`products` (`name`, `price`) VALUES ('xiaomi', '5000');
INSERT INTO `internet_shop`.`products` (`name`, `price`) VALUES ('samsung', '7500');
INSERT INTO `internet_shop`.`products` (`name`, `price`) VALUES ('apple', '10000');
