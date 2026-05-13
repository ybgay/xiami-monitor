package com.ruoyi.facility.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.facility.domain.MachineRoomFacility;
import com.ruoyi.facility.service.IMachineRoomFacilityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 机房设施管理Controller
 * 
 * @author ruoyi
 * @date 2026-03-06
 */
@RestController
@RequestMapping("/facility/machine")
public class MachineRoomFacilityController extends BaseController
{
    @Autowired
    private IMachineRoomFacilityService machineRoomFacilityService;

    /**
     * 查询机房设施管理列表
     */
    @PreAuthorize("@ss.hasPermi('facility:machine:list')")
    @GetMapping("/list")
    public TableDataInfo list(MachineRoomFacility machineRoomFacility)
    {
        startPage();
        List<MachineRoomFacility> list = machineRoomFacilityService.selectMachineRoomFacilityList(machineRoomFacility);
        return getDataTable(list);
    }

    /**
     * 导出机房设施管理列表
     */
    @PreAuthorize("@ss.hasPermi('facility:machine:export')")
    @Log(title = "机房设施管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MachineRoomFacility machineRoomFacility)
    {
        List<MachineRoomFacility> list = machineRoomFacilityService.selectMachineRoomFacilityList(machineRoomFacility);
        ExcelUtil<MachineRoomFacility> util = new ExcelUtil<MachineRoomFacility>(MachineRoomFacility.class);
        util.exportExcel(response, list, "机房设施管理数据");
    }

    /**
     * 获取机房设施管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('facility:machine:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(machineRoomFacilityService.selectMachineRoomFacilityById(id));
    }

    /**
     * 新增机房设施管理
     */
    @PreAuthorize("@ss.hasPermi('facility:machine:add')")
    @Log(title = "机房设施管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MachineRoomFacility machineRoomFacility)
    {
        return toAjax(machineRoomFacilityService.insertMachineRoomFacility(machineRoomFacility));
    }

    /**
     * 修改机房设施管理
     */
    @PreAuthorize("@ss.hasPermi('facility:machine:edit')")
    @Log(title = "机房设施管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MachineRoomFacility machineRoomFacility)
    {
        return toAjax(machineRoomFacilityService.updateMachineRoomFacility(machineRoomFacility));
    }

    /**
     * 删除机房设施管理
     */
    @PreAuthorize("@ss.hasPermi('facility:machine:remove')")
    @Log(title = "机房设施管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(machineRoomFacilityService.deleteMachineRoomFacilityByIds(ids));
    }
}
