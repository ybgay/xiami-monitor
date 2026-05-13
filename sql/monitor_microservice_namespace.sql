-- ----------------------------
-- 微服务命名空间配置表（存储用户手动维护的属于微服务的 namespace 列表）
-- ----------------------------
drop table if exists monitor_microservice_namespace;
create table monitor_microservice_namespace (
  id          bigint(20)   not null auto_increment  comment '主键ID',
  namespace   varchar(128) not null                 comment 'K8s命名空间名称',
  sort_order  int(4)       not null default 0        comment '排序',
  del_flag    char(1)      not null default '0'      comment '删除标志（0=正常 2=删除）',
  create_by   varchar(64)  default ''               comment '创建者',
  create_time datetime                              comment '创建时间',
  update_by   varchar(64)  default ''               comment '更新者',
  update_time datetime                              comment '更新时间',
  remark      varchar(500) default null             comment '备注',
  primary key (id),
  unique key uk_namespace (namespace, del_flag)
) engine=innodb auto_increment=1 comment = '微服务监控-命名空间配置表';
