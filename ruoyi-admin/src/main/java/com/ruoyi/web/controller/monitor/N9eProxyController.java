package com.ruoyi.web.controller.monitor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.core.config.N9eConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringEscapeUtils;

@RestController
@RequestMapping("/monitor/n9e")
public class N9eProxyController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(N9eProxyController.class);

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
            ObjectNode body = objectMapper.createObjectNode();
            body.put("username", n9eConfig.getUsername());
            body.put("password", n9eConfig.getPassword());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(body), headers);

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

    // ==================== 配置信息接口 ====================

    /** 获取当前N9e配置（脱敏） */
    @GetMapping("/config")
    public AjaxResult getConfig() {
        Map<String, Object> info = new java.util.HashMap<>();
        info.put("baseUrl", n9eConfig.getBaseUrl());
        info.put("username", n9eConfig.getUsername());
        info.put("defaultBusiGroup", n9eConfig.getDefaultBusiGroup());
        return success(info);
    }

    /** 更新N9e配置 */
    @PostMapping("/config")
    public AjaxResult updateConfig(@RequestBody Map<String, Object> body) {
        if (body.containsKey("baseUrl")) n9eConfig.setBaseUrl((String) body.get("baseUrl"));
        if (body.containsKey("username")) n9eConfig.setUsername((String) body.get("username"));
        if (body.containsKey("password") && StringUtils.hasText((String) body.get("password"))) {
            n9eConfig.setPassword((String) body.get("password"));
        }
        if (body.containsKey("defaultBusiGroup")) {
            n9eConfig.setDefaultBusiGroup(Long.valueOf(body.get("defaultBusiGroup").toString()));
        }
        // 重置token缓存，下次请求重新登录
        this.cachedToken = null;
        this.tokenExpireAt = 0L;
        return success("配置已更新");
    }

    // ==================== 告警规则 ====================

    @GetMapping("/alert-rules")
    public AjaxResult listAlertRules(
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(required = false) String query) {
        try {
            long bgid = n9eConfig.getDefaultBusiGroup();
            String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rules?p=" + p + "&limit=" + limit;
            if (StringUtils.hasText(query)) url += "&query=" + query;
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            return success(objectMapper.convertValue(root, Object.class));
        } catch (Exception e) {
            log.error("获取告警规则失败: {}", e.getMessage());
            return error("获取告警规则失败: " + e.getMessage());
        }
    }

    @PostMapping("/alert-rules")
    public AjaxResult createAlertRule(@RequestBody String body) {
        try {
            long bgid = n9eConfig.getDefaultBusiGroup();
            
            // 解码 HTML 实体
            String decodedBody = StringEscapeUtils.unescapeHtml4(body);
            
            String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rules";
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, 
                new HttpEntity<>(decodedBody, buildHeaders()), String.class);
            JsonNode respRoot = objectMapper.readTree(resp.getBody());
            return success(objectMapper.convertValue(respRoot, Object.class));
        } catch (Exception e) {
            log.error("创建告警规则失败: {}", e.getMessage());
            return error("创建告警规则失败: " + e.getMessage());
        }
    }

    @PutMapping("/alert-rules")
    public AjaxResult updateAlertRule(@RequestBody String body) {
        try {
            JsonNode root = objectMapper.readTree(body);
            long id = root.path("id").asLong();
            long bgid = n9eConfig.getDefaultBusiGroup();
            
            // 解码 HTML 实体
            String decodedBody = StringEscapeUtils.unescapeHtml4(body);
            
            String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rule/" + id;
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.PUT, 
                new HttpEntity<>(decodedBody, buildHeaders()), String.class);
            JsonNode respRoot = objectMapper.readTree(resp.getBody());
            return success(objectMapper.convertValue(respRoot, Object.class));
        } catch (Exception e) {
            log.error("更新告警规则失败: {}", e.getMessage());
            return error("更新告警规则失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/alert-rules")
    public ResponseEntity<String> deleteAlertRules(@RequestBody String body) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rules";
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @PutMapping("/alert-rules/status")
    public AjaxResult updateAlertRuleStatus(@RequestBody String body) {
        try {
            JsonNode root = objectMapper.readTree(body);
            JsonNode ids = root.path("ids");
            boolean disabled = root.path("disabled").asBoolean();
            long bgid = n9eConfig.getDefaultBusiGroup();
            
            // 构建 fields 更新请求
            ObjectNode updateBody = objectMapper.createObjectNode();
            updateBody.put("ids", ids);
            ObjectNode fields = objectMapper.createObjectNode();
            fields.put("disabled", disabled ? 1 : 0);
            updateBody.set("fields", fields);
            
            String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rules/fields";
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.PUT, 
                new HttpEntity<>(objectMapper.writeValueAsString(updateBody), buildHeaders()), String.class);
            JsonNode respRoot = objectMapper.readTree(resp.getBody());
            return success(objectMapper.convertValue(respRoot, Object.class));
        } catch (Exception e) {
            log.error("更新告警规则状态失败: {}", e.getMessage());
            return error("更新告警规则状态失败: " + e.getMessage());
        }
    }

    @GetMapping("/alert-rules/options")
    public AjaxResult listAlertRuleOptions(@RequestParam(defaultValue = "0") int p,
                                           @RequestParam(defaultValue = "200") int limit,
                                           @RequestParam(required = false) String query) {
        try {
            long bgid = n9eConfig.getDefaultBusiGroup();
            String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-rules?p=" + p + "&limit=" + limit;
            if (StringUtils.hasText(query)) {
                url += "&query=" + query;
            }
    
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            JsonNode dat = root.path("dat");
    
            // dat 可能是 list，或者 {list,total}
            JsonNode listNode = dat.isArray() ? dat : dat.path("list");
            java.util.List<Map<String, Object>> options = new java.util.ArrayList<>();
            if (listNode.isArray()) {
                for (JsonNode item : listNode) {
                    Map<String, Object> m = new java.util.HashMap<>();
                    m.put("id", item.path("id").asLong());
                    m.put("name", item.path("name").asText());
                    m.put("disabled", item.path("disabled").asInt());
                    m.put("severity", item.path("severity").asInt());
                    options.add(m);
                }
            }
            return success(options);
        } catch (Exception e) {
            log.error("获取告警规则下拉失败: {}", e.getMessage());
            return error("获取告警规则下拉失败: " + e.getMessage());
        }
    }

    @GetMapping("/datasources")
    public AjaxResult listDatasources() {
        try {
            String url = n9eConfig.getBaseUrl() + "/api/n9e/datasource/list";
            HttpEntity<String> entity = new HttpEntity<>("{}", buildHeaders());
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            JsonNode respRoot = objectMapper.readTree(resp.getBody());
            // N9e datasource/list 返回 {"data":[...],"error":""} 格式
            JsonNode dat = respRoot.path("data");
            if (dat.isNull() || dat.isMissingNode()) {
                dat = respRoot.path("dat");
            }
            return success(objectMapper.convertValue(dat, Object.class));
        } catch (Exception e) {
            log.error("获取数据源失败: {}", e.getMessage());
            return error("获取数据源失败: " + e.getMessage());
        }
    }

    // ==================== 活跃告警 ====================

    @GetMapping("/alert-cur-events")
    public AjaxResult listActiveAlerts(
            @RequestParam(defaultValue = "1") int p,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String severity) {
        try {
            String url = n9eConfig.getBaseUrl() + "/api/n9e/alert-cur-events/list?p=" + p + "&limit=" + limit;
            if (StringUtils.hasText(query)) url += "&query=" + query;
            if (StringUtils.hasText(severity)) url += "&severity=" + severity;
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());
            JsonNode dat = root.path("dat");
            return success(objectMapper.convertValue(dat, Object.class));
        } catch (Exception e) {
            log.error("获取活跃告警失败: {}", e.getMessage());
            return error("获取活跃告警失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/alert-cur-events")
    public ResponseEntity<String> deleteActiveAlerts(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/alert-cur-events";
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    // ==================== 历史告警 ====================

    @GetMapping("/alert-his-events")
    public AjaxResult listHistoryAlerts(
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) Long stime,
            @RequestParam(required = false) Long etime) {
        try {
            // 默认查最近 24 小时
            long now = System.currentTimeMillis() / 1000;
            long startTime = stime != null ? stime : now - 86400;
            long endTime = etime != null ? etime : now;

            String url = n9eConfig.getBaseUrl() + "/api/n9e/alert-his-events/list?p=" + p + "&limit=" + limit
                + "&stime=" + startTime + "&etime=" + endTime;
            if (StringUtils.hasText(query)) url += "&query=" + query;
            if (StringUtils.hasText(severity)) url += "&severity=" + severity;
            if (stime != null) url += "&stime=" + stime;
            if (etime != null) url += "&etime=" + etime;
            log.info("活跃告警请求URL: {}", url);
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
            log.info("活跃告警原始响应: {}", resp.getBody());
            JsonNode root = objectMapper.readTree(resp.getBody());
            JsonNode dat = root.path("dat");
            return success(objectMapper.convertValue(dat, Object.class));
        } catch (Exception e) {
            log.error("获取历史告警失败: {}", e.getMessage());
            return error("获取历史告警失败: " + e.getMessage());
        }
    }

    // ==================== 告警屏蔽 ====================

    @GetMapping("/alert-mutes")
    public ResponseEntity<String> listSilences(@RequestParam(required = false) String query) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-mutes?limit=200";
        if (StringUtils.hasText(query)) url += "&query=" + query;
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @PostMapping("/alert-mutes")
    public ResponseEntity<String> createSilence(@RequestBody String body) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-mutes";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @DeleteMapping("/alert-mutes")
    public ResponseEntity<String> deleteSilences(@RequestBody String body) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-mutes";
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    // ==================== 告警订阅 ====================

    @GetMapping("/alert-subscribes")
    public ResponseEntity<String> listSubscribes() {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-subscribes";
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @PostMapping("/alert-subscribes")
    public ResponseEntity<String> createSubscribe(@RequestBody String body) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-subscribes";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @PutMapping("/alert-subscribes")
    public ResponseEntity<String> updateSubscribe(@RequestBody String body) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-subscribes";
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @DeleteMapping("/alert-subscribes")
    public ResponseEntity<String> deleteSubscribes(@RequestBody String body) {
        long bgid = n9eConfig.getDefaultBusiGroup();
        String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-group/" + bgid + "/alert-subscribes";
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    // ==================== 通知渠道 ====================

    @GetMapping("/notify-channels")
    public ResponseEntity<String> listNotifyChannels() {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-channels";
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @PostMapping("/notify-channels")
    public ResponseEntity<String> createNotifyChannel(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-channels";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @PutMapping("/notify-channels")
    public ResponseEntity<String> updateNotifyChannel(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-channels";
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @DeleteMapping("/notify-channels")
    public ResponseEntity<String> deleteNotifyChannels(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-channels";
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @GetMapping("/message-templates")
    public ResponseEntity<String> listMessageTemplates(
            @RequestParam("notify_channel_ids") String notifyChannelIds,
            @RequestParam(required = false) Integer p,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String query) {
        StringBuilder url = new StringBuilder(n9eConfig.getBaseUrl() + "/api/n9e/message-templates?notify_channel_ids=" + notifyChannelIds);
        if (p != null) {
            url.append("&p=").append(p);
        }
        if (limit != null) {
            url.append("&limit=").append(limit);
        }
        if (StringUtils.hasText(query)) {
            url.append("&query=").append(query);
        }
        return restTemplate.exchange(url.toString(), HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @GetMapping("/notify-tpls")
    public ResponseEntity<String> listNotifyTpls(
            @RequestParam(required = false) Integer p,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String query) {
        StringBuilder url = new StringBuilder(n9eConfig.getBaseUrl() + "/api/n9e/notify-tpls");
        boolean hasQ = false;
        if (p != null) {
            url.append(hasQ ? "&" : "?").append("p=").append(p);
            hasQ = true;
        }
        if (limit != null) {
            url.append(hasQ ? "&" : "?").append("limit=").append(limit);
            hasQ = true;
        }
        if (StringUtils.hasText(query)) {
            url.append(hasQ ? "&" : "?").append("query=").append(query);
        }
        return restTemplate.exchange(url.toString(), HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @PutMapping("/notify-tpls")
    public ResponseEntity<String> updateNotifyTpl(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-tpls";
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    // ==================== 通知规则（官方页面使用 notify-rules） ====================

    @GetMapping("/notify-rules")
    public ResponseEntity<String> listNotifyRules(
            @RequestParam(required = false) Integer p,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String query) {
        StringBuilder url = new StringBuilder(n9eConfig.getBaseUrl() + "/api/n9e/notify-rules");
        boolean hasQ = false;
        if (p != null) {
            url.append(hasQ ? "&" : "?").append("p=").append(p);
            hasQ = true;
        }
        if (limit != null) {
            url.append(hasQ ? "&" : "?").append("limit=").append(limit);
            hasQ = true;
        }
        if (StringUtils.hasText(query)) {
            url.append(hasQ ? "&" : "?").append("query=").append(query);
        }
        return restTemplate.exchange(url.toString(), HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @GetMapping("/simplified-notify-channel-configs")
    public ResponseEntity<String> listSimplifiedNotifyChannelConfigs() {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/simplified-notify-channel-configs";
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
    }

    @PostMapping("/notify-rules")
    public ResponseEntity<String> createNotifyRule(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-rules";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @PutMapping("/notify-rule/{id}")
        public ResponseEntity<String> updateNotifyRule(@PathVariable("id") Long id, @RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-rule/" + id;
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    @DeleteMapping("/notify-rules")
    public ResponseEntity<String> deleteNotifyRules(@RequestBody String body) {
        String url = n9eConfig.getBaseUrl() + "/api/n9e/notify-rules";
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(body, buildHeaders()), String.class);
    }

    // ==================== 初始化默认业务组 ====================

    @PostConstruct
    public void initDefaultBusiGroup() {
        new Thread(() -> {
            try {
                Thread.sleep(3000); // 等待应用启动
                String token = getToken();
                if (token == null) return;
                // 查询业务组列表
                String url = n9eConfig.getBaseUrl() + "/api/n9e/busi-groups?limit=20&query=";
                ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeaders()), String.class);
                JsonNode root = objectMapper.readTree(resp.getBody());
                JsonNode list = root.path("dat").path("list");
                if (list.isArray() && list.size() > 0) {
                    long id = list.get(0).path("id").asLong();
                    n9eConfig.setDefaultBusiGroup(id);
                    log.info("N9e 默认业务组ID自动设置为: {}", id);
                } else {
                    // 自动创建默认业务组
                    String createUrl = n9eConfig.getBaseUrl() + "/api/n9e/busi-groups";
                    ObjectNode body = objectMapper.createObjectNode();
                    body.put("name", "默认业务组");
                    body.put("user_ids", objectMapper.createArrayNode());
                    restTemplate.exchange(createUrl, HttpMethod.POST,
                        new HttpEntity<>(objectMapper.writeValueAsString(body), buildHeaders()), String.class);
                    log.info("N9e 默认业务组已自动创建");
                    // 再查一次
                    initDefaultBusiGroup();
                }
            } catch (Exception e) {
                log.warn("N9e 初始化业务组失败（N9e可能未启动）: {}", e.getMessage());
            }
        }).start();
    }
}