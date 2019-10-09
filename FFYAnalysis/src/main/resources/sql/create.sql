CREATE TABLE `month_sum` (
  `id` int(11) NOT NULL auto_increment,
  `group_name` varchar(100) default NULL COMMENT '小组名字',
  `month` int(11) default NULL COMMENT '月份',
  `person_count` int(11) default NULL COMMENT '月末人数',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
