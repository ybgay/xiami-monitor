package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.DatabaseMasterMapper;
import com.ruoyi.device.domain.DatabaseMaster;
import com.ruoyi.device.service.IDatabaseMasterService;

/**
 * 数据库管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-13
 */
@Service
public class DatabaseMasterServiceImpl implements IDatabaseMasterService 
{
    @Autowired
    private DatabaseMasterMapper databaseMasterMapper;

    /**
     * 查询数据库管理
     * 
     * @param databaseId 数据库管理主键
     * @return 数据库管理
     */
    @Override
    public DatabaseMaster selectDatabaseMasterByDatabaseId(Long databaseId)
    {
        return databaseMasterMapper.selectDatabaseMasterByDatabaseId(databaseId);
    }

    /**
     * 查询数据库管理列表
     * 
     * @param databaseMaster 数据库管理
     * @return 数据库管理
     */
    @Override
    public List<DatabaseMaster> selectDatabaseMasterList(DatabaseMaster databaseMaster)
    {
        return databaseMasterMapper.selectDatabaseMasterList(databaseMaster);
    }

    /**
     * 新增数据库管理
     * 
     * @param databaseMaster 数据库管理
     * @return 结果
     */
    @Override
    public int insertDatabaseMaster(DatabaseMaster databaseMaster)
    {
        databaseMaster.setCreateTime(DateUtils.getNowDate());
        return databaseMasterMapper.insertDatabaseMaster(databaseMaster);
    }

    /**
     * 修改数据库管理
     * 
     * @param databaseMaster 数据库管理
     * @return 结果
     */
    @Override
    public int updateDatabaseMaster(DatabaseMaster databaseMaster)
    {
        return databaseMasterMapper.updateDatabaseMaster(databaseMaster);
    }

    /**
     * 批量删除数据库管理
     * 
     * @param databaseIds 需要删除的数据库管理主键
     * @return 结果
     */
    @Override
    public int deleteDatabaseMasterByDatabaseIds(Long[] databaseIds)
    {
        return databaseMasterMapper.deleteDatabaseMasterByDatabaseIds(databaseIds);
    }

    /**
     * 删除数据库管理信息
     * 
     * @param databaseId 数据库管理主键
     * @return 结果
     */
    @Override
    public int deleteDatabaseMasterByDatabaseId(Long databaseId)
    {
        return databaseMasterMapper.deleteDatabaseMasterByDatabaseId(databaseId);
    }
}
