package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 中间件管理对象 middleware_master
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
public class MiddlewareMaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 中间件设备id，唯一标识 */
    private Long middlewareId;

    /** 中间件名称 */
    @Excel(name = "中间件名称")
    private String middlewareName;

    /** 中间件类型 */
    @Excel(name = "中间件类型")
    private Long deviceId;

    /** 中间件版本号 */
    @Excel(name = "中间件版本号")
    private String middlewareVersion;

    /** 部署方式 */
    @Excel(name = "部署方式")
    private String deployType;

    /** 环境类型 */
    @Excel(name = "环境类型")
    private String envType;

    /** 部署ip，多个都好隔开 */
    @Excel(name = "部署ip，多个都好隔开")
    private String ipAddress;

    /** 端口 */
    @Excel(name = "端口")
    private String port;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 中间价状态，0正常，1维护中，2未启用 */
    @Excel(name = "中间价状态，0正常，1维护中，2未启用")
    private Long status;

    /** 监控状态，0未监控，1监控中，2异常 */
    @Excel(name = "监控状态，0未监控，1监控中，2异常")
    private Long monitorStatus;

    /** 登陆账号 */
    @Excel(name = "登陆账号")
    private String username;

    /** 登陆密码(加密之后) */
    @Excel(name = "登陆密码(加密之后)")
    private String password;

    public void setMiddlewareId(Long middlewareId) 
    {
        this.middlewareId = middlewareId;
    }

    public Long getMiddlewareId() 
    {
        return middlewareId;
    }

    public void setMiddlewareName(String middlewareName) 
    {
        this.middlewareName = middlewareName;
    }

    public String getMiddlewareName() 
    {
        return middlewareName;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setMiddlewareVersion(String middlewareVersion) 
    {
        this.middlewareVersion = middlewareVersion;
    }

    public String getMiddlewareVersion() 
    {
        return middlewareVersion;
    }

    public void setDeployType(String deployType) 
    {
        this.deployType = deployType;
    }

    public String getDeployType() 
    {
        return deployType;
    }

    public void setEnvType(String envType) 
    {
        this.envType = envType;
    }

    public String getEnvType() 
    {
        return envType;
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

    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setMonitorStatus(Long monitorStatus) 
    {
        this.monitorStatus = monitorStatus;
    }

    public Long getMonitorStatus() 
    {
        return monitorStatus;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("middlewareId", getMiddlewareId())
            .append("middlewareName", getMiddlewareName())
            .append("deviceId", getDeviceId())
            .append("middlewareVersion", getMiddlewareVersion())
            .append("deployType", getDeployType())
            .append("envType", getEnvType())
            .append("ipAddress", getIpAddress())
            .append("port", getPort())
            .append("leader", getLeader())
            .append("status", getStatus())
            .append("monitorStatus", getMonitorStatus())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("remark", getRemark())
            .toString();
    }
}
