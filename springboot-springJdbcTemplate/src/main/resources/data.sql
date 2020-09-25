drop database if exists testb;
create database if not exists testb;
use testb;
drop table if exists users;
create table if not exists users(
    id int(11) not null auto_increment comment 'userid',
    username varchar(64) collate utf8mb4_bin  default null ,
    password varchar(32) collate utf8mb4_bin default null,
    create_time datetime default null,
    primary key(id),
    unique key idx_username(username)
)ENGINE=InnoDB auto_increment=4 default charset=utf8mb4  COllate=utf8mb4_bin;

insert into users(username, password, create_time) values('zhangsan', '123456', current_timestamp);