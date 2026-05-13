package com.ruoyi.web.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "n9e")
public class N9eConfig {
    private String baseUrl;
    private String username;
    private String password;
    private Long defaultBusiGroup = 1L;

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getDefaultBusiGroup() { return defaultBusiGroup; }
    public void setDefaultBusiGroup(Long defaultBusiGroup) { this.defaultBusiGroup = defaultBusiGroup; }
}