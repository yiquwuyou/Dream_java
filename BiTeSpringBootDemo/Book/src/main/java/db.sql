-- 创建数据库
DROP DATABASE IF EXISTS book_test;
CREATE DATABASE book_test DEFAULT CHARACTER SET utf8mb4;
use book_test;
-- 用户表
DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info (
                           `id` INT NOT NULL AUTO_INCREMENT,
                           `user_name` VARCHAR ( 128 ) NOT NULL,
                           `password` VARCHAR ( 128 ) NOT NULL,
                           `delete_flag` TINYINT ( 4 ) NULL DEFAULT 0,
                           `create_time` DATETIME DEFAULT now(),
                           `update_time` DATETIME DEFAULT now() ON UPDATE now(),
                           PRIMARY KEY ( `id` ),
                           UNIQUE INDEX `user_name_UNIQUE` ( `user_name` ASC )) ENGINE = INNODB DEFAULT CHARACTER
SET = utf8mb4 COMMENT = '用户表';
-- 图书表
DROP TABLE IF EXISTS book_info;
CREATE TABLE `book_info` (
                             `id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
                             `book_name` VARCHAR ( 127 ) NOT NULL,
                             `author` VARCHAR ( 127 ) NOT NULL,
                             `count` INT ( 11 ) NOT NULL,
                             `price` DECIMAL (7,2 ) NOT NULL,
                             `publish` VARCHAR ( 256 ) NOT NULL,
                             `status` TINYINT ( 4 ) DEFAULT 1 COMMENT '0-⽆效, 1-正常, 2-不允许借阅',
                             `create_time` DATETIME DEFAULT now(),
                             `update_time` DATETIME DEFAULT now() ON UPDATE now(),
                             PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;
-- 初始化数据
INSERT INTO user_info ( user_name, PASSWORD ) VALUES ( "admin", "admin" );
INSERT INTO user_info ( user_name, PASSWORD ) VALUES ( "zhangsan", "123456" );
-- 初始化图书数据
INSERT INTO `book_info` (book_name,author,count, price, publish) VALUES ('活着', '余华', 29, 22.00, '北京文艺出版社');
INSERT INTO `book_info` (book_name,author,count, price, publish) VALUES ('平凡的世界', '路遥', 5, 98.56, '北京十月文艺出版社');
INSERT INTO `book_info` (book_name,author,count, price, publish) VALUES ('三体', '刘慈欣', 9, 102.67, '重庆出版社');
INSERT INTO `book_info` (book_name,author,count, price, publish) VALUES ('金字塔原理', '麦肯锡', 16, 178.00, '民主与建设出版社');