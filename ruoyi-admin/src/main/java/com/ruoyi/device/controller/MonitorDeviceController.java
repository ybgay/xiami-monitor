package com.ruoyi.device.controller;

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
import com.ruoyi.device.domain.MonitorDevice;
import com.ruoyi.device.service.IMonitorDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备管理Controller
 * 
 * @author ruoyi
 * @date 2026-03-05
 */
@RestController
@RequestMapping("/device/device")
public class MonitorDeviceController extends BaseController
{
    @Autowired
    private IMonitorDeviceService monitorDeviceService;

    /**
     * 查询设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:device:list')")
    @GetMapping("/list")
    public AjaxResult list(MonitorDevice monitorDevice)
    {
        List<MonitorDevice> list = monitorDeviceService.selectMonitorDeviceList(monitorDevice);
        return success(list);
    }

    /**
     * 导出设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:device:export')")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MonitorDevice monitorDevice)
    {
        List<MonitorDevice> list = monitorDeviceService.selectMonitorDeviceList(monitorDevice);
        ExcelUtil<MonitorDevice> util = new ExcelUtil<MonitorDevice>(MonitorDevice.class);
        util.exportExcel(response, list, "设备管理数据");
    }

    /**
     * 获取设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:device:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return success(monitorDeviceService.selectMonitorDeviceByDeviceId(deviceId));
    }

    /**
     * 新增设备管理
     */
    @PreAuthorize("@ss.hasPermi('device:device:add')")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MonitorDevice monitorDevice)
    {
        return toAjax(monitorDeviceService.insertMonitorDevice(monitorDevice));
    }

    /**
     * 修改设备管理
     */
    @PreAuthorize("@ss.hasPermi('device:device:edit')")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MonitorDevice monitorDevice)
    {
        return toAjax(monitorDeviceService.updateMonitorDevice(monitorDevice));
    }

    /**
     * 删除设备管理
     */
    @PreAuthorize("@ss.hasPermi('device:device:remove')")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds)
    {
        return toAjax(monitorDeviceService.deleteMonitorDeviceByDeviceIds(deviceIds));
    }
}
