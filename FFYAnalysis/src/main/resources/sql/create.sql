CREATE TABLE `month_sum` (
  `id` int(11) NOT NULL auto_increment,
  `group_name` varchar(100) default NULL COMMENT '小组名字',
  `month` int(11) default NULL COMMENT '月份',
  `person_count` int(11) default NULL COMMENT '月末人数',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `lossrate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '人名',
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
