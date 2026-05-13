package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Jars应用监控面板配置 jars_monitor_panel_config
 *
 * @author ruoyi
 */
public class JarsPanelConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 设备标识（对应jars应用管理中的设备，All=全部设备） */
    private String device;

    /** Jar应用分类/标签（对应jars应用管理中的分类，All=全部分类） */
    private String category;

    /** 面板分组名称 */
    private String panelGroup;

    /** Jar应用名称（对应jars应用管理中的应用名，All=全部应用） */
    private String appName;

    /** 面板唯一标识（如default_1、custom_xxx） */
    private String panelKey;

    /** 面板显示名称 */
    private String panelName;

    /** 是否自定义面板（0=默认面板 1=自定义面板） */
    private Integer isCustom;

    /** Grafana原始Dashboard的PanelID，仅默认面板使用 */
    private Integer grafanaPanelId;

    /** Prometheus查询语句，仅自定义面板使用 */
    private String promql;

    /** 图表类型（graph/stat/gauge/barchart/table） */
    private String chartType;

    /** 数值单位（none/percent/bytes/bps/s/ms/reqps） */
    private String unit;

    /** 时间范围（5m/15m/30m/1h/3h/6h/12h/24h） */
    private String timeRange;

    /** 刷新间隔（空=不刷新/5s/10s/30s/1m/5m） */
    private String refreshInterval;

    /** 生效范围（app=当前应用/category=当前分类/device=当前设备/global=全局） */
    private String scope;

    /** 面板排列顺序（同一分组内小面板的排序） */
    private Integer sortOrder;

    /** 所属分组的排序序号（同一 panelGroup 的行共享此值） */
    private Integer groupSortOrder;

    /** 面板宽度跨列（el-col span）：6=1/4宽 8=1/3宽 12=1/2宽 24=全宽 */
    private Integer panelSpan;

    /** 指标描述（含义、作用、异常场景等） */
    private String description;

    /** 删除标志（0=正常 2=删除） */
    private String delFlag;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDevice() { return device; }
    public void setDevice(String device) { this.device = device; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }

    public String getPanelKey() { return panelKey; }
    public void setPanelKey(String panelKey) { this.panelKey = panelKey; }

    public String getPanelName() { return panelName; }
    public void setPanelName(String panelName) { this.panelName = panelName; }

    public String getPanelGroup() { return panelGroup; }
    public void setPanelGroup(String panelGroup) { this.panelGroup = panelGroup; }

    public Integer getIsCustom() { return isCustom; }
    public void setIsCustom(Integer isCustom) { this.isCustom = isCustom; }

    public Integer getGrafanaPanelId() { return grafanaPanelId; }
    public void setGrafanaPanelId(Integer grafanaPanelId) { this.grafanaPanelId = grafanaPanelId; }

    public String getPromql() { return promql; }
    public void setPromql(String promql) { this.promql = promql; }

    public String getChartType() { return chartType; }
    public void setChartType(String chartType) { this.chartType = chartType; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getTimeRange() { return timeRange; }
    public void setTimeRange(String timeRange) { this.timeRange = timeRange; }

    public String getRefreshInterval() { return refreshInterval; }
    public void setRefreshInterval(String refreshInterval) { this.refreshInterval = refreshInterval; }

    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public Integer getGroupSortOrder() { return groupSortOrder; }
    public void setGroupSortOrder(Integer groupSortOrder) { this.groupSortOrder = groupSortOrder; }

    public Integer getPanelSpan() { return panelSpan; }
    public void setPanelSpan(Integer panelSpan) { this.panelSpan = panelSpan; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDelFlag() { return delFlag; }
    public void setDelFlag(String delFlag) { this.delFlag = delFlag; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("device", getDevice())
            .append("category", getCategory())
            .append("appName", getAppName())
            .append("panelKey", getPanelKey())
            .append("panelName", getPanelName())
            .append("panelGroup", getPanelGroup())
            .append("isCustom", getIsCustom())
            .append("grafanaPanelId", getGrafanaPanelId())
            .append("promql", getPromql())
            .append("chartType", getChartType())
            .append("unit", getUnit())
            .append("timeRange", getTimeRange())
            .append("refreshInterval", getRefreshInterval())
            .append("scope", getScope())
            .append("sortOrder", getSortOrder())
            .append("groupSortOrder", getGroupSortOrder())
            .append("panelSpan", getPanelSpan())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
