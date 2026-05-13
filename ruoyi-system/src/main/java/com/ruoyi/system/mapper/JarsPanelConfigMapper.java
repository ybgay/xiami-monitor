package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.JarsPanelConfig;

/**
 * Jars应用监控面板配置 Mapper 接口
 *
 * @author ruoyi
 */
public interface JarsPanelConfigMapper
{
    /**
     * 查询面板配置列表（按 device + category + appName 过滤）
     */
    List<JarsPanelConfig> selectPanelConfigList(JarsPanelConfig config);

    /**
     * 根据主键查询面板配置
     */
    JarsPanelConfig selectPanelConfigById(Long id);

    /**
     * 根据 device + category + appName + panelKey 查询单条（upsert 用）
     */
    JarsPanelConfig selectPanelConfigByKey(JarsPanelConfig config);

    /**
     * 新增面板配置
     */
    int insertPanelConfig(JarsPanelConfig config);

    /**
     * 修改面板配置
     */
    int updatePanelConfig(JarsPanelConfig config);

    /**
     * 删除面板配置（逻辑删除）
     */
    int deletePanelConfigById(Long id);

    /**
     * 批量删除面板配置（逻辑删除）
     */
    int deletePanelConfigByIds(Long[] ids);

    /**
     * 批量更新面板排序和宽度（仅更新 sort_order / group_sort_order / panel_span）
     */
    int batchUpdateSort(List<JarsPanelConfig> list);
}
