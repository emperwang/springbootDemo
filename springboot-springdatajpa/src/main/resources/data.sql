drop database if exists testjpa;
create database if not exists testjpa;
use testjpa;
create table if not exists testjpa.users (
    id int(11) not null auto_increment comment '用户编码',
    username varchar(64) collate utf8mb4_bin default null comment '账号',
    password varchar(32) collate utf8mb4_bin default null comment '密码',
    create_time datetime default null,
    primary key(id)
) ENGINE=InnoDB auto_increment=4 default charset=UTF8mb4 collate=utf8mb4_bin;

insert into testjpa.users(username, password, create_time) values
('zhangsan','admin', current_timestamp);