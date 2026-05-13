package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.PhysicalRoom;

import java.util.List;

public interface PhysicalRoomMapper {

    /**
     * 查询机房管理数据
     *
     * @param room 机房信息
     * @return 机房信息集合
     */
    public List<PhysicalRoom> selectRoomList(PhysicalRoom room);

    /**
     * 根据机房ID查询信息
     *
     * @param roomId 机房ID
     * @return 机房信息
     */
    public PhysicalRoom selectRoomById(Long roomId);

    /**
     * 新增机房信息
     *
     * @param room 机房信息
     * @return 结果
     */
    public int insertRoom(PhysicalRoom room);

    /**
     * 修改机房信息
     *
     * @param room 机房信息
     * @return 结果
     */
    public int updateRoom(PhysicalRoom room);

    /**
     * 删除机房管理信息
     *
     * @param roomId 机房ID
     * @return 结果
     */
    public int deleteRoomById(Long roomId);
}
