package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.MicroserviceService;

/**
 * 微服务 Service 配置 Mapper
 */
public interface MicroserviceServiceMapper
{
    /**
     * 查询指定命名空间的所有 Service（未删除）
     */
    List<String> selectServicesByNamespace(String namespace);

    /**
     * 查询指定命名空间和 Service 名称的记录（包含已删除）
     */
    MicroserviceService selectByNamespaceAndService(@Param("namespace") String namespace, @Param("serviceName") String serviceName);

    /**
     * 软删除指定命名空间的所有 Service 记录
     */
    int deleteByNamespace(@Param("namespace") String namespace, @Param("updateBy") String updateBy);

    /**
     * 恢复（更新）Service 记录
     */
    int restoreService(MicroserviceService service);

    /**
     * 新增 Service 记录
     */
    int insertService(MicroserviceService service);
}