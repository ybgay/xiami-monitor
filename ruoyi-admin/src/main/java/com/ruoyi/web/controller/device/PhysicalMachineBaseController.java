package com.ruoyi.web.controller.device;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.device.domain.PhysicalMachineBase;
import com.ruoyi.device.service.IPhysicalMachineBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 物理机基础信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-09
 */
@RestController
@RequestMapping("/system/base")
public class PhysicalMachineBaseController extends BaseController
{
    @Autowired
    private IPhysicalMachineBaseService physicalMachineBaseService;

    /**
     * 查询物理机基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:base:list')")
    @GetMapping("/list")
    public TableDataInfo list(PhysicalMachineBase physicalMachineBase)
    {
        startPage();
        List<PhysicalMachineBase> list = physicalMachineBaseService.selectPhysicalMachineBaseList(physicalMachineBase);
        return getDataTable(list);
    }

    /**
     * 导出物理机基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:base:export')")
    @Log(title = "物理机基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PhysicalMachineBase physicalMachineBase)
    {
        List<PhysicalMachineBase> list = physicalMachineBaseService.selectPhysicalMachineBaseList(physicalMachineBase);
        ExcelUtil<PhysicalMachineBase> util = new ExcelUtil<PhysicalMachineBase>(PhysicalMachineBase.class);
        util.exportExcel(response, list, "物理机基础信息数据");
    }

    /**
     * 获取物理机基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:base:query')")
    @GetMapping(value = "/{machineId}")
    public AjaxResult getInfo(@PathVariable("machineId") String machineId)
    {
        return success(physicalMachineBaseService.selectPhysicalMachineBaseByMachineId(machineId));
    }

    /**
     * 新增物理机基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:base:add')")
    @Log(title = "物理机基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PhysicalMachineBase physicalMachineBase)
    {
        return toAjax(physicalMachineBaseService.insertPhysicalMachineBase(physicalMachineBase));
    }

    /**
     * 修改物理机基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:base:edit')")
    @Log(title = "物理机基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PhysicalMachineBase physicalMachineBase)
    {
        return toAjax(physicalMachineBaseService.updatePhysicalMachineBase(physicalMachineBase));
    }

    /**
     * 删除物理机基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:base:remove')")
    @Log(title = "物理机基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{machineIds}")
    public AjaxResult remove(@PathVariable String[] machineIds)
    {
        return toAjax(physicalMachineBaseService.deletePhysicalMachineBaseByMachineIds(machineIds));
    }
}
