-- ----------------------------
-- 微服务监控面板配置表
-- ----------------------------
drop table if exists microservice_monitor_panel_config;
create table monitor_panel_config (
  id                bigint(20)      not null auto_increment    comment '主键ID',
  namespace         varchar(128)    not null                   comment 'K8s命名空间',
  service           varchar(128)    not null                   comment '服务/容器名',
  pod               varchar(256)    not null default 'All'     comment 'Pod名称（All表示全部Pod）',
  panel_key         varchar(64)     not null                   comment '面板唯一标识（如default_1、custom_xxx）',
  panel_name        varchar(128)    not null                   comment '面板显示名称',
  is_custom         tinyint(1)      not null default 0         comment '是否自定义面板（0=默认面板 1=自定义面板）',
  grafana_panel_id  int(11)         default null               comment 'Grafana原始Dashboard的PanelID，仅默认面板使用',
  promql            text            default null               comment 'Prometheus查询语句，仅自定义面板使用',
  chart_type        varchar(32)     default 'graph'            comment '图表类型（graph/stat/gauge/barchart/table）',
  unit              varchar(32)     default 'none'             comment '数值单位（none/percent/bytes/bps/s/ms/reqps）',
  time_range        varchar(16)     not null default '5m'      comment '时间范围（5m/15m/30m/1h/3h/6h/12h/24h）',
  refresh_interval  varchar(16)     default '30s'              comment '刷新间隔（空=不刷新/5s/10s/30s/1m/5m）',
  sort_order        int(4)          not null default 0         comment '面板排列顺序',
  del_flag          char(1)         not null default '0'       comment '删除标志（0=正常 2=删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id),
  unique key uk_panel (namespace, service, pod, panel_key)
) engine=innodb auto_increment=1 comment = '微服务监控面板配置表';