/* 创建小组表 */
DROP TABLE IF EXISTS month_sum;
CREATE TABLE `month_sum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) DEFAULT NULL COMMENT '小组名字',
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `first_person_count` int(11) DEFAULT NULL COMMENT '月初人数',
  `end_person_count` int(11) DEFAULT NULL COMMENT '月末人数',
  `depents_id` int(11) NOT NULL COMMENT '所属的大区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



/**
 流失率表
 */
 DROP TABLE IF EXISTS lossrate;
CREATE TABLE `lossrate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '人名',
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/**
大部表
 */
 DROP TABLE IF EXISTS region;
CREATE TABLE `region` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(100) NOT NULL COMMENT '大部名字',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/**
大区表
 */
 DROP TABLE IF EXISTS depents;
CREATE TABLE `depents` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `dept_name` varchar(100) NOT NULL COMMENT '大区名字',
  `region_id` int(11) NOT NULL COMMENT '对应的大部',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
