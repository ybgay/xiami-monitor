package com.ruoyi.device.mapper;

import java.util.List;
import com.ruoyi.device.domain.PhysicalMaster;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-11
 */
public interface PhysicalMasterMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param physicalId 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePhysicalMasterByPhysicalId(Long physicalId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param physicalIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePhysicalMasterByPhysicalIds(Long[] physicalIds);
}
