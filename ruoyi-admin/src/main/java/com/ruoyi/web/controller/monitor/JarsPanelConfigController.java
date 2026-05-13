package com.ruoyi.web.controller.monitor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.JarsPanelConfig;
import com.ruoyi.system.service.IJarsPanelConfigService;

/**
 * Jars应用监控面板配置
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/jars/panel")
public class JarsPanelConfigController extends BaseController
{
    @Autowired
    private IJarsPanelConfigService panelConfigService;

    /**
     * 查询面板配置列表（按 device + category + appName 过滤）
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:list')")
    @GetMapping("/list")
    public AjaxResult list(JarsPanelConfig config)
    {
        List<JarsPanelConfig> list = panelConfigService.selectPanelConfigList(config);
        return success(list);
    }

    /**
     * 根据主键查询面板配置详情
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(panelConfigService.selectPanelConfigById(id));
    }

    /**
     * 保存面板配置（upsert：存在则更新，不存在则新增）
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:edit')")
    @Log(title = "Jars监控面板配置", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult save(@Validated @RequestBody JarsPanelConfig config)
    {
        return toAjax(panelConfigService.savePanelConfig(config));
    }

    /**
     * 新增面板配置
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:add')")
    @Log(title = "Jars监控面板配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody JarsPanelConfig config)
    {
        return toAjax(panelConfigService.insertPanelConfig(config));
    }

    /**
     * 修改面板配置
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:edit')")
    @Log(title = "Jars监控面板配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody JarsPanelConfig config)
    {
        return toAjax(panelConfigService.updatePanelConfig(config));
    }

    /**
     * 删除面板配置
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:remove')")
    @Log(title = "Jars监控面板配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(panelConfigService.deletePanelConfigByIds(ids));
    }

    /**
     * 批量更新面板排序和宽度
     * 请求体：[{ id, sortOrder, groupSortOrder, panelSpan }, ...]
     */
    @PreAuthorize("@ss.hasPermi('monitor:jars:edit')")
    @Log(title = "Jars监控面板配置", businessType = BusinessType.UPDATE)
    @PutMapping("/batchSort")
    public AjaxResult batchSort(@RequestBody List<JarsPanelConfig> list)
    {
        return toAjax(panelConfigService.batchUpdateSort(list));
    }
}
