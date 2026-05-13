package com.ruoyi.device.service;

import com.ruoyi.device.domain.PhysicalMachineBase;

import java.util.List;

/**
 * 物理机基础信息Service接口
 * 
 * @author ruoyi
 * @date 2026-03-09
 */
public interface IPhysicalMachineBaseService 
{
    /**
     * 查询物理机基础信息
     * 
     * @param machineId 物理机基础信息主键
     * @return 物理机基础信息
     */
    public PhysicalMachineBase selectPhysicalMachineBaseByMachineId(String machineId);

    /**
     * 查询物理机基础信息列表
     * 
     * @param physicalMachineBase 物理机基础信息
     * @return 物理机基础信息集合
     */
    public List<PhysicalMachineBase> selectPhysicalMachineBaseList(PhysicalMachineBase physicalMachineBase);

    /**
     * 新增物理机基础信息
     * 
     * @param physicalMachineBase 物理机基础信息
     * @return 结果
     */
    public int insertPhysicalMachineBase(PhysicalMachineBase physicalMachineBase);

    /**
     * 修改物理机基础信息
     * 
     * @param physicalMachineBase 物理机基础信息
     * @return 结果
     */
    public int updatePhysicalMachineBase(PhysicalMachineBase physicalMachineBase);

    /**
     * 批量删除物理机基础信息
     * 
     * @param machineIds 需要删除的物理机基础信息主键集合
     * @return 结果
     */
    public int deletePhysicalMachineBaseByMachineIds(String[] machineIds);

    /**
     * 删除物理机基础信息信息
     * 
     * @param machineId 物理机基础信息主键
     * @return 结果
     */
    public int deletePhysicalMachineBaseByMachineId(String machineId);
}
