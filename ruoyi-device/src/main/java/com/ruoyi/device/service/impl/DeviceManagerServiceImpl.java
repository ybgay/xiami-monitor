package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.DeviceManagerMapper;
import com.ruoyi.device.domain.DeviceManager;
import com.ruoyi.device.service.IDeviceManagerService;
import java.util.ArrayList;

/**
 * 设备管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
@Service
public class DeviceManagerServiceImpl implements IDeviceManagerService 
{
    @Autowired
    private DeviceManagerMapper deviceManagerMapper;

    /**
     * 查询设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理
     */
    @Override
    public DeviceManager selectDeviceManagerByDeviceId(Long deviceId)
    {
        return deviceManagerMapper.selectDeviceManagerByDeviceId(deviceId);
    }

    /**
     * 查询设备管理列表
     * 
     * @param deviceManager 设备管理
     * @return 设备管理
     */
    @Override
    public List<DeviceManager> selectDeviceManagerList(DeviceManager deviceManager)
    {
        return deviceManagerMapper.selectDeviceManagerList(deviceManager);
    }

    /**
     * 新增设备管理
     * 
     * @param deviceManager 设备管理
     * @return 结果
     */
    @Override
    public int insertDeviceManager(DeviceManager deviceManager)
    {
        deviceManager.setCreateTime(DateUtils.getNowDate());
        return deviceManagerMapper.insertDeviceManager(deviceManager);
    }

    /**
     * 修改设备管理
     * 
     * @param deviceManager 设备管理
     * @return 结果
     */
    @Override
    public int updateDeviceManager(DeviceManager deviceManager)
    {
        deviceManager.setUpdateTime(DateUtils.getNowDate());
        return deviceManagerMapper.updateDeviceManager(deviceManager);
    }

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceManagerByDeviceIds(Long[] deviceIds)
    {
        return deviceManagerMapper.deleteDeviceManagerByDeviceIds(deviceIds);
    }

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceManagerByDeviceId(Long deviceId)
    {
        return deviceManagerMapper.deleteDeviceManagerByDeviceId(deviceId);
    }


        /**
     * 查询设备管理树状图
     */
    @Override
    public List<DeviceManager> selectDeviceManagerTree()
    {
        DeviceManager deviceManager = new DeviceManager();
        deviceManager.setDelFlag(0L);
        List<DeviceManager> list = deviceManagerMapper.selectDeviceManagerList(deviceManager);
        return buildTree(list, 0L);
    }

     /**
     * 构建树状结构
     */
    private List<DeviceManager> buildTree(List<DeviceManager> list, Long parentId)
    {
        List<DeviceManager> tree = new ArrayList<>();
        for (DeviceManager item : list) {
            if (parentId.equals(item.getParentId())) {
                List<DeviceManager> children = buildTree(list, item.getDeviceId());
                if (!children.isEmpty()) {
                    item.setChildren(children);
                }
                tree.add(item);
            }
        }
        return tree;
    }
}
