CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`
(
    `product_id` BIGINT(16)   NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(256) NOT NULL,
    `price`      DOUBLE       NOT NULL,
    `is_deleted` TINYINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `internet_shop`.`users`
(
    `user_id`    BIGINT        NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(256)  NOT NULL,
    `login`      VARCHAR(256)  NOT NULL,
    `password`   VARCHAR(256)  NOT NULL,
    `salt`       VARBINARY(16) NOT NULL,
    `is_deleted` TINYINT       NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
);

CREATE TABLE `internet_shop`.`orders`
(
    `order_id`   BIGINT  NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT  NOT NULL,
    `is_deleted` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`order_id`),
    INDEX `user_order_key_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `user_order_key`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`roles`
(
    `role_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`role_id`)
);

INSERT INTO `internet_shop`.`roles` (`role_name`)
VALUES ('admin');
INSERT INTO `internet_shop`.`roles` (`role_name`)
VALUES ('user');

CREATE TABLE `internet_shop`.`users_roles`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL DEFAULT 2,
    INDEX `user_users_roles_idx` (`user_id` ASC) VISIBLE,
    INDEX `role_users_roles_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `user_users_roles`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `role_users_roles`
        FOREIGN KEY (`role_id`)
            REFERENCES `internet_shop`.`roles` (`role_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`orders_products`
(
    `order_id`   BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    INDEX `orders_orders_products_idx` (`order_id` ASC) VISIBLE,
    INDEX `products_orders_products_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `ordesr_orders_products`
        FOREIGN KEY (`order_id`)
            REFERENCES `internet_shop`.`orders` (`order_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `products_orders_products`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`shopping_carts`
(
    `cart_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT      NOT NULL,
    `is_deleted` VARCHAR(45) NOT NULL DEFAULT 0,
    PRIMARY KEY (`cart_id`),
    INDEX `user_shopping_cart_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `user_shopping_cart`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`shopping_carts_products`
(
    `cart_id`    BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    INDEX `products_shopping_carts_products_idx` (`product_id` ASC) VISIBLE,
    INDEX `carts_carts_products_idx` (`cart_id` ASC) VISIBLE,
    CONSTRAINT `products_carts_products`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `carts_carts_products`
        FOREIGN KEY (`cart_id`)
            REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

INSERT INTO `internet_shop`.`products` (`name`, `price`)
VALUES ('xiaomi', '5000');
INSERT INTO `internet_shop`.`products` (`name`, `price`)
VALUES ('samsung', '7500');
INSERT INTO `internet_shop`.`products` (`name`, `price`)
VALUES ('apple', '10000');
