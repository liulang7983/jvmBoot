CREATE TABLE tansaction_test (
  ID int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  remark varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='事务表';
insert into tansaction_test value (1,'正常');
insert into tansaction_test value (2,'异常');
insert into tansaction_test value (3,'异常未开启事务');
insert into tansaction_test value (4,'异常开启事务但tryCatch');
insert into tansaction_test value (5,'异常开启事务但非public修饰');
insert into tansaction_test value (6,'异常开启事务但有final修饰');