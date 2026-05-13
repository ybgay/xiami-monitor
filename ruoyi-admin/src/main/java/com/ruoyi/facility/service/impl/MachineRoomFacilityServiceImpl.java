package com.ruoyi.facility.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.facility.mapper.MachineRoomFacilityMapper;
import com.ruoyi.facility.domain.MachineRoomFacility;
import com.ruoyi.facility.service.IMachineRoomFacilityService;

/**
 * 机房设施管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-06
 */
@Service
public class MachineRoomFacilityServiceImpl implements IMachineRoomFacilityService 
{
    @Autowired
    private MachineRoomFacilityMapper machineRoomFacilityMapper;

    /**
     * 查询机房设施管理
     * 
     * @param id 机房设施管理主键
     * @return 机房设施管理
     */
    @Override
    public MachineRoomFacility selectMachineRoomFacilityById(Long id)
    {
        return machineRoomFacilityMapper.selectMachineRoomFacilityById(id);
    }

    /**
     * 查询机房设施管理列表
     * 
     * @param machineRoomFacility 机房设施管理
     * @return 机房设施管理
     */
    @Override
    public List<MachineRoomFacility> selectMachineRoomFacilityList(MachineRoomFacility machineRoomFacility)
    {
        return machineRoomFacilityMapper.selectMachineRoomFacilityList(machineRoomFacility);
    }

    /**
     * 新增机房设施管理
     * 
     * @param machineRoomFacility 机房设施管理
     * @return 结果
     */
    @Override
    public int insertMachineRoomFacility(MachineRoomFacility machineRoomFacility)
    {
        machineRoomFacility.setCreateTime(DateUtils.getNowDate());
        return machineRoomFacilityMapper.insertMachineRoomFacility(machineRoomFacility);
    }

    /**
     * 修改机房设施管理
     * 
     * @param machineRoomFacility 机房设施管理
     * @return 结果
     */
    @Override
    public int updateMachineRoomFacility(MachineRoomFacility machineRoomFacility)
    {
        machineRoomFacility.setUpdateTime(DateUtils.getNowDate());
        return machineRoomFacilityMapper.updateMachineRoomFacility(machineRoomFacility);
    }

    /**
     * 批量删除机房设施管理
     * 
     * @param ids 需要删除的机房设施管理主键
     * @return 结果
     */
    @Override
    public int deleteMachineRoomFacilityByIds(Long[] ids)
    {
        return machineRoomFacilityMapper.deleteMachineRoomFacilityByIds(ids);
    }

    /**
     * 删除机房设施管理信息
     * 
     * @param id 机房设施管理主键
     * @return 结果
     */
    @Override
    public int deleteMachineRoomFacilityById(Long id)
    {
        return machineRoomFacilityMapper.deleteMachineRoomFacilityById(id);
    }
}
