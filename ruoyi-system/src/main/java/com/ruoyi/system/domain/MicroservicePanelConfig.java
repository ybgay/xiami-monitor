package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微服务监控面板配置 microservice_monitor_panel_config
 *
 * @author ruoyi
 */
public class MicroservicePanelConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** K8s命名空间 */
    private String namespace;

    /** 服务/容器名 */
    private String service;

    /** Pod名称（All表示全部Pod） */
    private String pod;

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

    /** 面板排列顺序 */
    private Integer sortOrder;

    /** 删除标志（0=正常 2=删除） */
    private String delFlag;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamespace() { return namespace; }
    public void setNamespace(String namespace) { this.namespace = namespace; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getPod() { return pod; }
    public void setPod(String pod) { this.pod = pod; }

    public String getPanelKey() { return panelKey; }
    public void setPanelKey(String panelKey) { this.panelKey = panelKey; }

    public String getPanelName() { return panelName; }
    public void setPanelName(String panelName) { this.panelName = panelName; }

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

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public String getDelFlag() { return delFlag; }
    public void setDelFlag(String delFlag) { this.delFlag = delFlag; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("namespace", getNamespace())
            .append("service", getService())
            .append("pod", getPod())
            .append("panelKey", getPanelKey())
            .append("panelName", getPanelName())
            .append("isCustom", getIsCustom())
            .append("grafanaPanelId", getGrafanaPanelId())
            .append("promql", getPromql())
            .append("chartType", getChartType())
            .append("unit", getUnit())
            .append("timeRange", getTimeRange())
            .append("refreshInterval", getRefreshInterval())
            .append("sortOrder", getSortOrder())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
