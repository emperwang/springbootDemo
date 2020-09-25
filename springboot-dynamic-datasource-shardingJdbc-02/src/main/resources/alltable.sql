drop database if exists t_orders;
create database if not exists t_orders;
use t_orders;
drop table if exists orders;
create table if not exists t_orders.orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';

insert into t_orders.orders(id, user_id) values (1,1);


create database if not exists t_orders01;
create table if not exists t_orders01.orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';
insert into t_orders01.orders(id, user_id) values (2,2);

create database if not exists t_orders02;
create table if not exists t_orders02.orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';
insert into t_orders02.orders(id, user_id) values (3,3);
