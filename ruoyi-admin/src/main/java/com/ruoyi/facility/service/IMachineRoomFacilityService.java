package com.ruoyi.facility.service;

import java.util.List;
import com.ruoyi.facility.domain.MachineRoomFacility;

/**
 * 机房设施管理Service接口
 * 
 * @author ruoyi
 * @date 2026-03-06
 */
public interface IMachineRoomFacilityService 
{
    /**
     * 查询机房设施管理
     * 
     * @param id 机房设施管理主键
     * @return 机房设施管理
     */
    public MachineRoomFacility selectMachineRoomFacilityById(Long id);

    /**
     * 查询机房设施管理列表
     * 
     * @param machineRoomFacility 机房设施管理
     * @return 机房设施管理集合
     */
    public List<MachineRoomFacility> selectMachineRoomFacilityList(MachineRoomFacility machineRoomFacility);

    /**
     * 新增机房设施管理
     * 
     * @param machineRoomFacility 机房设施管理
     * @return 结果
     */
    public int insertMachineRoomFacility(MachineRoomFacility machineRoomFacility);

    /**
     * 修改机房设施管理
     * 
     * @param machineRoomFacility 机房设施管理
     * @return 结果
     */
    public int updateMachineRoomFacility(MachineRoomFacility machineRoomFacility);

    /**
     * 批量删除机房设施管理
     * 
     * @param ids 需要删除的机房设施管理主键集合
     * @return 结果
     */
    public int deleteMachineRoomFacilityByIds(Long[] ids);

    /**
     * 删除机房设施管理信息
     * 
     * @param id 机房设施管理主键
     * @return 结果
     */
    public int deleteMachineRoomFacilityById(Long id);
}
