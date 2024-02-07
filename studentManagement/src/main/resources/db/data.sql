insert into stu_user values
(1, 'admin','admin','admin@qq.com','1,2','1911817756',LOCALTIMESTAMP,LOCALTIMESTAMP),
(2, 'test','test','test2@qq.com','2','1911817756',LOCALTIMESTAMP,LOCALTIMESTAMP),
(3, 'zhangsan','test','zhangsan@qq.com','2','1911817756',LOCALTIMESTAMP,LOCALTIMESTAMP),
(4, 'wangwu','test','wangwu@qq.com','2','1911817756',LOCALTIMESTAMP,LOCALTIMESTAMP),
(5, 'zhaosi','test','zhaosi@qq.com','2','1911817756',LOCALTIMESTAMP,LOCALTIMESTAMP);

insert into stu_role values
(1, 'admin','/admin,/role,/user,/certificate',LOCALTIMESTAMP,LOCALTIMESTAMP),
(2, 'test','/user,/certificate',LOCALTIMESTAMP,LOCALTIMESTAMP);

insert into stu_score values
(1, 3,'zhangsan','2020-2021',1,70,'高等数学',5,1,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(2, 3,'zhangsan','2020-2021',1,80,'形式与政策',5,2,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(3, 3,'zhangsan','2020-2021',1,70,'体美劳',5,0,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(4, 3,'zhangsan','2020-2021',1,90,'线性代数',5,1,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(5, 4,'wangwu','2020-2021',1,75,'高等数学',5,1,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(6, 4,'wangwu','2020-2021',1,85,'形式与政策',5,2,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(7, 4,'wangwu','2020-2021',1,75,'体美劳',5,0,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP),
(8, 4,'wangwu','2020-2021',1,92,'线性代数',5,1,3.7,LOCALTIMESTAMP,LOCALTIMESTAMP);



insert into stu_certificate values
(1, 3,'zhangsan','2024-01-21_13_56_39_4d0fd56b95.jpg','2012',LOCALTIMESTAMP,LOCALTIMESTAMP,LOCALTIMESTAMP);

insert into stu_score_summary values
(1,'2020-2021','高等数学,形式与政策,体美劳,线性代数');
