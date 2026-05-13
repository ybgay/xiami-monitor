package com.ruoyi.system.service;

import java.util.List;

/**
 * 微服务监控-命名空间配置 Service 接口
 *
 * @author ruoyi
 */
public interface IMicroserviceNamespaceService
{
    /**
     * 查询所有微服务命名空间列表
     *
     * @return namespace 名称列表
     */
    List<String> listNamespaces();

    /**
     * 保存微服务命名空间列表（全量替换）
     * 先软删除所有旧数据，再逐个插入/恢复新数据
     *
     * @param namespaces namespace 名称列表
     */
    void saveNamespaces(List<String> namespaces);
}
