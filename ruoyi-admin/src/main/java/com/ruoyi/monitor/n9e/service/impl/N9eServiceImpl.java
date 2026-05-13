package com.ruoyi.monitor.n9e.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.monitor.n9e.service.IN9eService;
import com.ruoyi.web.core.config.N9eConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class N9eServiceImpl implements IN9eService {

    private static final Logger log = LoggerFactory.getLogger(N9eServiceImpl.class);

    @Autowired
    private N9eConfig n9eConfig;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // token 缓存
    private volatile String cachedToken = null;
    private volatile long tokenExpireAt = 0L;

    // ==================== Token 管理 ====================

    private String getToken() {
        long now = System.currentTimeMillis() / 1000;
        if (cachedToken != null && tokenExpireAt > now + 60) {
            return cachedToken;
        }
        return refreshToken();
    }

    private synchronized String refreshToken() {
        try {
            String url = n9eConfig.getBaseUrl() + "/api/n9e/auth/login";
            
            String body = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", 
                n9eConfig.getUsername(), n9eConfig.getPassword());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> resp = restTemplate.postForEntity(url, entity, String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            String token = root.path("dat").path("access_token").asText();
            long expireAt = root.path("dat").path("access_token_expire_at").asLong();
            if (StringUtils.hasText(token)) {
                this.cachedToken = token;
                this.tokenExpireAt = expireAt;
                log.info("N9e token 刷新成功");
                return token;
            }
        } catch (Exception e) {
            log.error("N9e token 获取失败", e);
        }
        return null;
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getToken();
        if (token != null) {
            headers.setBearerAuth(token);
        }
        return headers;
    }

    // ==================== 接口实现 ====================

    @Override
    public Object listActiveAlerts(Map<String, Object> params) {
        try {
            int p = params != null && params.containsKey("p") ? (int) params.get("p") : 1;
            int limit = params != null && params.containsKey("limit") ? (int) params.get("limit") : 20;
            String query = params != null && params.containsKey("query") ? (String) params.get("query") : null;
            String severity = params != null && params.containsKey("severity") ? (String) params.get("severity") : null;

            String url = n9eConfig.getBaseUrl() + "/api/n9e/alert-cur-events/list?p=" + p + "&limit=" + limit;
            if (StringUtils.hasText(query)) url += "&query=" + query;
            if (StringUtils.hasText(severity)) url += "&severity=" + severity;
            
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, 
                new HttpEntity<>(buildHeaders()), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            JsonNode dat = root.path("dat");
            return objectMapper.convertValue(dat, Object.class);
        } catch (Exception e) {
            log.error("获取活跃告警失败: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Object listAlertRules(Map<String, Object> params) {
        try {
            int p = params != null && params.containsKey("p") ? (int) params.get("p") : 0;
            int limit = params != null && params.containsKey("limit") ? (int) params.get("limit") : 20;
            String query = params != null && params.containsKey("query") ? (String) params.get("query") : null;
            long bgid = n9eConfig.getDefaultBusiGroup();

            String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rules?p=" + p + "&limit=" + limit;
            if (StringUtils.hasText(query)) url += "&query=" + query;
            
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, 
                new HttpEntity<>(buildHeaders()), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            return objectMapper.convertValue(root, Object.class);
        } catch (Exception e) {
            log.error("获取告警规则失败: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Object listHistoryAlerts(Map<String, Object> params) {
        try {
            int p = params != null && params.containsKey("p") ? (int) params.get("p") : 0;
            int limit = params != null && params.containsKey("limit") ? (int) params.get("limit") : 20;
            String query = params != null && params.containsKey("query") ? (String) params.get("query") : null;
            String severity = params != null && params.containsKey("severity") ? (String) params.get("severity") : null;
            
            // 默认查最近 24 小时
            long now = System.currentTimeMillis() / 1000;
            long startTime = params != null && params.containsKey("stime") ? (long) params.get("stime") : now - 86400;
            long endTime = params != null && params.containsKey("etime") ? (long) params.get("etime") : now;

            String url = n9eConfig.getBaseUrl() + "/api/n9e/alert-his-events/list?p=" + p + "&limit=" + limit
                + "&stime=" + startTime + "&etime=" + endTime;
            if (StringUtils.hasText(query)) url += "&query=" + query;
            if (StringUtils.hasText(severity)) url += "&severity=" + severity;
            
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, 
                new HttpEntity<>(buildHeaders()), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            JsonNode dat = root.path("dat");
            return objectMapper.convertValue(dat, Object.class);
        } catch (Exception e) {
            log.error("获取历史告警失败: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Object listMonitorDevices(Map<String, Object> params) {
        // 这个方法需要根据实际的设备管理服务来实现
        // 目前返回空，表示需要集成设备管理服务
        log.warn("listMonitorDevices 方法需要集成设备管理服务");
        return null;
    }
}