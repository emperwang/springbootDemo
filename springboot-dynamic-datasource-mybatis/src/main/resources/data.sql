drop database if exists test_orders;
create database if not exists test_orders;
use test_orders;
drop table if exists user;
create table if not exists users(
    id int(11) not null auto_increment comment '用户编号',
    username varchar(64),
    password varchar(64),
    create_time timestamp,
    primary key(id),
    UNIQUE key idx_username(username)
)ENGINE=InnoDB default charset=utf8 comment='用户';

insert into test_orders.users(id,username,password,create_time)values
(1,'zhangsan','123',current_timestamp);

drop table if exists orders;
create table if not exists orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';

insert into test_orders.orders(id, user_id) values (1,1);
