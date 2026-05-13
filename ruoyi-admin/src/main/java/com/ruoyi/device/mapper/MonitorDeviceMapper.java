package com.ruoyi.device.mapper;

import java.util.List;
import com.ruoyi.device.domain.MonitorDevice;

/**
 * 设备管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-05
 */
public interface MonitorDeviceMapper 
{
    /**
     * 查询设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理
     */
    public MonitorDevice selectMonitorDeviceByDeviceId(Long deviceId);

    /**
     * 查询设备管理列表
     * 
     * @param monitorDevice 设备管理
     * @return 设备管理集合
     */
    public List<MonitorDevice> selectMonitorDeviceList(MonitorDevice monitorDevice);

    /**
     * 新增设备管理
     * 
     * @param monitorDevice 设备管理
     * @return 结果
     */
    public int insertMonitorDevice(MonitorDevice monitorDevice);

    /**
     * 修改设备管理
     * 
     * @param monitorDevice 设备管理
     * @return 结果
     */
    public int updateMonitorDevice(MonitorDevice monitorDevice);

    /**
     * 删除设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteMonitorDeviceByDeviceId(Long deviceId);

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMonitorDeviceByDeviceIds(Long[] deviceIds);
}
