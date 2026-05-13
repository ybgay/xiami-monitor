package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.vo.K8sPodVO;
import com.ruoyi.system.domain.vo.K8sDeploymentVO;
import com.ruoyi.system.domain.vo.K8sServiceVO;
import com.ruoyi.system.service.IKubernetesService;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Event;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.utils.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KubernetesServiceImpl implements IKubernetesService {

    @Autowired
    private KubernetesClient client;

    @Override
    public List<K8sPodVO> getPods(String namespace, String nodeName) {
        PodList podList;
        if (StringUtils.hasText(namespace)) {
            podList = client.pods().inNamespace(namespace).list();
        } else {
            podList = client.pods().inAnyNamespace().list();
        }

        List<K8sPodVO> voList = new ArrayList<>();
        for (Pod pod : podList.getItems()) {
            // 增加节点过滤逻辑
            String currentPodNode = pod.getSpec().getNodeName();
            if (StringUtils.hasText(nodeName) && !nodeName.equals(currentPodNode)) {
                continue;
            }

            K8sPodVO vo = new K8sPodVO();
            vo.setName(pod.getMetadata().getName());
            vo.setNamespace(pod.getMetadata().getNamespace());
            vo.setCreationTimestamp(pod.getMetadata().getCreationTimestamp());
            vo.setPodIp(pod.getStatus().getPodIP());
            vo.setNodeName(currentPodNode);
            vo.setStatus(pod.getStatus().getPhase());

            int restarts = 0;
            List<ContainerStatus> containerStatuses = pod.getStatus().getContainerStatuses();
            if (containerStatuses != null) {
                for (ContainerStatus cs : containerStatuses) {
                    restarts += cs.getRestartCount();
                }
            }
            vo.setRestartCount(restarts);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Map<String, Object> getPodDetail(String namespace, String podName) {
        Map<String, Object> result = new HashMap<>();
        
        // 1. 获取 Pod 原始对象并转为 YAML 字符串 (包含 labels, annotations, spec, status)
        Pod pod = client.pods().inNamespace(namespace).withName(podName).get();
        if (pod != null) {
            result.put("yaml", Serialization.asYaml(pod));
            result.put("pod", pod);
        } else {
            result.put("yaml", "Pod not found");
        }

        // 2. 获取该 Pod 相关的 Events (模拟 describe 最下方的 Events 列表)
        List<Event> events = client.v1().events().inNamespace(namespace)
                .withField("involvedObject.name", podName)
                .withField("involvedObject.kind", "Pod")
                .list().getItems();
        
        List<String> eventLogs = events.stream()
                .map(e -> String.format("[%s] %s: %s", e.getType(), e.getReason(), e.getMessage()))
                .collect(Collectors.toList());
        
        result.put("events", eventLogs);
        
        return result;
    }

    @Override
    public List<String> getNamespaces() {
        return client.namespaces().list().getItems().stream()
                .map(n -> n.getMetadata().getName())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getNodes() {
        return client.nodes().list().getItems().stream()
                .map(n -> n.getMetadata().getName())
                .collect(Collectors.toList());
    }

    // ================= Deployment 业务逻辑 =================
    @Override
    public List<K8sDeploymentVO> getDeployments(String namespace) {
        io.fabric8.kubernetes.api.model.apps.DeploymentList list;
        if (StringUtils.hasText(namespace)) {
            list = client.apps().deployments().inNamespace(namespace).list();
        } else {
            list = client.apps().deployments().inAnyNamespace().list();
        }

        List<K8sDeploymentVO> voList = new ArrayList<>();
        for (io.fabric8.kubernetes.api.model.apps.Deployment d : list.getItems()) {
            K8sDeploymentVO vo = new K8sDeploymentVO();
            vo.setName(d.getMetadata().getName());
            vo.setNamespace(d.getMetadata().getNamespace());
            vo.setCreationTimestamp(d.getMetadata().getCreationTimestamp());
            
            // 副本状态
            vo.setReplicas(d.getSpec().getReplicas());
            vo.setReadyReplicas(d.getStatus().getReadyReplicas() != null ? d.getStatus().getReadyReplicas() : 0);
            vo.setAvailableReplicas(d.getStatus().getAvailableReplicas() != null ? d.getStatus().getAvailableReplicas() : 0);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Map<String, Object> getDeploymentDetail(String namespace, String deploymentName) {
        Map<String, Object> result = new HashMap<>();
        io.fabric8.kubernetes.api.model.apps.Deployment deploy = client.apps().deployments().inNamespace(namespace).withName(deploymentName).get();
        if (deploy != null) {
            result.put("yaml", Serialization.asYaml(deploy));
            result.put("deployment", deploy);
        }
        
        // 获取事件
        List<Event> events = client.v1().events().inNamespace(namespace)
                .withField("involvedObject.name", deploymentName)
                .withField("involvedObject.kind", "Deployment")
                .list().getItems();
        List<String> eventLogs = events.stream()
                .map(e -> String.format("[%s] %s: %s", e.getType(), e.getReason(), e.getMessage()))
                .collect(Collectors.toList());
        result.put("events", eventLogs);
        return result;
    }

    // ================= Service 业务逻辑 =================
    @Override
    public List<K8sServiceVO> getServices(String namespace) {
        io.fabric8.kubernetes.api.model.ServiceList list;
        if (StringUtils.hasText(namespace)) {
            list = client.services().inNamespace(namespace).list();
        } else {
            list = client.services().inAnyNamespace().list();
        }

        List<K8sServiceVO> voList = new ArrayList<>();
        for (io.fabric8.kubernetes.api.model.Service s : list.getItems()) {
            K8sServiceVO vo = new K8sServiceVO();
            vo.setName(s.getMetadata().getName());
            vo.setNamespace(s.getMetadata().getNamespace());
            vo.setCreationTimestamp(s.getMetadata().getCreationTimestamp());
            vo.setType(s.getSpec().getType());
            vo.setClusterIp(s.getSpec().getClusterIP());
            
            // 拼接外部 IP
            List<String> externalIps = s.getSpec().getExternalIPs();
            if (externalIps != null && !externalIps.isEmpty()) {
                vo.setExternalIp(String.join(", ", externalIps));
            } else {
                vo.setExternalIp("<none>");
            }

            // 拼接端口列表 (如 80:30080/TCP)
            List<String> portStrs = new ArrayList<>();
            if (s.getSpec().getPorts() != null) {
                for (io.fabric8.kubernetes.api.model.ServicePort port : s.getSpec().getPorts()) {
                    String pStr = port.getPort() + ":" + (port.getNodePort() != null ? port.getNodePort() : port.getTargetPort().getIntVal()) + "/" + port.getProtocol();
                    portStrs.add(pStr);
                }
            }
            vo.setPorts(String.join(", ", portStrs));
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Map<String, Object> getServiceDetail(String namespace, String serviceName) {
        Map<String, Object> result = new HashMap<>();
        io.fabric8.kubernetes.api.model.Service svc = client.services().inNamespace(namespace).withName(serviceName).get();
        if (svc != null) {
            result.put("yaml", Serialization.asYaml(svc));
            result.put("service", svc);
        }

        // 【新增】：获取同名的 Endpoints 资源，并传给前端
        io.fabric8.kubernetes.api.model.Endpoints endpoints = client.endpoints().inNamespace(namespace).withName(serviceName).get();
        if (endpoints != null) {
            result.put("endpoints", endpoints);
        }

        List<Event> events = client.v1().events().inNamespace(namespace)
                .withField("involvedObject.name", serviceName)
                .withField("involvedObject.kind", "Service")
                .list().getItems();
        List<String> eventLogs = events.stream()
                .map(e -> String.format("[%s] %s: %s", e.getType(), e.getReason(), e.getMessage()))
                .collect(Collectors.toList());
        result.put("events", eventLogs);
        return result;
    }
}