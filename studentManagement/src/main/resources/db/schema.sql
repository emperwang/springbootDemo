create table IF NOT EXISTS stu_user (
    uid BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 6),
    username varchar(50) NOT NULL COMMENT 'user name',
    password varchar(100) NOT NULL COMMENT 'pwd',
    email varchar(50) NOT NULL COMMENT 'email',
    rids varchar(50) NOT NULL,
    phone_number varchar(15) NOT NULL comment 'phone number',
    create_time TIMESTAMP NOT NULL COMMENT 'create time',
    update_time TIMESTAMP NOT NULL COMMENT 'update time'
);


create table if not exists stu_role (
    rid BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 3),
    role_name  varchar(50) not null,
    role_permission varchar(500)  not null,
    create_time timestamp NOT NULL,
    update_time  timestamp NOT NULL
);

create table if not exists  stu_score (
    sid BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 13),
    uid BIGINT not null,
    username varchar(50) NOT NULL COMMENT 'user name',
	academic_year varchar(50) NOT NULL COMMENT '学年',
	semester int NOT NULL COMMENT '学年',
	score int NOT NULL COMMENT '成绩',
    course varchar(50) NOT NULL COMMENT '课程',
    credit float NOT NULL COMMENT '学分',
    course_nature int NOT NULL COMMENT '课程性值:必修/选秀',
    grade_point_average float NOT NULL COMMENT '绩点',
	create_time timestamp NOT NULL,
    update_time  timestamp NOT NULL
);

create table if not exists stu_certificate (
    pid BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 3),
    uid BIGINT not null,
    username varchar(50) NOT NULL COMMENT 'user name',
	certificate_path varchar(200) not null,
	grade varchar(10),
	get_time  timestamp,
	create_time timestamp,
	update_time timestamp
);