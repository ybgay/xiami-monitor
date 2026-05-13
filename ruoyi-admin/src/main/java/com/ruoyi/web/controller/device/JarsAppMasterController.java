package com.ruoyi.web.controller.device;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.device.domain.JarsAppMaster;
import com.ruoyi.device.service.IJarsAppMasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * JAR应用管理 Controller
 *
 * @author ruoyi
 * @date 2026-03-17
 */
@RestController
@RequestMapping("/device/jars")
public class JarsAppMasterController extends BaseController
{
    @Autowired
    private IJarsAppMasterService jarsAppMasterService;

    /** 查询JAR应用列表 */
    @PreAuthorize("@ss.hasPermi('device:jars:list')")
    @GetMapping("/list")
    public TableDataInfo list(JarsAppMaster jarsAppMaster)
    {
        startPage();
        List<JarsAppMaster> list = jarsAppMasterService.selectJarsAppMasterList(jarsAppMaster);
        return getDataTable(list);
    }

    /** 导出JAR应用列表 */
    @PreAuthorize("@ss.hasPermi('device:jars:export')")
    @Log(title = "JAR应用管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JarsAppMaster jarsAppMaster)
    {
        List<JarsAppMaster> list = jarsAppMasterService.selectJarsAppMasterList(jarsAppMaster);
        ExcelUtil<JarsAppMaster> util = new ExcelUtil<>(JarsAppMaster.class);
        util.exportExcel(response, list, "JAR应用管理数据");
    }

    /** 获取JAR应用详细信息 */
    @PreAuthorize("@ss.hasPermi('device:jars:query')")
    @GetMapping("/{jarsId}")
    public AjaxResult getInfo(@PathVariable Long jarsId)
    {
        return success(jarsAppMasterService.selectJarsAppMasterByJarsId(jarsId));
    }

    /** 新增JAR应用 */
    @PreAuthorize("@ss.hasPermi('device:jars:add')")
    @Log(title = "JAR应用管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JarsAppMaster jarsAppMaster)
    {
        return toAjax(jarsAppMasterService.insertJarsAppMaster(jarsAppMaster));
    }

    /** 修改JAR应用 */
    @PreAuthorize("@ss.hasPermi('device:jars:edit')")
    @Log(title = "JAR应用管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JarsAppMaster jarsAppMaster)
    {
        return toAjax(jarsAppMasterService.updateJarsAppMaster(jarsAppMaster));
    }

    /** 删除JAR应用 */
    @PreAuthorize("@ss.hasPermi('device:jars:remove')")
    @Log(title = "JAR应用管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jarsIds}")
    public AjaxResult remove(@PathVariable Long[] jarsIds)
    {
        return toAjax(jarsAppMasterService.deleteJarsAppMasterByJarsIds(jarsIds));
    }
}