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
import com.ruoyi.device.domain.DatabaseMaster;
import com.ruoyi.device.service.IDatabaseMasterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 数据库管理Controller
 * 
 * @author 
 * @date 2026-03-13
 */
@RestController
@RequestMapping("/device/database")
public class DatabaseMasterController extends BaseController
{
    @Autowired
    private IDatabaseMasterService databaseMasterService;

    /**
     * 查询数据库管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:database:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatabaseMaster databaseMaster)
    {
        startPage();
        List<DatabaseMaster> list = databaseMasterService.selectDatabaseMasterList(databaseMaster);
        return getDataTable(list);
    }

    /**
     * 导出数据库管理列表
     */
    @PreAuthorize("@ss.hasPermi('device:database:export')")
    @Log(title = "数据库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DatabaseMaster databaseMaster)
    {
        List<DatabaseMaster> list = databaseMasterService.selectDatabaseMasterList(databaseMaster);
        ExcelUtil<DatabaseMaster> util = new ExcelUtil<DatabaseMaster>(DatabaseMaster.class);
        util.exportExcel(response, list, "数据库管理数据");
    }

    /**
     * 获取数据库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:database:query')")
    @GetMapping(value = "/{databaseId}")
    public AjaxResult getInfo(@PathVariable("databaseId") Long databaseId)
    {
        return success(databaseMasterService.selectDatabaseMasterByDatabaseId(databaseId));
    }

    /**
     * 新增数据库管理
     */
    @PreAuthorize("@ss.hasPermi('device:database:add')")
    @Log(title = "数据库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatabaseMaster databaseMaster)
    {
        return toAjax(databaseMasterService.insertDatabaseMaster(databaseMaster));
    }

    /**
     * 修改数据库管理
     */
    @PreAuthorize("@ss.hasPermi('device:database:edit')")
    @Log(title = "数据库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatabaseMaster databaseMaster)
    {
        return toAjax(databaseMasterService.updateDatabaseMaster(databaseMaster));
    }

    /**
     * 删除数据库管理
     */
    @PreAuthorize("@ss.hasPermi('device:database:remove')")
    @Log(title = "数据库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{databaseIds}")
    public AjaxResult remove(@PathVariable Long[] databaseIds)
    {
        return toAjax(databaseMasterService.deleteDatabaseMasterByDatabaseIds(databaseIds));
    }
}
