package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.MicroserviceNamespace;
import com.ruoyi.system.mapper.MicroserviceNamespaceMapper;
import com.ruoyi.system.service.IMicroserviceNamespaceService;

/**
 * 微服务监控-命名空间配置 Service 实现
 *
 * @author ruoyi
 */
@Service
public class MicroserviceNamespaceServiceImpl implements IMicroserviceNamespaceService
{
    @Autowired
    private MicroserviceNamespaceMapper namespaceMapper;

    @Override
    public List<String> listNamespaces()
    {
        return namespaceMapper.selectAllNamespaces();
    }

    /**
     * 全量替换：先软删除所有旧记录，再逐个处理新列表
     * - 若数据库已存在该 namespace（含已删除）：restore（恢复 del_flag=0）
     * - 若不存在：insert
     * 这样可以保留历史记录的 id 和 create_time，避免频繁物理删除。
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveNamespaces(List<String> namespaces)
    {
        String currentUser = SecurityUtils.getUsername();

        // 1. 软删除所有现有记录
        namespaceMapper.deleteAll(currentUser);

        if (namespaces == null || namespaces.isEmpty())
        {
            return;
        }

        // 2. 逐个处理新列表
        for (int i = 0; i < namespaces.size(); i++)
        {
            String nsName = namespaces.get(i);
            if (nsName == null || nsName.trim().isEmpty())
            {
                continue;
            }
            nsName = nsName.trim();

            // 查询是否已存在（包含已删除的记录）
            MicroserviceNamespace existing = namespaceMapper.selectByNamespace(nsName);
            if (existing != null)
            {
                // 已存在则恢复
                existing.setSortOrder(i);
                existing.setUpdateBy(currentUser);
                namespaceMapper.restoreNamespace(existing);
            }
            else
            {
                // 不存在则新增
                MicroserviceNamespace entity = new MicroserviceNamespace();
                entity.setNamespace(nsName);
                entity.setSortOrder(i);
                entity.setCreateBy(currentUser);
                namespaceMapper.insertNamespace(entity);
            }
        }
    }
}
