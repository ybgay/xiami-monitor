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
import com.ruoyi.device.domain.PhysicalMaster;
import com.ruoyi.device.service.IPhysicalMasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;


/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2026-03-11
 */
@RestController
@RequestMapping("/device/master")
public class PhysicalMasterController extends BaseController
{
    @Autowired
    private IPhysicalMasterService physicalMasterService;



    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('device:master:list')")
    @GetMapping("/list")
    public TableDataInfo list(PhysicalMaster physicalMaster)
    {
        startPage();
        List<PhysicalMaster> list = physicalMasterService.selectPhysicalMasterList(physicalMaster);
         
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('device:master:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PhysicalMaster physicalMaster)
    {
        List<PhysicalMaster> list = physicalMasterService.selectPhysicalMasterList(physicalMaster);
        ExcelUtil<PhysicalMaster> util = new ExcelUtil<PhysicalMaster>(PhysicalMaster.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:master:query')")
    @GetMapping(value = "/{physicalId}")
    public AjaxResult getInfo(@PathVariable("physicalId") Long physicalId)
    {
        return success(physicalMasterService.selectPhysicalMasterByPhysicalId(physicalId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('device:master:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PhysicalMaster physicalMaster)
    {
        return toAjax(physicalMasterService.insertPhysicalMaster(physicalMaster));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('device:master:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PhysicalMaster physicalMaster)
    {
        System.out.println(physicalMaster.toString());
        return toAjax(physicalMasterService.updatePhysicalMaster(physicalMaster));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('device:master:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{physicalIds}")
    public AjaxResult remove(@PathVariable Long[] physicalIds)
    {
        return toAjax(physicalMasterService.deletePhysicalMasterByPhysicalIds(physicalIds));
    }

    @GetMapping("/getListByDeviceId")
    public TableDataInfo getListByDeviceId(int deviceId)
    {
        List<PhysicalMaster> list = physicalMasterService.selectPhysicalMasterByDeviceId(deviceId);
        return getDataTable(list);
    }
}
