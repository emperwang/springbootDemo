DROP TABLE user if EXISTS ;

CREATE TABLE user (
  id INT(11) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) DEFAULT NULL,
  age INT(11) DEFAULT NULL
);