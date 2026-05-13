package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.MonitorDeviceMapper;
import com.ruoyi.device.domain.MonitorDevice;
import com.ruoyi.device.service.IMonitorDeviceService;

/**
 * 设备管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-05
 */
@Service
public class MonitorDeviceServiceImpl implements IMonitorDeviceService 
{
    @Autowired
    private MonitorDeviceMapper monitorDeviceMapper;

    /**
     * 查询设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理
     */
    @Override
    public MonitorDevice selectMonitorDeviceByDeviceId(Long deviceId)
    {
        return monitorDeviceMapper.selectMonitorDeviceByDeviceId(deviceId);
    }

    /**
     * 查询设备管理列表
     * 
     * @param monitorDevice 设备管理
     * @return 设备管理
     */
    @Override
    public List<MonitorDevice> selectMonitorDeviceList(MonitorDevice monitorDevice)
    {
        return monitorDeviceMapper.selectMonitorDeviceList(monitorDevice);
    }

    /**
     * 新增设备管理
     * 
     * @param monitorDevice 设备管理
     * @return 结果
     */
    @Override
    public int insertMonitorDevice(MonitorDevice monitorDevice)
    {
        monitorDevice.setCreateTime(DateUtils.getNowDate());
        return monitorDeviceMapper.insertMonitorDevice(monitorDevice);
    }

    /**
     * 修改设备管理
     * 
     * @param monitorDevice 设备管理
     * @return 结果
     */
    @Override
    public int updateMonitorDevice(MonitorDevice monitorDevice)
    {
        monitorDevice.setUpdateTime(DateUtils.getNowDate());
        return monitorDeviceMapper.updateMonitorDevice(monitorDevice);
    }

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteMonitorDeviceByDeviceIds(Long[] deviceIds)
    {
        return monitorDeviceMapper.deleteMonitorDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteMonitorDeviceByDeviceId(Long deviceId)
    {
        return monitorDeviceMapper.deleteMonitorDeviceByDeviceId(deviceId);
    }
}
