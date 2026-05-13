package com.ruoyi.device.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.device.domain.PhysicalMachineBase;
import com.ruoyi.device.mapper.PhysicalMachineBaseMapper;
import com.ruoyi.device.service.IPhysicalMachineBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物理机基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-09
 */
@Service
public class PhysicalMachineBaseServiceImpl implements IPhysicalMachineBaseService 
{
    @Autowired
    private PhysicalMachineBaseMapper physicalMachineBaseMapper;

    /**
     * 查询物理机基础信息
     * 
     * @param machineId 物理机基础信息主键
     * @return 物理机基础信息
     */
    @Override
    public PhysicalMachineBase selectPhysicalMachineBaseByMachineId(String machineId)
    {
        return physicalMachineBaseMapper.selectPhysicalMachineBaseByMachineId(machineId);
    }

    /**
     * 查询物理机基础信息列表
     * 
     * @param physicalMachineBase 物理机基础信息
     * @return 物理机基础信息
     */
    @Override
    public List<PhysicalMachineBase> selectPhysicalMachineBaseList(PhysicalMachineBase physicalMachineBase)
    {
        return physicalMachineBaseMapper.selectPhysicalMachineBaseList(physicalMachineBase);
    }

    /**
     * 新增物理机基础信息
     * 
     * @param physicalMachineBase 物理机基础信息
     * @return 结果
     */
    @Override
    public int insertPhysicalMachineBase(PhysicalMachineBase physicalMachineBase)
    {
        physicalMachineBase.setCreateTime(DateUtils.getNowDate());
        return physicalMachineBaseMapper.insertPhysicalMachineBase(physicalMachineBase);
    }

    /**
     * 修改物理机基础信息
     * 
     * @param physicalMachineBase 物理机基础信息
     * @return 结果
     */
    @Override
    public int updatePhysicalMachineBase(PhysicalMachineBase physicalMachineBase)
    {
        physicalMachineBase.setUpdateTime(DateUtils.getNowDate());
        return physicalMachineBaseMapper.updatePhysicalMachineBase(physicalMachineBase);
    }

    /**
     * 批量删除物理机基础信息
     * 
     * @param machineIds 需要删除的物理机基础信息主键
     * @return 结果
     */
    @Override
    public int deletePhysicalMachineBaseByMachineIds(String[] machineIds)
    {
        return physicalMachineBaseMapper.deletePhysicalMachineBaseByMachineIds(machineIds);
    }

    /**
     * 删除物理机基础信息信息
     * 
     * @param machineId 物理机基础信息主键
     * @return 结果
     */
    @Override
    public int deletePhysicalMachineBaseByMachineId(String machineId)
    {
        return physicalMachineBaseMapper.deletePhysicalMachineBaseByMachineId(machineId);
    }
}
