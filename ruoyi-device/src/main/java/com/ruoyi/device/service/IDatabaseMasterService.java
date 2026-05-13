package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.DatabaseMaster;

/**
 * 数据库管理Service接口
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
public interface IDatabaseMasterService 
{
    /**
     * 查询数据库管理
     * 
     * @param databaseId 数据库管理主键
     * @return 数据库管理
     */
    public DatabaseMaster selectDatabaseMasterByDatabaseId(Long databaseId);

    /**
     * 查询数据库管理列表
     * 
     * @param databaseMaster 数据库管理
     * @return 数据库管理集合
     */
    public List<DatabaseMaster> selectDatabaseMasterList(DatabaseMaster databaseMaster);

    /**
     * 新增数据库管理
     * 
     * @param databaseMaster 数据库管理
     * @return 结果
     */
    public int insertDatabaseMaster(DatabaseMaster databaseMaster);

    /**
     * 修改数据库管理
     * 
     * @param databaseMaster 数据库管理
     * @return 结果
     */
    public int updateDatabaseMaster(DatabaseMaster databaseMaster);

    /**
     * 批量删除数据库管理
     * 
     * @param databaseIds 需要删除的数据库管理主键集合
     * @return 结果
     */
    public int deleteDatabaseMasterByDatabaseIds(Long[] databaseIds);

    /**
     * 删除数据库管理信息
     * 
     * @param databaseId 数据库管理主键
     * @return 结果
     */
    public int deleteDatabaseMasterByDatabaseId(Long databaseId);
}
