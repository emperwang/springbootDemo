create table IF NOT EXISTS stu_user (
    uid BIGINT primary key COMMENT 'id',
    username varchar(50) NOT NULL COMMENT 'user name',
    password varchar(100) NOT NULL COMMENT 'pwd',
    email varchar(50) NOT NULL COMMENT 'email',
    phone_number varchar(15) NOT NULL comment 'phone number',
    create_time TIMESTAMP NOT NULL COMMENT 'create time',
    update_time TIMESTAMP NOT NULL COMMENT 'update time'
);