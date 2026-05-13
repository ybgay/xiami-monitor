package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.MonitorDevice;

/**
 * 设备管理Service接口
 * 
 * @author ruoyi
 * @date 2026-03-05
 */
public interface IMonitorDeviceService 
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
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteMonitorDeviceByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteMonitorDeviceByDeviceId(Long deviceId);
}
