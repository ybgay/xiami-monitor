package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.MicroserviceService;
import com.ruoyi.system.mapper.MicroserviceServiceMapper;
import com.ruoyi.system.service.IMicroserviceServiceService;

/**
 * 微服务 Service 配置 Service 实现
 *
 * @author ruoyi
 */
@Service
public class MicroserviceServiceServiceImpl implements IMicroserviceServiceService
{
    @Autowired
    private MicroserviceServiceMapper serviceMapper;

    @Override
    public List<String> listServices(String namespace)
    {
        return serviceMapper.selectServicesByNamespace(namespace);
    }

    /**
     * 全量替换：先软删除所有旧记录，再逐个处理新列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveServices(String namespace, List<String> services)
    {
        String currentUser = SecurityUtils.getUsername();

        // 1. 软删除该命名空间的所有现有记录
        serviceMapper.deleteByNamespace(namespace, currentUser);

        if (services == null || services.isEmpty())
        {
            return;
        }

        // 2. 逐个处理新列表
        for (int i = 0; i < services.size(); i++)
        {
            String svcName = services.get(i);
            if (svcName == null || svcName.trim().isEmpty())
            {
                continue;
            }
            svcName = svcName.trim();

            // 查询是否已存在（包含已删除的记录）
            MicroserviceService existing = serviceMapper.selectByNamespaceAndService(namespace, svcName);
            if (existing != null)
            {
                // 已存在则恢复
                existing.setSortOrder(i);
                existing.setUpdateBy(currentUser);
                serviceMapper.restoreService(existing);
            }
            else
            {
                // 不存在则新增
                MicroserviceService entity = new MicroserviceService();
                entity.setNamespace(namespace);
                entity.setServiceName(svcName);
                entity.setSortOrder(i);
                entity.setCreateBy(currentUser);
                serviceMapper.insertService(entity);
            }
        }
    }
}