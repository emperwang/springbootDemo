DROP TABLE  IF EXISTS USER;
CREATE TABLE USER(
	id INT(11) AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,
	age INT(11) NOT NULL,
	address VARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;