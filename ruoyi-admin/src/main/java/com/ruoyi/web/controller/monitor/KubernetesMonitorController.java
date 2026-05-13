package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.vo.K8sPodVO;
import com.ruoyi.system.service.IKubernetesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitor/kubernetes")
public class KubernetesMonitorController extends BaseController {

    @Autowired
    private IKubernetesService kubernetesService;

    /**
     * 查询 Pod 列表
     * 注意：不要使用若依原生的 startPage()！PageHelper 无法拦截第三方 API 的内存数据。
     * 分页应该交由 Vue 前端去实现。
     */

    @PreAuthorize("@ss.hasPermi('monitor:kubernetes:list')")
    @GetMapping("/pods")
    public AjaxResult listPods(String namespace, String nodeName) {
        List<K8sPodVO> list = kubernetesService.getPods(namespace, nodeName);
        return AjaxResult.success(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:kubernetes:list')")
    @GetMapping("/pods/detail")
    public AjaxResult getPodDetail(String namespace, String podName) {
        Map<String, Object> detail = kubernetesService.getPodDetail(namespace, podName);
        return AjaxResult.success(detail);
    }

    @GetMapping("/namespaces")
    public AjaxResult getNamespaces() {
        return AjaxResult.success(kubernetesService.getNamespaces());
    }

    @GetMapping("/nodes")
    public AjaxResult getNodes() {
        return AjaxResult.success(kubernetesService.getNodes());
    }

    // ================= Deployment 接口 =================
    @PreAuthorize("@ss.hasPermi('monitor:kubernetes:list')")
    @GetMapping("/deployments")
    public AjaxResult listDeployments(String namespace) {
        return AjaxResult.success(kubernetesService.getDeployments(namespace));
    }

    @PreAuthorize("@ss.hasPermi('monitor:kubernetes:list')")
    @GetMapping("/deployments/detail")
    public AjaxResult getDeploymentDetail(String namespace, String deploymentName) {
        return AjaxResult.success(kubernetesService.getDeploymentDetail(namespace, deploymentName));
    }

    // ================= Service 接口 =================
    @PreAuthorize("@ss.hasPermi('monitor:kubernetes:list')")
    @GetMapping("/services")
    public AjaxResult listServices(String namespace) {
        return AjaxResult.success(kubernetesService.getServices(namespace));
    }

    @PreAuthorize("@ss.hasPermi('monitor:kubernetes:list')")
    @GetMapping("/services/detail")
    public AjaxResult getServiceDetail(String namespace, String serviceName) {
        return AjaxResult.success(kubernetesService.getServiceDetail(namespace, serviceName));
    }
}