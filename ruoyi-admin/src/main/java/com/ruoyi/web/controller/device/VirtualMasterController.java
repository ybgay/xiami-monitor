package com.ruoyi.web.controller.device;

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
import com.ruoyi.device.domain.VirtualMaster;
import com.ruoyi.device.service.IVirtualMasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 虚拟机管理Controller
 * 
 * @author zly
 * @date 2026-03-17
 */
@RestController
@RequestMapping("/device/virtual")
public class VirtualMasterController extends BaseController
{
    @Autowired
    private IVirtualMasterService virtualMasterService;

    /**
     * 查询虚拟机管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:virtual:list')")
    @GetMapping("/list")
    public TableDataInfo list(VirtualMaster virtualMaster)
    {
        startPage();
        List<VirtualMaster> list = virtualMasterService.selectVirtualMasterList(virtualMaster);
        return getDataTable(list);
    }

    /**
     * 导出虚拟机管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:virtual:export')")
    @Log(title = "虚拟机管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VirtualMaster virtualMaster)
    {
        List<VirtualMaster> list = virtualMasterService.selectVirtualMasterList(virtualMaster);
        ExcelUtil<VirtualMaster> util = new ExcelUtil<VirtualMaster>(VirtualMaster.class);
        util.exportExcel(response, list, "虚拟机管理数据");
    }

    /**
     * 获取虚拟机管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:virtual:query')")
    @GetMapping(value = "/{virtualId}")
    public AjaxResult getInfo(@PathVariable("virtualId") Long virtualId)
    {
        return success(virtualMasterService.selectVirtualMasterByVirtualId(virtualId));
    }

    /**
     * 新增虚拟机管理
     */
    @PreAuthorize("@ss.hasPermi('device:virtual:add')")
    @Log(title = "虚拟机管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VirtualMaster virtualMaster)
    {
        return toAjax(virtualMasterService.insertVirtualMaster(virtualMaster));
    }

    /**
     * 修改虚拟机管理
     */
    @PreAuthorize("@ss.hasPermi('device:virtual:edit')")
    @Log(title = "虚拟机管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VirtualMaster virtualMaster)
    {
        return toAjax(virtualMasterService.updateVirtualMaster(virtualMaster));
    }

    /**
     * 删除虚拟机管理
     */
    @PreAuthorize("@ss.hasPermi('device:virtual:remove')")
    @Log(title = "虚拟机管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{virtualIds}")
    public AjaxResult remove(@PathVariable Long[] virtualIds)
    {
        return toAjax(virtualMasterService.deleteVirtualMasterByVirtualIds(virtualIds));
    }
}
