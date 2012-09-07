#####################################
ALTER TABLE Activity ADD COLUMN startDate  date after activity_name;
ALTER TABLE Activity ADD COLUMN endDate  date after startDate;

UPDATE Activity SET startDate='2012-07-26' , endDate='2012-10-25' where id='01';
UPDATE Activity SET startDate='2012-08-30' , endDate='2012-09-30' where id='02';


CREATE TABLE Report_User(
username VARCHAR(100) NOT NULL PRIMARY KEY,
password VARCHAR(100) NOT NULL,
enabled BIT NOT NULL,
activity_id VARCHAR(100) NOT NULL,
descn  VARCHAR(100)
);

CREATE TABLE Report_Role(
rolename VARCHAR(100) NOT NULL PRIMARY KEY,
descn  VARCHAR(100)
);

CREATE TABLE Report_User_Role(
username VARCHAR(100) NOT NULL REFERENCES Report_User(username),
rolename  VARCHAR(100) NOT NULL REFERENCES Report_Role(rolename),
PRIMARY KEY(username,rolename)
);

CREATE TABLE Report_Authorities(
authority VARCHAR(100) NOT NULL PRIMARY KEY,
descn  VARCHAR(100) 
);

CREATE TABLE Report_Role_Authorities(
rolename   VARCHAR(100) NOT NULL REFERENCES Report_Role(rolename),
authority   VARCHAR(100) NOT NULL REFERENCES Report_Authorities(authority),
PRIMARY KEY(rolename, authority)
);

INSERT INTO Report_Authorities VALUES('report_detail','查看明细报表的权限');
INSERT INTO Report_Authorities VALUES('report_token','查看验证码使用情况的权限');
INSERT INTO Report_Authorities VALUES('report_total','查看总计报表的权限');

#配置角色
INSERT INTO Report_Role VALUES('report_admin','报表管理员角色');
INSERT INTO Report_Role VALUES('report_detail','报表明细查看角色');
INSERT INTO Report_Role VALUES('report_token','验证码使用情况查看角色');
INSERT INTO Report_Role VALUES('report_total','总计报表查看角色');
INSERT INTO Report_Role VALUES('report_token_total','验证码和总计报表查看角色');
#给角色配置权限
INSERT INTO Report_Role_Authorities VALUES('report_admin','report_detail');
INSERT INTO Report_Role_Authorities VALUES('report_admin','report_token');
INSERT INTO Report_Role_Authorities VALUES('report_admin','report_total');
INSERT INTO Report_Role_Authorities VALUES('report_detail','report_detail');
INSERT INTO Report_Role_Authorities VALUES('report_token','report_token');
INSERT INTO Report_Role_Authorities VALUES('report_total','report_total');
INSERT INTO Report_Role_Authorities VALUES('report_token_total','report_token');
INSERT INTO Report_Role_Authorities VALUES('report_token_total','report_total');

#配置用户
INSERT INTO Report_User VALUES('coast','coast',1,'01','海岸城报表管理员');
INSERT INTO Report_User VALUES('chengdu','chengdu',1,'02','海岸城报表管理员');
#给用户配置角色
INSERT INTO Report_User_Role VALUES('coast','report_admin');
INSERT INTO Report_User_Role VALUES('chengdu','report_admin');
