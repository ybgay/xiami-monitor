package com.ruoyi.agent.service;

import com.ruoyi.agent.dto.AgentRequest;
import com.ruoyi.agent.dto.AgentResponse;
import com.ruoyi.agent.dto.ApprovalRequest;
import com.ruoyi.agent.node.AlertAgentNode;
import com.ruoyi.agent.node.K8sAgentNode;
import com.ruoyi.agent.node.SupervisorNode;
import com.ruoyi.agent.node.SystemAgentNode;
import com.ruoyi.device.service.IDatabaseMasterService;
import com.ruoyi.device.service.IJarsAppMasterService;
import com.ruoyi.device.service.IMiddlewareMasterService;
import com.ruoyi.device.service.IPhysicalMachineBaseService;
import com.ruoyi.device.service.IVirtualMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MultiAgentService {

    private static final Logger log = LoggerFactory.getLogger(MultiAgentService.class);

    @Autowired
    private SupervisorNode supervisorNode;
    
    @Autowired
    private K8sAgentNode k8sAgentNode;
    
    @Autowired
    private SystemAgentNode systemAgentNode;
    
    @Autowired
    private AlertAgentNode alertAgentNode;
    
    @Autowired
    private IPhysicalMachineBaseService physicalMachineService;
    
    @Autowired
    private IDatabaseMasterService databaseService;
    
    @Autowired
    private IMiddlewareMasterService middlewareService;
    
    @Autowired
    private IVirtualMasterService virtualService;
    
    @Autowired
    private IJarsAppMasterService jarsService;

    private final Map<String, List<Map<String, Object>>> conversationStore = new ConcurrentHashMap<>();

    public AgentResponse processRequest(AgentRequest request) {
        String threadId = request.getThreadId();
        
        if (threadId == null || threadId.trim().isEmpty()) {
            threadId = "thread-" + System.currentTimeMillis();
            log.warn("[主管] threadId为空，生成新的threadId: {}", threadId);
        }
        
        String userMessage = request.getMessage();
        
        log.info("[主管] 收到用户消息: {}", userMessage);
        
        saveMessage(threadId, "user", "supervisor", userMessage);
        
        String nextAgent = supervisorNode.route(userMessage, getConversationContext(threadId));
        
        log.info("[主管] 路由决策结果: {}", nextAgent);
        
        AgentResponse response;
        
        switch (nextAgent) {
            case "K8S":
                log.info("[主管] 分发任务给 K8s运维助手");
                response = k8sAgentNode.process(userMessage, getConversationContext(threadId));
                response.setAgent("k8s-agent");
                break;
                
            case "SYSTEM":
                log.info("[主管] 分发任务给 系统运维助手");
                response = systemAgentNode.process(userMessage, getConversationContext(threadId));
                response.setAgent("system-agent");
                break;
                
            case "ALERT":
                log.info("[主管] 分发任务给 告警运维助手");
                response = alertAgentNode.process(userMessage, getConversationContext(threadId));
                response.setAgent("alert-agent");
                break;
                
            case "CHAT":
                log.info("[主管] 普通闲聊，直接回复");
                response = supervisorNode.handleChat(userMessage);
                response.setAgent("supervisor");
                break;
                
            case "FINISH":
                log.info("[主管] 任务完成，结束对话");
                response = supervisorNode.handleFinish(userMessage);
                response.setAgent("supervisor");
                break;
                
            default:
                log.warn("[主管] 未知路由: {}，使用默认处理", nextAgent);
                response = supervisorNode.handleDefault(userMessage);
                response.setAgent("supervisor");
        }
        
        saveMessage(threadId, "ai", response.getAgent(), response.getReply());
        
        log.info("[主管] 任务完成，返回响应");
        
        return response;
    }

    public Map<String, Object> handleApproval(ApprovalRequest request) {
        log.info("[审批] 收到审批请求: formId={}, action={}", 
                request.getFormId(), request.getAction());
        
        Map<String, Object> result = new HashMap<>();
        
        if ("approve".equals(request.getAction())) {
            try {
                executeApprovedOperation(request);
                
                result.put("success", true);
                result.put("message", "✅ 操作已成功执行！");
                
                log.info("[审批] 操作执行成功: {}", request.getFormId());
            } catch (Exception e) {
                log.error("[审批] 操作执行失败", e);
                result.put("success", false);
                result.put("message", "❌ 操作执行失败: " + e.getMessage());
            }
        } else {
            result.put("success", false);
            result.put("message", "❌ 操作已取消");
            
            log.info("[审批] 操作已取消: {}", request.getFormId());
        }
        
        return result;
    }

    public List<Map<String, Object>> getConversationHistory(String threadId) {
        return conversationStore.getOrDefault(threadId, new ArrayList<>());
    }

    private void saveMessage(String threadId, String role, String agent, String content) {
        if (threadId == null) {
            log.error("[保存消息] threadId为null，无法保存消息");
            return;
        }
        
        Map<String, Object> message = new HashMap<>();
        message.put("role", role);
        message.put("agent", agent);
        message.put("content", content);
        message.put("timestamp", new Date());
        
        conversationStore.computeIfAbsent(threadId, k -> new ArrayList<>()).add(message);
        
        List<Map<String, Object>> history = conversationStore.get(threadId);
        if (history.size() > 50) {
            history.subList(0, history.size() - 50).clear();
        }
    }

    private String getConversationContext(String threadId) {
        List<Map<String, Object>> history = getConversationHistory(threadId);
        StringBuilder context = new StringBuilder();
        
        for (Map<String, Object> msg : history) {
            String role = (String) msg.get("role");
            String agent = (String) msg.get("agent");
            String content = (String) msg.get("content");
            
            if ("user".equals(role)) {
                context.append("用户: ").append(content).append("\n");
            } else {
                String agentName = getAgentDisplayName(agent);
                context.append(agentName).append(": ").append(content).append("\n");
            }
        }
        
        return context.toString();
    }

    private String getAgentDisplayName(String agent) {
        switch (agent) {
            case "supervisor": return "运维主管";
            case "k8s-agent": return "K8s运维助手";
            case "system-agent": return "系统运维助手";
            case "alert-agent": return "告警运维助手";
            default: return "智能助手";
        }
    }

    private void executeApprovedOperation(ApprovalRequest request) {
        String formId = request.getFormId();
        Map<String, Object> data = request.getData();
        
        log.info("[执行] 开始执行操作: {}, 参数: {}", formId, data);
        
        if (formId.startsWith("add_device_")) {
            executeAddDevice(data);
        } else if (formId.startsWith("update_device_")) {
            executeUpdateDevice(data);
        } else if (formId.startsWith("delete_device_")) {
            executeDeleteDevice(data);
        } else if (formId.startsWith("k8s_operation_")) {
            executeK8sOperation(data);
        } else if (formId.startsWith("alert_config_")) {
            executeAlertConfig(data);
        } else {
            throw new RuntimeException("未知的操作类型: " + formId);
        }
        
        log.info("[执行] 操作执行成功: {}", formId);
    }

    private void executeAddDevice(Map<String, Object> data) {
        String deviceType = (String) data.get("deviceType");
        
        switch (deviceType) {
            case "physical":
                log.info("添加物理机: {}", data);
                break;
            case "virtual":
                log.info("添加虚拟机: {}", data);
                break;
            case "database":
                log.info("添加数据库: {}", data);
                break;
            case "middleware":
                log.info("添加中间件: {}", data);
                break;
            case "jars":
                log.info("添加JAR应用: {}", data);
                break;
            default:
                throw new RuntimeException("不支持的设备类型: " + deviceType);
        }
    }

    private void executeUpdateDevice(Map<String, Object> data) {
        String deviceType = (String) data.get("deviceType");
        String deviceId = (String) data.get("deviceId");
        String updateField = (String) data.get("updateField");
        String newValue = (String) data.get("newValue");
        
        log.info("更新设备: type={}, id={}, field={}, value={}", 
                deviceType, deviceId, updateField, newValue);
    }

    private void executeDeleteDevice(Map<String, Object> data) {
        String deviceType = (String) data.get("deviceType");
        String deviceIds = (String) data.get("deviceIds");
        
        log.info("删除设备: type={}, ids={}", deviceType, deviceIds);
        
        String[] ids = deviceIds.split(",");
        
        switch (deviceType) {
            case "physical":
                break;
            case "virtual":
                break;
            case "database":
                break;
            case "middleware":
                break;
            case "jars":
                break;
            default:
                throw new RuntimeException("不支持的设备类型: " + deviceType);
        }
    }

    private void executeK8sOperation(Map<String, Object> data) {
        log.info("执行K8s操作: {}", data);
    }

    private void executeAlertConfig(Map<String, Object> data) {
        log.info("配置告警规则: {}", data);
    }
}