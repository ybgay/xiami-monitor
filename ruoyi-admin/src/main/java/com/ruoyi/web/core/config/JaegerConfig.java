package com.ruoyi.web.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jaeger")
public class JaegerConfig
{
    /**
     * Jaeger Query API 基础地址（示例：http://192.168.31.34:32686）
     */
    private String baseUrl = "http://192.168.31.34:32686";

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }
}
