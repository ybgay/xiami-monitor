-- ============================================================
-- 迁移：为 jars_monitor_panel_config 添加分组排序和面板宽度列
-- 执行时间：2026-03-25
-- ============================================================

-- 1. 大面板（分组）排序：group_sort_order
--    每个 panel_group 内所有行共享同一个 group_sort_order 值。
--    前端批量更新时按分组名写入。
ALTER TABLE `jars_monitor_panel_config`
    ADD COLUMN `group_sort_order` int(11) NOT NULL DEFAULT 0
        COMMENT '所属分组的排序序号（同一 panel_group 的行值相同）'
        AFTER `sort_order`;

-- 2. 小面板宽度跨列数：panel_span
--    对应前端 el-col span 值，可选 6/8/12/24（即 1/4、1/3、1/2、1个大面板宽度）
ALTER TABLE `jars_monitor_panel_config`
    ADD COLUMN `panel_span` int(11) NOT NULL DEFAULT 6
        COMMENT '面板宽度（el-col span）：6=1/4宽, 8=1/3宽, 12=1/2宽, 24=全宽'
        AFTER `group_sort_order`;
