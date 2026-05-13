package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.MiddlewareMaster;
import com.ruoyi.device.domain.PhysicalMaster;

/**
 * 中间件管理Service接口
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
public interface IMiddlewareMasterService 
{

    public List<MiddlewareMaster> selectMiddwareMasterByDeviceId(int deviceId);
    /**
     * 查询中间件管理
     * 
     * @param middlewareId 中间件管理主键
     * @return 中间件管理
     */
    public MiddlewareMaster selectMiddlewareMasterByMiddlewareId(Long middlewareId);

    /**
     * 查询中间件管理列表
     * 
     * @param middlewareMaster 中间件管理
     * @return 中间件管理集合
     */
    public List<MiddlewareMaster> selectMiddlewareMasterList(MiddlewareMaster middlewareMaster);

    /**
     * 新增中间件管理
     * 
     * @param middlewareMaster 中间件管理
     * @return 结果
     */
    public int insertMiddlewareMaster(MiddlewareMaster middlewareMaster);

    /**
     * 修改中间件管理
     * 
     * @param middlewareMaster 中间件管理
     * @return 结果
     */
    public int updateMiddlewareMaster(MiddlewareMaster middlewareMaster);

    /**
     * 批量删除中间件管理
     * 
     * @param middlewareIds 需要删除的中间件管理主键集合
     * @return 结果
     */
    public int deleteMiddlewareMasterByMiddlewareIds(Long[] middlewareIds);

    /**
     * 删除中间件管理信息
     * 
     * @param middlewareId 中间件管理主键
     * @return 结果
     */
    public int deleteMiddlewareMasterByMiddlewareId(Long middlewareId);
}
