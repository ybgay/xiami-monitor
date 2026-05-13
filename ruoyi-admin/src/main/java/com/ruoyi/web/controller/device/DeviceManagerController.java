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
import com.ruoyi.device.domain.DeviceManager;
import com.ruoyi.device.service.IDeviceManagerService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备管理Controller
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
@RestController
@RequestMapping("/device/manager")
public class DeviceManagerController extends BaseController
{
    @Autowired
    private IDeviceManagerService deviceManagerService;


    @GetMapping("/tree")
    public AjaxResult getTree()
    {
        List<DeviceManager> tree = deviceManagerService.selectDeviceManagerTree();
        return AjaxResult.success(tree);
    }

    /**
     * 查询设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:manager:list')")
    @GetMapping("/list")
    public AjaxResult list(DeviceManager deviceManager)
    {
        List<DeviceManager> list = deviceManagerService.selectDeviceManagerList(deviceManager);
        return success(list);
    }

    /**
     * 导出设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:manager:export')")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceManager deviceManager)
    {
        List<DeviceManager> list = deviceManagerService.selectDeviceManagerList(deviceManager);
        ExcelUtil<DeviceManager> util = new ExcelUtil<DeviceManager>(DeviceManager.class);
        util.exportExcel(response, list, "设备管理数据");
    }

    /**
     * 获取设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:manager:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return success(deviceManagerService.selectDeviceManagerByDeviceId(deviceId));
    }

    /**
     * 新增设备管理
     */
    @PreAuthorize("@ss.hasPermi('device:manager:add')")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceManager deviceManager)
    {
        return toAjax(deviceManagerService.insertDeviceManager(deviceManager));
    }

    /**
     * 修改设备管理
     */
    @PreAuthorize("@ss.hasPermi('device:manager:edit')")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceManager deviceManager)
    {
        return toAjax(deviceManagerService.updateDeviceManager(deviceManager));
    }

    /**
     * 删除设备管理
     */
    @PreAuthorize("@ss.hasPermi('device:manager:remove')")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds)
    {
        return toAjax(deviceManagerService.deleteDeviceManagerByDeviceIds(deviceIds));
    }
}
