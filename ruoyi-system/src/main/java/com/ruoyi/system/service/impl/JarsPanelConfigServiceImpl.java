package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.JarsPanelConfig;
import com.ruoyi.system.mapper.JarsPanelConfigMapper;
import com.ruoyi.system.service.IJarsPanelConfigService;

/**
 * Jars应用监控面板配置 Service 实现
 *
 * @author ruoyi
 */
@Service
public class JarsPanelConfigServiceImpl implements IJarsPanelConfigService
{
    @Autowired
    private JarsPanelConfigMapper panelConfigMapper;

    @Override
    public List<JarsPanelConfig> selectPanelConfigList(JarsPanelConfig config)
    {
        return panelConfigMapper.selectPanelConfigList(config);
    }

    @Override
    public JarsPanelConfig selectPanelConfigById(Long id)
    {
        return panelConfigMapper.selectPanelConfigById(id);
    }

    /**
     * 保存（upsert）：根据 device+category+appName+panelKey 判断是否已存在
     * 存在则更新，不存在则新增
     */
    @Override
    public int savePanelConfig(JarsPanelConfig config)
    {
        if (config.getId() != null)
        {
            return updatePanelConfig(config);
        }
        JarsPanelConfig existing = panelConfigMapper.selectPanelConfigByKey(config);
        if (existing != null)
        {
            config.setId(existing.getId());
            return updatePanelConfig(config);
        }
        return insertPanelConfig(config);
    }

    @Override
    public int insertPanelConfig(JarsPanelConfig config)
    {
        config.setCreateBy(SecurityUtils.getUsername());
        return panelConfigMapper.insertPanelConfig(config);
    }

    @Override
    public int updatePanelConfig(JarsPanelConfig config)
    {
        config.setUpdateBy(SecurityUtils.getUsername());
        return panelConfigMapper.updatePanelConfig(config);
    }

    @Override
    public int deletePanelConfigById(Long id)
    {
        return panelConfigMapper.deletePanelConfigById(id);
    }

    @Override
    public int deletePanelConfigByIds(Long[] ids)
    {
        return panelConfigMapper.deletePanelConfigByIds(ids);
    }

    @Override
    public int batchUpdateSort(List<JarsPanelConfig> list)
    {
        if (list == null || list.isEmpty()) return 0;
        return panelConfigMapper.batchUpdateSort(list);
    }
}
