package com.ruoyi.device.mapper;

import java.util.List;
import com.ruoyi.device.domain.DeviceManager;

/**
 * 设备管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
public interface DeviceManagerMapper 
{
    /**
     * 查询设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理
     */
    public DeviceManager selectDeviceManagerByDeviceId(Long deviceId);

    /**
     * 查询设备管理列表
     * 
     * @param deviceManager 设备管理
     * @return 设备管理集合
     */
    public List<DeviceManager> selectDeviceManagerList(DeviceManager deviceManager);

    /**
     * 新增设备管理
     * 
     * @param deviceManager 设备管理
     * @return 结果
     */
    public int insertDeviceManager(DeviceManager deviceManager);

    /**
     * 修改设备管理
     * 
     * @param deviceManager 设备管理
     * @return 结果
     */
    public int updateDeviceManager(DeviceManager deviceManager);

    /**
     * 删除设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteDeviceManagerByDeviceId(Long deviceId);

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceManagerByDeviceIds(Long[] deviceIds);
}
