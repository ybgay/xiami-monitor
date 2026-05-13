package com.ruoyi.device.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 物理机基础信息对象 physical_machine_base
 * 
 * @author ruoyi
 * @date 2026-03-09
 */
public class PhysicalMachineBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物理机全局唯一标识（建议用SN号+类型缩写，如：DELL-T-001） */
    private String machineId;

    /** 物理机自定义名称（便于运维识别，如：业务服务器-塔式-01） */
    @Excel(name = "物理机自定义名称", readConverterExp = "便=于运维识别，如：业务服务器-塔式-01")
    private String machineName;

    /** 物理机类型：tower=塔式、rack=机架式、blade=刀片式 */
    @Excel(name = "物理机类型：tower=塔式、rack=机架式、blade=刀片式")
    private String machineType;

    /** 服务器品牌（如戴尔、华为、IBM、浪潮） */
    @Excel(name = "服务器品牌", readConverterExp = "如=戴尔、华为、IBM、浪潮")
    private String brand;

    /** 服务器具体型号（如戴尔R750、华为RH2288H V5、IBM BladeCenter HS22） */
    @Excel(name = "服务器具体型号", readConverterExp = "如=戴尔R750、华为RH2288H,V=5、IBM,B=ladeCenter,H=S22")
    private String model;

    /** 物理机当前运行状态 */
    @Excel(name = "物理机当前运行状态")
    private String status;

    /** 所属机房ID（关联机房信息表） */
    @Excel(name = "所属机房ID", readConverterExp = "关=联机房信息表")
    private String roomId;

    /** 采购入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseTime;

    /** 保修到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保修到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warrantyExpire;

    /** 所属运维负责人（工号/姓名） */
    @Excel(name = "所属运维负责人", readConverterExp = "工=号/姓名")
    private String adminUser;

    public void setMachineId(String machineId) 
    {
        this.machineId = machineId;
    }

    public String getMachineId() 
    {
        return machineId;
    }

    public void setMachineName(String machineName) 
    {
        this.machineName = machineName;
    }

    public String getMachineName() 
    {
        return machineName;
    }

    public void setMachineType(String machineType) 
    {
        this.machineType = machineType;
    }

    public String getMachineType() 
    {
        return machineType;
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

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setRoomId(String roomId) 
    {
        this.roomId = roomId;
    }

    public String getRoomId() 
    {
        return roomId;
    }

    public void setPurchaseTime(Date purchaseTime) 
    {
        this.purchaseTime = purchaseTime;
    }

    public Date getPurchaseTime() 
    {
        return purchaseTime;
    }

    public void setWarrantyExpire(Date warrantyExpire) 
    {
        this.warrantyExpire = warrantyExpire;
    }

    public Date getWarrantyExpire() 
    {
        return warrantyExpire;
    }

    public void setAdminUser(String adminUser) 
    {
        this.adminUser = adminUser;
    }

    public String getAdminUser() 
    {
        return adminUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("machineId", getMachineId())
            .append("machineName", getMachineName())
            .append("machineType", getMachineType())
            .append("brand", getBrand())
            .append("model", getModel())
            .append("status", getStatus())
            .append("roomId", getRoomId())
            .append("purchaseTime", getPurchaseTime())
            .append("warrantyExpire", getWarrantyExpire())
            .append("adminUser", getAdminUser())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
