CREATE TABLE payment (
  ID int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  serial varchar(64) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=2002 DEFAULT CHARSET=utf8 COMMENT='合同文件列表';

CREATE TABLE test_total (
  ID int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 total int COMMENT '总数',
	 success int COMMENT '成功数',
	 fail int COMMENT '失败数',
	 status int  COMMENT '状态',
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=2002 DEFAULT CHARSET=utf8 COMMENT='状态测试表';