create table IF NOT EXISTS stu_user (
    uid BIGINT primary key AUTO_INCREMENT  COMMENT 'id',
    username varchar(50) NOT NULL COMMENT 'user name',
    password varchar(100) NOT NULL COMMENT 'pwd',
    email varchar(50) NOT NULL COMMENT 'email',
    rids varchar(50) NOT NULL,
    phone_number varchar(15) NOT NULL comment 'phone number',
    create_time TIMESTAMP NOT NULL COMMENT 'create time',
    update_time TIMESTAMP NOT NULL COMMENT 'update time'
);


create table if not exists stu_role (
    rid  BIGINT primary key AUTO_INCREMENT,
    role_name  varchar(50) not null,
    role_permission varchar(500)  not null,
    create_time timestamp NOT NULL,
    update_time  timestamp NOT NULL
);