package com.ruoyi.system.service;

import com.ruoyi.system.domain.vo.K8sPodVO;
import com.ruoyi.system.domain.vo.K8sDeploymentVO;
import com.ruoyi.system.domain.vo.K8sServiceVO;
import java.util.List;
import java.util.Map;

public interface IKubernetesService {
    /**
     * 获取指定命名空间和节点下的所有 Pod 信息
     */
    List<K8sPodVO> getPods(String namespace, String nodeName);
    /**
     * 获取 Pod 详情（模拟 kubectl describe）
     */
    Map<String, Object> getPodDetail(String namespace, String podName);
    /** 获取所有命名空间名称 */
    List<String> getNamespaces();
    /** 获取所有节点名称 */
    List<String> getNodes();

    // Deployment 相关
    List<K8sDeploymentVO> getDeployments(String namespace);
    Map<String, Object> getDeploymentDetail(String namespace, String deploymentName);

    // Service 相关
    List<K8sServiceVO> getServices(String namespace);
    Map<String, Object> getServiceDetail(String namespace, String serviceName);
}