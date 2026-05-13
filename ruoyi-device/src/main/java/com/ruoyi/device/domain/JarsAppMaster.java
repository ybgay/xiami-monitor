package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * JAR应用管理对象 jars_app_master
 * JAR应用不属于K8s Pod，无法自动发现，需手动添加配置
 *
 * @author ruoyi
 * @date 2026-03-17
 */
public class JarsAppMaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** JAR应用ID */
    private Long jarsId;

    /** 所属设备ID */
    @Excel(name = "所属设备ID")
    private Long deviceId;

    /** JAR应用名称 */
    @Excel(name = "JAR应用名称")
    private String appName;

    /** JAR应用中文名称 */
    @Excel(name = "JAR应用中文名称")
    private String appNameCn;   

    /** 应用分类 */
    @Excel(name = "应用分类")
    private String category;

    /** 应用状态（0=正常 1=禁用） */
    @Excel(name = "应用状态", readConverterExp = "0=正常,1=禁用")
    private Integer status;

    /** 部署主机IP地址 */
    @Excel(name = "IP地址")
    private String ipAddress;

    /** 应用监听端口 */
    @Excel(name = "端口")
    private String port;

    /** Metrics暴露端口 */
    @Excel(name = "Metrics端口")
    private String metricsPort;

    /** Metrics路径 */
    @Excel(name = "Metrics路径")
    private String metricsPath;

    /** JVM启动参数备注 */
    @Excel(name = "JVM参数")
    private String jvmArgs;

    /** JAR包部署路径 */
    @Excel(name = "部署路径")
    private String deployPath;

    /** Java版本 */
    @Excel(name = "Java版本")
    private String javaVersion;


    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    public Long getJarsId() { return jarsId; }
    public void setJarsId(Long jarsId) { this.jarsId = jarsId; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }

    public String getAppNameCn() { return appNameCn; }
    public void setAppNameCn(String appNameCn) { this.appNameCn = appNameCn; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getPort() { return port; }
    public void setPort(String port) { this.port = port; }

    public String getMetricsPort() { return metricsPort; }
    public void setMetricsPort(String metricsPort) { this.metricsPort = metricsPort; }

    public String getMetricsPath() { return metricsPath; }
    public void setMetricsPath(String metricsPath) { this.metricsPath = metricsPath; }

    public String getJvmArgs() { return jvmArgs; }
    public void setJvmArgs(String jvmArgs) { this.jvmArgs = jvmArgs; }

    public String getDeployPath() { return deployPath; }
    public void setDeployPath(String deployPath) { this.deployPath = deployPath; }

    public String getJavaVersion() { return javaVersion; }
    public void setJavaVersion(String javaVersion) { this.javaVersion = javaVersion; }

    public String getLeader() { return leader; }
    public void setLeader(String leader) { this.leader = leader; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("jarsId", getJarsId())
            .append("deviceId", getDeviceId())
            .append("appName", getAppName())
            .append("appNameCn", getAppNameCn())
            .append("category", getCategory())
            .append("status", getStatus())
            .append("ipAddress", getIpAddress())
            .append("port", getPort())
            .append("metricsPort", getMetricsPort())
            .append("metricsPath", getMetricsPath())
            .append("jvmArgs", getJvmArgs())
            .append("deployPath", getDeployPath())
            .append("javaVersion", getJavaVersion())
            .append("leader", getLeader())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
