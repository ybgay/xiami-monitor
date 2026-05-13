package com.ruoyi.system.domain.vo;

public class K8sDeploymentVO {
    private String name;
    private String namespace;
    private Integer replicas;        // 期望副本数
    private Integer readyReplicas;   // 就绪副本数
    private Integer availableReplicas; // 可用副本数
    private String creationTimestamp;

    // --- Getter 和 Setter ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNamespace() { return namespace; }
    public void setNamespace(String namespace) { this.namespace = namespace; }
    public Integer getReplicas() { return replicas; }
    public void setReplicas(Integer replicas) { this.replicas = replicas; }
    public Integer getReadyReplicas() { return readyReplicas; }
    public void setReadyReplicas(Integer readyReplicas) { this.readyReplicas = readyReplicas; }
    public Integer getAvailableReplicas() { return availableReplicas; }
    public void setAvailableReplicas(Integer availableReplicas) { this.availableReplicas = availableReplicas; }
    public String getCreationTimestamp() { return creationTimestamp; }
    public void setCreationTimestamp(String creationTimestamp) { this.creationTimestamp = creationTimestamp; }
}