package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.PhysicalMaster;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2026-03-11
 */
public interface IPhysicalMasterService 
{
       
    public List<PhysicalMaster> selectPhysicalMasterByDeviceId(int deviceId);


    /**
     * 查询【请填写功能名称】
     * 
     * @param physicalId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public PhysicalMaster selectPhysicalMasterByPhysicalId(Long physicalId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param physicalMaster 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PhysicalMaster> selectPhysicalMasterList(PhysicalMaster physicalMaster);

    /**
     * 新增【请填写功能名称】
     * 
     * @param physicalMaster 【请填写功能名称】
     * @return 结果
     */
    public int insertPhysicalMaster(PhysicalMaster physicalMaster);

    /**
     * 修改【请填写功能名称】
     * 
     * @param physicalMaster 【请填写功能名称】
     * @return 结果
     */
    public int updatePhysicalMaster(PhysicalMaster physicalMaster);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param physicalIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deletePhysicalMasterByPhysicalIds(Long[] physicalIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param physicalId 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePhysicalMasterByPhysicalId(Long physicalId);
}
