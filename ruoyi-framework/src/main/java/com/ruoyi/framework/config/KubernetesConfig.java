package com.ruoyi.framework.config;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class KubernetesConfig {
    
    private static final Logger log = LoggerFactory.getLogger(KubernetesConfig.class);
    private KubernetesClient client;

    @Bean
    public KubernetesClient kubernetesClient() {
        log.info("初始化 Kubernetes Client...");
        // 自动发现配置：如果在 K8s 内部运行，读取 ServiceAccount；在外部运行，默认读取 ~/.kube/config
        client = new KubernetesClientBuilder().build();
        return client;
    }

    @PreDestroy
    public void closeClient() {
        if (client != null) {
            log.info("关闭 Kubernetes Client...");
            client.close();
        }
    }
}