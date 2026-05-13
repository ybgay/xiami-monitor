package com.ruoyi.monitor.n9e.service;

import java.util.Map;

/**
 * N9e监控服务接口
 * 
 * @author ruoyi
 */
public interface IN9eService {
    
    /**
     * 查询活跃告警列表
     * 
     * @param params 查询参数
     * @return 活跃告警列表
     */
    Object listActiveAlerts(Map<String, Object> params);
    
    /**
     * 查询告警规则列表
     * 
     * @param params 查询参数
     * @return 告警规则列表
     */
    Object listAlertRules(Map<String, Object> params);
    
    /**
     * 查询历史告警列表
     * 
     * @param params 查询参数
     * @return 历史告警列表
     */
    Object listHistoryAlerts(Map<String, Object> params);
    
    /**
     * 查询监控设备列表
     * 
     * @param params 查询参数
     * @return 监控设备列表
     */
    Object listMonitorDevices(Map<String, Object> params);
}