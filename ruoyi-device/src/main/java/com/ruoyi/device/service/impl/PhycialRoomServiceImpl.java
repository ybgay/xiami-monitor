package com.ruoyi.device.service.impl;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.device.domain.PhysicalRoom;
import com.ruoyi.device.mapper.PhysicalRoomMapper;
import com.ruoyi.device.service.PhycialRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 机房管理 服务层实现
 *
 * @author ruoyi
 */
@Service
public class PhycialRoomServiceImpl implements PhycialRoomService
{
    @Autowired
    private PhysicalRoomMapper roomMapper;

    /**
     * 查询机房管理数据
     *
     * @param room 机房信息
     * @return 机房信息集合
     */
    @Override
    public List<PhysicalRoom> selectRoomList(PhysicalRoom room)
    {
        return roomMapper.selectRoomList(room);
    }

    /**
     * 根据机房ID查询信息
     *
     * @param roomId 机房ID
     * @return 机房信息
     */
    @Override
    public PhysicalRoom selectRoomById(Long roomId)
    {
        return roomMapper.selectRoomById(roomId);
    }

    /**
     * 新增机房信息
     *
     * @param room 机房信息
     * @return 结果
     */
    @Override
    public int insertRoom(PhysicalRoom room)
    {
        return roomMapper.insertRoom(room);
    }

    /**
     * 修改机房信息
     *
     * @param room 机房信息
     * @return 结果
     */
    @Override
    public int updateRoom(PhysicalRoom room)
    {
        return roomMapper.updateRoom(room);
    }

    /**
     * 删除机房管理信息
     *
     * @param roomId 机房ID
     * @return 结果
     */
    @Override
    public int deleteRoomById(Long roomId)
    {
        return roomMapper.deleteRoomById(roomId);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param rooms 机房列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildRoomTreeSelect(List<PhysicalRoom> rooms)
    {
        List<PhysicalRoom> roomTrees = buildRoomTree(rooms);
        return roomTrees.stream().map(this::newTreeSelect).collect(Collectors.toList());
    }

    /**
     * 将SysRoom转换为TreeSelect对象
     */
    private TreeSelect newTreeSelect(PhysicalRoom room)
    {
        TreeSelect treeSelect = new TreeSelect();
        treeSelect.setId(room.getRoomId());
        treeSelect.setLabel(room.getRoomName());
        List<PhysicalRoom> children = (List<PhysicalRoom>) room.getChildren();
        treeSelect.setMachineType(room.getMachineType());

        if (children != null && !children.isEmpty())
        {
            treeSelect.setChildren(children.stream().map(this::newTreeSelect).collect(Collectors.toList()));
        }
        return treeSelect;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param rooms 机房列表
     * @return 树结构列表
     */
    public List<PhysicalRoom> buildRoomTree(List<PhysicalRoom> rooms)
    {
        List<PhysicalRoom> returnList = new ArrayList<PhysicalRoom>();
        List<Long> tempList = new ArrayList<Long>();
        for (PhysicalRoom room : rooms)
        {
            tempList.add(room.getRoomId());
        }
        for (Iterator<PhysicalRoom> iterator = rooms.iterator(); iterator.hasNext();)
        {
            PhysicalRoom room = (PhysicalRoom) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(room.getParentId()))
            {
                recursionFn(rooms, room);
                returnList.add(room);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = rooms;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<PhysicalRoom> list, PhysicalRoom t)
    {
        // 得到子节点列表
        List<PhysicalRoom> childList = getChildList(list, t);
        t.setChildren(childList);
        for (PhysicalRoom tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<PhysicalRoom> getChildList(List<PhysicalRoom> list, PhysicalRoom t)
    {
        List<PhysicalRoom> tlist = new ArrayList<PhysicalRoom>();
        Iterator<PhysicalRoom> it = list.iterator();
        while (it.hasNext())
        {
            PhysicalRoom n = (PhysicalRoom) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getRoomId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<PhysicalRoom> list, PhysicalRoom t)
    {
        return getChildList(list, t).size() > 0;
    }
}