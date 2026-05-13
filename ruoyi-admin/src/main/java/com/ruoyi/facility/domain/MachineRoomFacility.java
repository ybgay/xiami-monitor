package com.ruoyi.facility.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机房设施管理对象 machine_room_facility
 * 
 * @author ruoyi
 * @date 2026-03-06
 */
public class MachineRoomFacility extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设施ID */
    private Long id;

    /** 设施编号 */
    @Excel(name = "设施编号")
    private String facilityCode;

    /** 设施名称 */
    @Excel(name = "设施名称")
    private String facilityName;

    /** 设施类型 */
    private Long deviceId;

    /** 所属机房 */
    @Excel(name = "所属机房")
    private String machineRoom;

    /** 安装位置 */
    @Excel(name = "安装位置")
    private String installPosition;

    /** 厂商 */
    @Excel(name = "厂商")
    private String vendor;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 运行状态 */
    @Excel(name = "运行状态")
    private Integer status;

    /** 负责人 */
    @Excel(name = "负责人")
    private String responsiblePerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 安装时间 */
    private Date installTime;

    /** 最后维护时间 */
    private Date lastMaintainTime;

    /** 下次维护时间 */
    private Date nextMaintainTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setFacilityCode(String facilityCode) 
    {
        this.facilityCode = facilityCode;
    }

    public String getFacilityCode() 
    {
        return facilityCode;
    }

    public void setFacilityName(String facilityName) 
    {
        this.facilityName = facilityName;
    }

    public String getFacilityName() 
    {
        return facilityName;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setMachineRoom(String machineRoom) 
    {
        this.machineRoom = machineRoom;
    }

    public String getMachineRoom() 
    {
        return machineRoom;
    }

    public void setInstallPosition(String installPosition) 
    {
        this.installPosition = installPosition;
    }

    public String getInstallPosition() 
    {
        return installPosition;
    }

    public void setVendor(String vendor) 
    {
        this.vendor = vendor;
    }

    public String getVendor() 
    {
        return vendor;
    }

    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setResponsiblePerson(String responsiblePerson) 
    {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson() 
    {
        return responsiblePerson;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setInstallTime(Date installTime) 
    {
        this.installTime = installTime;
    }

    public Date getInstallTime() 
    {
        return installTime;
    }

    public void setLastMaintainTime(Date lastMaintainTime) 
    {
        this.lastMaintainTime = lastMaintainTime;
    }

    public Date getLastMaintainTime() 
    {
        return lastMaintainTime;
    }

    public void setNextMaintainTime(Date nextMaintainTime) 
    {
        this.nextMaintainTime = nextMaintainTime;
    }

    public Date getNextMaintainTime() 
    {
        return nextMaintainTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("facilityCode", getFacilityCode())
            .append("facilityName", getFacilityName())
            .append("deviceId", getDeviceId())
            .append("machineRoom", getMachineRoom())
            .append("installPosition", getInstallPosition())
            .append("vendor", getVendor())
            .append("model", getModel())
            .append("status", getStatus())
            .append("responsiblePerson", getResponsiblePerson())
            .append("contactPhone", getContactPhone())
            .append("installTime", getInstallTime())
            .append("lastMaintainTime", getLastMaintainTime())
            .append("nextMaintainTime", getNextMaintainTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
