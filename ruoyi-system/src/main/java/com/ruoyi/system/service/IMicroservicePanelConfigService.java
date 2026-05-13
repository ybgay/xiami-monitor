package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MicroservicePanelConfig;

/**
 * 微服务监控面板配置 Service 接口
 *
 * @author ruoyi
 */
public interface IMicroservicePanelConfigService
{
    /**
     * 查询面板配置列表
     */
    List<MicroservicePanelConfig> selectPanelConfigList(MicroservicePanelConfig config);

    /**
     * 根据主键查询
     */
    MicroservicePanelConfig selectPanelConfigById(Long id);

    /**
     * 保存面板配置（存在则更新，不存在则新增）
     */
    int savePanelConfig(MicroservicePanelConfig config);

    /**
     * 新增面板配置
     */
    int insertPanelConfig(MicroservicePanelConfig config);

    /**
     * 修改面板配置
     */
    int updatePanelConfig(MicroservicePanelConfig config);

    /**
     * 删除面板配置
     */
    int deletePanelConfigById(Long id);

    /**
     * 批量删除面板配置
     */
    int deletePanelConfigByIds(Long[] ids);
}
