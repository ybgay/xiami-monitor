package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 physical_master
 * 
 * @author ruoyi
 * @date 2026-03-11
 */
public class PhysicalMaster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物理机id */
    private Long physicalId;

    /** 物理机名称 */
    @Excel(name = "物理机名称")
    private String physicalName;

    /** 物理机类型 */
    @Excel(name = "物理机类型")
    private Long deviceId;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 状态，0正常，1禁用 */
    @Excel(name = "状态，0正常，1禁用")
    private Long status;

    /** 网卡MAC地址 */
    @Excel(name = "网卡MAC地址")
    private String macAddress;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ipAddress;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    @Excel(name="用户名")
    private String username;

    @Excel(name="密码")
    private String password;

    @Excel(name="是否k8s集群")
    private int isK8s;

    
	
	
    public int getIsK8s() {
        return isK8s;
    }

    public void setIsK8s(int isK8s) {
        this.isK8s = isK8s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhysicalId(Long physicalId)
    {
        this.physicalId = physicalId;
    }

    public Long getPhysicalId() 
    {
        return physicalId;
    }

    public void setPhysicalName(String physicalName) 
    {
        this.physicalName = physicalName;
    }

    public String getPhysicalName() 
    {
        return physicalName;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }

    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setMacAddress(String macAddress) 
    {
        this.macAddress = macAddress;
    }

    public String getMacAddress() 
    {
        return macAddress;
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



    @Override
    public String toString() {
        return "PhysicalMaster{" +
                "physicalId=" + physicalId +
                ", physicalName='" + physicalName + '\'' +
                ", deviceId=" + deviceId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                ", macAddress='" + macAddress + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", leader='" + leader + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isK8s=" + isK8s +
                '}';
    }
}
