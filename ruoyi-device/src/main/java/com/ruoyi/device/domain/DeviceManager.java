package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 设备管理对象 device_manager
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
public class DeviceManager extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备类型 */
    private Long deviceId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Long deviceStatus;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leaders;

    /** 负责人电话 */
    @Excel(name = "负责人电话")
    private String phone;

    /** 负责人邮箱 */
    @Excel(name = "负责人邮箱")
    private String email;

    /** 删除标志(0=存在，2=删除) */
    private Long delFlag;

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }

    public void setDeviceStatus(Long deviceStatus) 
    {
        this.deviceStatus = deviceStatus;
    }

    public Long getDeviceStatus() 
    {
        return deviceStatus;
    }

    public void setLeaders(String leaders) 
    {
        this.leaders = leaders;
    }

    public String getLeaders() 
    {
        return leaders;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
            .append("parentId", getParentId())
            .append("deviceName", getDeviceName())
            .append("deviceStatus", getDeviceStatus())
            .append("leaders", getLeaders())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
