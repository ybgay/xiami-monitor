package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.MicroservicePanelConfig;
import com.ruoyi.system.mapper.MicroservicePanelConfigMapper;
import com.ruoyi.system.service.IMicroservicePanelConfigService;

/**
 * 微服务监控面板配置 Service 实现
 *
 * @author ruoyi
 */
@Service
public class MicroservicePanelConfigServiceImpl implements IMicroservicePanelConfigService
{
    @Autowired
    private MicroservicePanelConfigMapper panelConfigMapper;

    @Override
    public List<MicroservicePanelConfig> selectPanelConfigList(MicroservicePanelConfig config)
    {
        return panelConfigMapper.selectPanelConfigList(config);
    }

    @Override
    public MicroservicePanelConfig selectPanelConfigById(Long id)
    {
        return panelConfigMapper.selectPanelConfigById(id);
    }

    /**
     * 保存（upsert）：根据 namespace+service+pod+panelKey 判断是否已存在
     * 存在则更新，不存在则新增
     */
    @Override
    public int savePanelConfig(MicroservicePanelConfig config)
    {
        MicroservicePanelConfig existing = panelConfigMapper.selectPanelConfigByKey(config);
        if (existing != null)
        {
            config.setId(existing.getId());
            return updatePanelConfig(config);
        }
        return insertPanelConfig(config);
    }

    @Override
    public int insertPanelConfig(MicroservicePanelConfig config)
    {
        config.setCreateBy(SecurityUtils.getUsername());
        return panelConfigMapper.insertPanelConfig(config);
    }

    @Override
    public int updatePanelConfig(MicroservicePanelConfig config)
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
}
