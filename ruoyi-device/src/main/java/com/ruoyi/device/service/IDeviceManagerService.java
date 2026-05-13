package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.DeviceManager;

/**
 * 设备管理Service接口
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
public interface IDeviceManagerService 
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
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteDeviceManagerByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteDeviceManagerByDeviceId(Long deviceId);
    
    List<DeviceManager> selectDeviceManagerTree();
}
