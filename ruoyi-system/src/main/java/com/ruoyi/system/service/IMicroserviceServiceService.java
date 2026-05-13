package com.ruoyi.system.service;

import java.util.List;

/**
 * 微服务 Service 配置 Service 接口
 */
public interface IMicroserviceServiceService
{
    /**
     * 查询指定命名空间的微服务 Service 列表
     */
    List<String> listServices(String namespace);

    /**
     * 保存指定命名空间的微服务 Service 列表（全量替换）
     */
    void saveServices(String namespace, List<String> services);
}