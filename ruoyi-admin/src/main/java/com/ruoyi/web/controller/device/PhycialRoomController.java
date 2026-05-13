package com.ruoyi.web.controller.device;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.device.domain.PhysicalRoom;
import com.ruoyi.device.service.PhycialRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机房信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/room")
public class PhycialRoomController extends BaseController
{
    @Autowired
    private PhycialRoomService roomService;

    /**
     * 获取机房列表
     */
    @PreAuthorize("@ss.hasPermi('system:room:list')")
    @GetMapping("/list")
    public AjaxResult list(PhysicalRoom room)
    {
        List<PhysicalRoom> list = roomService.selectRoomList(room);
        return success(list);
    }

    /**
     * 获取机房下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(PhysicalRoom room)
    {
        List<PhysicalRoom> rooms = roomService.selectRoomList(room);
        return success(roomService.buildRoomTreeSelect(rooms));
    }

    /**
     * 根据机房编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:room:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable Long roomId)
    {
        return success(roomService.selectRoomById(roomId));
    }

    /**
     * 新增机房
     */
    @PreAuthorize("@ss.hasPermi('system:room:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PhysicalRoom room)
    {
        room.setCreateBy(getUsername());
        return toAjax(roomService.insertRoom(room));
    }

    /**
     * 修改机房
     */
    @PreAuthorize("@ss.hasPermi('system:room:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PhysicalRoom room)
    {
        room.setUpdateBy(getUsername());
        return toAjax(roomService.updateRoom(room));
    }

    /**
     * 删除机房
     */
    @PreAuthorize("@ss.hasPermi('system:room:remove')")
    @DeleteMapping("/{roomId}")
    public AjaxResult remove(@PathVariable Long roomId)
    {
        return toAjax(roomService.deleteRoomById(roomId));
    }
}