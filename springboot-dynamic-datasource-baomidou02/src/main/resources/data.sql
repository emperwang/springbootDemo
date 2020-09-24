drop database if exists test_orders;
create database if not exists test_orders;
use test_orders;
drop table if exists orders;
create table if not exists orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';
insert into test_orders.orders(id, user_id) values (1,1);

drop database if exists test_orders01;
create database if not exists test_orders01;
use test_orders01;
drop table if exists orders;
create table if not exists orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';

insert into test_orders01.orders(id, user_id) values (2,2);

drop database if exists test_orders02;
create database if not exists test_orders02;
use test_orders02;
drop table if exists orders;
create table if not exists orders(
    id int(11),
    user_id int(11) default null comment '用户编号'
)ENGINE=InnoDB default charset=utf8 comment='订单表';

insert into test_orders02.orders(id, user_id) values (3,3);
