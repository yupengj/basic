DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30)   NULL COMMENT '姓名',
  `email` varchar(50)   NULL COMMENT '邮箱',
  `age` INT(11)   NULL COMMENT '邮箱',
  `created_on` datetime  NULL COMMENT '创建时间',
  `updated_on` datetime  NULL COMMENT '修改时间',
  `created_by` varchar(30)   NULL COMMENT '创建人',
  `updated_by` varchar(30)   NULL COMMENT '修改人',

  PRIMARY KEY (`id`)
)  COMMENT='账号表';