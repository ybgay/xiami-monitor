package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MicroserviceNamespace;

/**
 * 微服务监控-命名空间配置 Mapper 接口
 *
 * @author ruoyi
 */
public interface MicroserviceNamespaceMapper
{
    /**
     * 查询所有微服务命名空间（未删除）
     */
    List<String> selectAllNamespaces();

    /**
     * 根据 namespace 名称查询
     */
    MicroserviceNamespace selectByNamespace(String namespace);

    /**
     * 新增
     */
    int insertNamespace(MicroserviceNamespace ns);

    /**
     * 逻辑删除全部（保存前先全部软删除）
     */
    int deleteAll(String updateBy);

    /**
     * 恢复（将已删除的记录重新标记为正常）
     */
    int restoreNamespace(MicroserviceNamespace ns);
}
