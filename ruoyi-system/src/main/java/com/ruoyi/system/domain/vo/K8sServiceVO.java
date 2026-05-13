package com.ruoyi.system.domain.vo;

public class K8sServiceVO {
    private String name;
    private String namespace;
    private String type;         // ClusterIP, NodePort, LoadBalancer 等
    private String clusterIp;
    private String externalIp;
    private String ports;        // 端口映射组合字符串
    private String creationTimestamp;

    // --- Getter 和 Setter ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNamespace() { return namespace; }
    public void setNamespace(String namespace) { this.namespace = namespace; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getClusterIp() { return clusterIp; }
    public void setClusterIp(String clusterIp) { this.clusterIp = clusterIp; }
    public String getExternalIp() { return externalIp; }
    public void setExternalIp(String externalIp) { this.externalIp = externalIp; }
    public String getPorts() { return ports; }
    public void setPorts(String ports) { this.ports = ports; }
    public String getCreationTimestamp() { return creationTimestamp; }
    public void setCreationTimestamp(String creationTimestamp) { this.creationTimestamp = creationTimestamp; }
}