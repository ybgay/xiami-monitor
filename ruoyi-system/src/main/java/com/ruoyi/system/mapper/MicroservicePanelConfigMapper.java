package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MicroservicePanelConfig;

/**
 * 微服务监控面板配置 Mapper 接口
 *
 * @author ruoyi
 */
public interface MicroservicePanelConfigMapper
{
    /**
     * 查询面板配置列表（按 namespace + service + pod 过滤）
     */
    List<MicroservicePanelConfig> selectPanelConfigList(MicroservicePanelConfig config);

    /**
     * 根据主键查询面板配置
     */
    MicroservicePanelConfig selectPanelConfigById(Long id);

    /**
     * 根据 namespace + service + pod + panelKey 查询单条
     */
    MicroservicePanelConfig selectPanelConfigByKey(MicroservicePanelConfig config);

    /**
     * 新增面板配置
     */
    int insertPanelConfig(MicroservicePanelConfig config);

    /**
     * 修改面板配置
     */
    int updatePanelConfig(MicroservicePanelConfig config);

    /**
     * 删除面板配置（逻辑删除）
     */
    int deletePanelConfigById(Long id);

    /**
     * 批量删除面板配置（逻辑删除）
     */
    int deletePanelConfigByIds(Long[] ids);
}
