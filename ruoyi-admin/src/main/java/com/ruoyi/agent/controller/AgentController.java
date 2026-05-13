package com.ruoyi.agent.controller;

import com.ruoyi.agent.dto.AgentRequest;
import com.ruoyi.agent.dto.AgentResponse;
import com.ruoyi.agent.dto.ApprovalRequest;
import com.ruoyi.agent.service.MultiAgentService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
@Anonymous
public class AgentController {

    private static final Logger log = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    private MultiAgentService multiAgentService;

    @PostMapping("/chat")
    public AjaxResult chat(@RequestBody AgentRequest request) {
        try {
            log.info("收到聊天请求: message={}, threadId={}", request.getMessage(), request.getThreadId());
            AgentResponse response = multiAgentService.processRequest(request);
            log.info("聊天请求处理成功");
            return AjaxResult.success(response);
        } catch (Exception e) {
            log.error("处理聊天请求失败", e);
            String errorMsg = e.getMessage() != null ? e.getMessage() : "未知错误，请查看后端日志";
            return AjaxResult.error("处理请求失败: " + errorMsg);
        }
    }

    @GetMapping("/history/{threadId}")
    public AjaxResult getHistory(@PathVariable String threadId) {
        try {
            return AjaxResult.success(multiAgentService.getConversationHistory(threadId));
        } catch (Exception e) {
            log.error("获取历史失败", e);
            String errorMsg = e.getMessage() != null ? e.getMessage() : "未知错误";
            return AjaxResult.error("获取历史失败: " + errorMsg);
        }
    }

    @PostMapping("/approve")
    public AjaxResult approve(@RequestBody ApprovalRequest request) {
        try {
            return AjaxResult.success(multiAgentService.handleApproval(request));
        } catch (Exception e) {
            log.error("审批失败", e);
            String errorMsg = e.getMessage() != null ? e.getMessage() : "未知错误";
            return AjaxResult.error("审批失败: " + errorMsg);
        }
    }
}