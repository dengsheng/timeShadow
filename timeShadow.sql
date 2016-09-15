/*创建User表*/
CREATE TABLE user(
	uid INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
	uname varchar(100) NOT NULL,
	upwd varchar(50) NOT NULL,
	uemail varchar(100) NOT NULL
);
/*创建相册表*/
CREATE TABLE album(
	aid INT NOT NULL AUTO_INCREMENT,
	aname varchar(100) NOT NULL,
	adescription text ,
	adate date,
	apv int,
	uid int NOT NULL,
	PRIMARY KEY(aid),
	FOREIGN KEY(uid) REFERENCES user(uid)
);
/*创建照片表*/
CREATE TABLE imgs(
   tid INT NOT NULL AUTO_INCREMENT,
   tname varchar(255) NULL,
   turl varchar(255) NULL,
   aid int NOT NULL,
   PRIMARY KEY (tid),
   FOREIGN KEY(aid) REFERENCES album(aid)
);
/*创建好友关系表*/