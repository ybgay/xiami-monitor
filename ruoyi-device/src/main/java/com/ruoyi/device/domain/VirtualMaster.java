package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 虚拟机管理对象 virtual_master
 * 
 * @author zly
 * @date 2026-03-17
 */
public class VirtualMaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 虚拟机 */
    private Long virtualId;

    /** 类型 */
    private Long deviceId;

    /** 虚拟机名称 */
    @Excel(name = "虚拟机名称")
    private String virtualName;

    /** 状态，0正常，1禁用 */
    @Excel(name = "状态，0正常，1禁用")
    private Long status;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ipAddress;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 操作系统类型 */
    @Excel(name = "操作系统类型")
    private String osType;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** 用户密码 */
    private String password;

    /** 所属物理机 */
    @Excel(name = "所属物理机")
    private Long physicalId;

    public void setVirtualId(Long virtualId) 
    {
        this.virtualId = virtualId;
    }

    public Long getVirtualId() 
    {
        return virtualId;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setVirtualName(String virtualName) 
    {
        this.virtualName = virtualName;
    }

    public String getVirtualName() 
    {
        return virtualName;
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

    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }

    public void setOsType(String osType) 
    {
        this.osType = osType;
    }

    public String getOsType() 
    {
        return osType;
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

    public void setPhysicalId(Long physicalId) 
    {
        this.physicalId = physicalId;
    }

    public Long getPhysicalId() 
    {
        return physicalId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("virtualId", getVirtualId())
            .append("deviceId", getDeviceId())
            .append("virtualName", getVirtualName())
            .append("status", getStatus())
            .append("ipAddress", getIpAddress())
            .append("createTime", getCreateTime())
            .append("leader", getLeader())
            .append("osType", getOsType())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("remark", getRemark())
            .append("physicalId", getPhysicalId())
            .toString();
    }
}
