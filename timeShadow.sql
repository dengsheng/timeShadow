/*创建User表*/
CREATE TABLE user(
	uid INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
	uname varchar(100) NOT NULL,
	upwd varchar(50) NOT NULL,
	uemail varchar(100) NOT NULL
);