package com.ruoyi.device.domain;



import com.ruoyi.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 机房表 physical_room
 *
 * @author ruoyi
 */
public class PhysicalRoom extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 机房ID */
    private Long roomId;

    /** 机房名称 */
    private String roomName;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 机房状态:0正常,1停用 */
    private String status;


    /** 绑定机器类型（1=塔式、2=机架式、3=刀片式） */
    private Integer machineType;

    public Integer getMachineType()
    {
        return machineType;
    }

    public void setMachineType(Integer machineType)
    {
        this.machineType = machineType;
    }

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public Long getRoomId()
    {
        return roomId;
    }

    public void setRoomId(Long roomId)
    {
        this.roomId = roomId;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    public String getLeader()
    {
        return leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("roomId", getRoomId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("roomName", getRoomName())
                .append("orderNum", getOrderNum())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}