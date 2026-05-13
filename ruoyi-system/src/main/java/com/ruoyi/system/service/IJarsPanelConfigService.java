package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.JarsPanelConfig;

/**
 * Jars应用监控面板配置 Service 接口
 *
 * @author ruoyi
 */
public interface IJarsPanelConfigService
{
    /**
     * 查询面板配置列表
     */
    List<JarsPanelConfig> selectPanelConfigList(JarsPanelConfig config);

    /**
     * 根据主键查询
     */
    JarsPanelConfig selectPanelConfigById(Long id);

    /**
     * 保存面板配置（存在则更新，不存在则新增）
     */
    int savePanelConfig(JarsPanelConfig config);

    /**
     * 新增面板配置
     */
    int insertPanelConfig(JarsPanelConfig config);

    /**
     * 修改面板配置
     */
    int updatePanelConfig(JarsPanelConfig config);

    /**
     * 删除面板配置
     */
    int deletePanelConfigById(Long id);

    /**
     * 批量删除面板配置
     */
    int deletePanelConfigByIds(Long[] ids);

    /**
     * 批量更新面板排序和宽度（sort_order / group_sort_order / panel_span）
     */
    int batchUpdateSort(List<JarsPanelConfig> list);
}
