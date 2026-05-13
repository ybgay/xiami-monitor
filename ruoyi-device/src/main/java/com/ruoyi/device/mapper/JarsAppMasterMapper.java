package com.ruoyi.device.mapper;

import java.util.List;
import com.ruoyi.device.domain.JarsAppMaster;

/**
 * JAR应用管理 Mapper接口
 *
 * @author ruoyi
 * @date 2026-03-17
 */
public interface JarsAppMasterMapper
{
    /**
     * 查询JAR应用
     *
     * @param jarsId JAR应用主键
     * @return JAR应用
     */
    JarsAppMaster selectJarsAppMasterByJarsId(Long jarsId);

    /**
     * 查询JAR应用列表
     *
     * @param jarsAppMaster 查询条件
     * @return JAR应用集合
     */
    List<JarsAppMaster> selectJarsAppMasterList(JarsAppMaster jarsAppMaster);

    /**
     * 新增JAR应用
     *
     * @param jarsAppMaster JAR应用信息
     * @return 结果
     */
    int insertJarsAppMaster(JarsAppMaster jarsAppMaster);

    /**
     * 修改JAR应用
     *
     * @param jarsAppMaster JAR应用信息
     * @return 结果
     */
    int updateJarsAppMaster(JarsAppMaster jarsAppMaster);

    /**
     * 删除JAR应用
     *
     * @param jarsId JAR应用主键
     * @return 结果
     */
    int deleteJarsAppMasterByJarsId(Long jarsId);

    /**
     * 批量删除JAR应用
     *
     * @param jarsIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteJarsAppMasterByJarsIds(Long[] jarsIds);
}
