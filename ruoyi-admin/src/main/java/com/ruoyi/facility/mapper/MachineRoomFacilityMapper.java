package com.ruoyi.facility.mapper;

import java.util.List;
import com.ruoyi.facility.domain.MachineRoomFacility;

/**
 * 机房设施管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-06
 */
public interface MachineRoomFacilityMapper 
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
     * 删除机房设施管理
     * 
     * @param id 机房设施管理主键
     * @return 结果
     */
    public int deleteMachineRoomFacilityById(Long id);

    /**
     * 批量删除机房设施管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMachineRoomFacilityByIds(Long[] ids);
}
