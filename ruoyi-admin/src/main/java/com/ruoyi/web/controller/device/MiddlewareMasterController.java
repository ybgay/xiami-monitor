package com.ruoyi.web.controller.device;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.device.domain.PhysicalMaster;
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
import com.ruoyi.device.domain.MiddlewareMaster;
import com.ruoyi.device.service.IMiddlewareMasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 中间件管理Controller
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
@RestController
@RequestMapping("/device/middware")
public class MiddlewareMasterController extends BaseController
{

    @GetMapping("/getListByDeviceId")
    public TableDataInfo getListByDeviceId(int deviceId)
    {
        List<MiddlewareMaster> middlewareMasters = middlewareMasterService.selectMiddwareMasterByDeviceId(deviceId);
        return getDataTable(middlewareMasters);
    }
    @Autowired
    private IMiddlewareMasterService middlewareMasterService;

    /**
     * 查询中间件管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:middware:list')")
    @GetMapping("/list")
    public TableDataInfo list(MiddlewareMaster middlewareMaster)
    {
        startPage();
        List<MiddlewareMaster> list = middlewareMasterService.selectMiddlewareMasterList(middlewareMaster);
        return getDataTable(list);
    }

    /**
     * 导出中间件管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:middware:export')")
    @Log(title = "中间件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MiddlewareMaster middlewareMaster)
    {
        List<MiddlewareMaster> list = middlewareMasterService.selectMiddlewareMasterList(middlewareMaster);
        ExcelUtil<MiddlewareMaster> util = new ExcelUtil<MiddlewareMaster>(MiddlewareMaster.class);
        util.exportExcel(response, list, "中间件管理数据");
    }

    /**
     * 获取中间件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:middware:query')")
    @GetMapping(value = "/{middlewareId}")
    public AjaxResult getInfo(@PathVariable("middlewareId") Long middlewareId)
    {
        return success(middlewareMasterService.selectMiddlewareMasterByMiddlewareId(middlewareId));
    }

    /**
     * 新增中间件管理
     */
    @PreAuthorize("@ss.hasPermi('device:middware:add')")
    @Log(title = "中间件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MiddlewareMaster middlewareMaster)
    {
        return toAjax(middlewareMasterService.insertMiddlewareMaster(middlewareMaster));
    }

    /**
     * 修改中间件管理
     */
    @PreAuthorize("@ss.hasPermi('device:middware:edit')")
    @Log(title = "中间件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MiddlewareMaster middlewareMaster)
    {
        return toAjax(middlewareMasterService.updateMiddlewareMaster(middlewareMaster));
    }

    /**
     * 删除中间件管理
     */
    @PreAuthorize("@ss.hasPermi('device:middware:remove')")
    @Log(title = "中间件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{middlewareIds}")
    public AjaxResult remove(@PathVariable Long[] middlewareIds)
    {
        return toAjax(middlewareMasterService.deleteMiddlewareMasterByMiddlewareIds(middlewareIds));
    }


}
