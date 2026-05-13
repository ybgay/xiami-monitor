package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据库管理对象 database_master
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
public class DatabaseMaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据库id */
    private Long databaseId;

    /** 设备类型id */
    @Excel(name = "设备类型id")
    private Long deviceId;

    /** 数据库名称 */
    @Excel(name = "数据库名称")
    private String databaseName;

    /** 数据库状态，0正常，1禁用 */
    @Excel(name = "数据库状态，0正常，1禁用")
    private Long status;

    /** grafana dashboard url */
    @Excel(name = "grafana dashboard url")
    private String grafanaUrl;
    /** 数据库ip地址 */
    @Excel(name = "数据库ip地址")
    private String ipAddress;

    public String getGrafanaUrl() {
        return grafanaUrl;
    }
    public void setGrafanaUrl(String grafanaUrl) {
        this.grafanaUrl = grafanaUrl;
    }
    /** 端口号 */
    @Excel(name = "端口号")
    private String port;

    /** 数据库用户名 */
    @Excel(name = "数据库用户名")
    private String username;

    /** 数据库密码 */
    private String password;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 数据库版本 */
    @Excel(name = "数据库版本")
    private String version;
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    /** 1111111 */


    public void setDatabaseId(Long databaseId) 
    {
        this.databaseId = databaseId;
    }

    public Long getDatabaseId() 
    {
        return databaseId;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setDatabaseName(String databaseName) 
    {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() 
    {
        return databaseName;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }

    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("databaseId", getDatabaseId())
            .append("deviceId", getDeviceId())
            .append("databaseName", getDatabaseName())
            .append("status", getStatus())
            .append("ipAddress", getIpAddress())
            .append("port", getPort())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("createTime", getCreateTime())
            .append("leader", getLeader())
            .toString();
    }
}
