DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30)   NULL COMMENT '姓名',
  `email` varchar(50)   NULL COMMENT '邮箱',
   `created_on` DATE   NULL COMMENT '建立时间',
   `updated_on` DATE   NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
)  COMMENT='账号表';