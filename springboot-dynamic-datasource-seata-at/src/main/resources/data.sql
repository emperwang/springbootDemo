drop database if exists seata_order;
create database if not exists seata_order;
create table if not exists seata_order.orders(
    id int(11)  not null auto_increment,
    user_id int(11) default null,
    product_id int(11) default null,
    pay_amount decimal(10,0) default null,
    add_time datetime default current_timestamp ,
    last_update_time datetime default current_timestamp,
    primary key(id)

)ENGINE=InnoDB auto_increment=1 default charset=utf8;

create table if not exists seata_order.undo_log(
    id  bigint(20) not null auto_increment,
    branch_id bigint(20) not null,
    xid varchar(100) not null,
    context varchar(128) not null,
    rollback_info LONGBLOB  not null,
    log_status int(11) not null,
    log_created datetime not null,
    log_modified  datetime not null,
    primary key(id),
    unique key ux_undo_log(xid, branch_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8;


# storage
drop database if exists seata_storage;
create database if not exists seata_storage;

create table if not exists seata_storage.product(
    id int(11) not null auto_increment,
    stock  int(11) default null,
    last_update_time datetime default current_timestamp on update current_timestamp ,
    primary key(id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8;

insert into seata_storage.product(id, stock) values (1,10); # 插入一条产品库存

create table if not exists seata_storage.undo_log(
    id BIGINT(20) not null auto_increment,
    branch_id BIGINT(20) not null,
    xid varchar(100) not null,
    context varchar(128) not null,
    rollback_info LONGBLOB not null,
    log_status int(11)  not null,
    log_created datetime not null,
    log_modified datetime  not null,
    primary key(id),
    unique key ux_undo_log(xid, branch_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8;

# amount
drop database if exists seata_amount;
create database if not exists seata_amount;
create table if not exists seata_amount.account(
    id int(11) not null auto_increment,
    balance double default null,
    last_update_time datetime default current_timestamp on update current_timestamp,
    primary key(id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8;

insert into seata_amount.account(id, balance) values (1,1);

create table if not exists seata_amount.undo_log(
    id bigint(20)  not null auto_increment,
    branch_id bigint(20)  not null,
    xid  varchar(100) not null,
    context varchar(128) not null,
    rollback_info longblob not null,
    log_status  int(11)  not null,
    log_created datetime not null,
    log_modified datetime not null,
    primary key(id),
    unique key ux_undo_log (xid, branch_id)
)ENGINE=InnoDB auto_increment=1 default charset=utf8;


