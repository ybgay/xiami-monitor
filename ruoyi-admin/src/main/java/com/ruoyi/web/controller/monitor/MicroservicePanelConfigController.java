package com.ruoyi.web.controller.monitor;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.system.domain.MicroservicePanelConfig;
import com.ruoyi.system.service.IMicroservicePanelConfigService;
import com.ruoyi.system.service.IMicroserviceNamespaceService;
import com.ruoyi.system.service.IMicroserviceServiceService;

/**
 * 微服务监控面板配置 + 命名空间管理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/panel")
public class MicroservicePanelConfigController extends BaseController
{
    @Autowired
    private IMicroservicePanelConfigService panelConfigService;

    @Autowired
    private IMicroserviceNamespaceService namespaceService;

    @Autowired
    private IMicroserviceServiceService serviceService;


    // ==================== 微服务命名空间管理 ====================

    /**
     * 查询已保存的微服务命名空间列表
     */
    @GetMapping("/ns/list")
    public AjaxResult listNamespaces()
    {
        List<String> list = namespaceService.listNamespaces();
        return success(list);
    }

    /**
     * 保存微服务命名空间列表（全量替换）
     * 请求体：{ "namespaces": ["ns1", "ns2", ...] }
     */
    @Log(title = "微服务命名空间配置", businessType = BusinessType.UPDATE)
    @PostMapping("/ns/save")
    public AjaxResult saveNamespaces(@RequestBody Map<String, List<String>> body)
    {
        List<String> namespaces = body.get("namespaces");
        namespaceService.saveNamespaces(namespaces);
        return success();
    }

    // ==================== 面板配置 ====================

    /**
     * 查询面板配置列表（按 namespace + service + pod 过滤）
     */
    @PreAuthorize("@ss.hasPermi('monitor:panel:list')")
    @GetMapping("/list")
    public AjaxResult list(MicroservicePanelConfig config)
    {
        List<MicroservicePanelConfig> list = panelConfigService.selectPanelConfigList(config);
        return success(list);
    }

    /**
     * 根据主键查询面板配置详情
     */
    @PreAuthorize("@ss.hasPermi('monitor:panel:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(panelConfigService.selectPanelConfigById(id));
    }

    /**
     * 保存面板配置（upsert：存在则更新，不存在则新增）
     */
    @PreAuthorize("@ss.hasPermi('monitor:panel:edit')")
    @Log(title = "微服务监控面板配置", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult save(@Validated @RequestBody MicroservicePanelConfig config)
    {
        return toAjax(panelConfigService.savePanelConfig(config));
    }

    /**
     * 新增面板配置
     */
    @PreAuthorize("@ss.hasPermi('monitor:panel:add')")
    @Log(title = "微服务监控面板配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody MicroservicePanelConfig config)
    {
        return toAjax(panelConfigService.insertPanelConfig(config));
    }

    /**
     * 修改面板配置
     */
    @PreAuthorize("@ss.hasPermi('monitor:panel:edit')")
    @Log(title = "微服务监控面板配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody MicroservicePanelConfig config)
    {
        return toAjax(panelConfigService.updatePanelConfig(config));
    }

    /**
     * 删除面板配置
     */
    @PreAuthorize("@ss.hasPermi('monitor:panel:remove')")
    @Log(title = "微服务监控面板配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(panelConfigService.deletePanelConfigByIds(ids));
    }

    /**
     * 查询指定命名空间的微服务 Service 列表
     */
    @GetMapping("/microservice/services")
    public AjaxResult listServices(String namespace)
    {
        List<String> list = serviceService.listServices(namespace);
        return success(list);
    }

    /**
     * 保存指定命名空间的微服务 Service 列表
     * 请求体：{ "namespace": "xxx", "services": ["svc1", "svc2", ...] }
     */
    @Log(title = "微服务 Service 配置", businessType = BusinessType.UPDATE)
    @PostMapping("/microservice/services")
    public AjaxResult saveServices(@RequestBody Map<String, Object> body)
    {
        String namespace = (String) body.get("namespace");
        @SuppressWarnings("unchecked")
        List<String> services = (List<String>) body.get("services");
        serviceService.saveServices(namespace, services);
        return success();
    }
}
