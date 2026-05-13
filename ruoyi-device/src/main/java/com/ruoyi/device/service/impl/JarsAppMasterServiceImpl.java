package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.JarsAppMasterMapper;
import com.ruoyi.device.domain.JarsAppMaster;
import com.ruoyi.device.service.IJarsAppMasterService;

/**
 * JAR应用管理 Service业务层处理
 *
 * @author ruoyi
 * @date 2026-03-17
 */
@Service
public class JarsAppMasterServiceImpl implements IJarsAppMasterService
{
    @Autowired
    private JarsAppMasterMapper jarsAppMasterMapper;

    @Override
    public JarsAppMaster selectJarsAppMasterByJarsId(Long jarsId)
    {
        return jarsAppMasterMapper.selectJarsAppMasterByJarsId(jarsId);
    }

    @Override
    public List<JarsAppMaster> selectJarsAppMasterList(JarsAppMaster jarsAppMaster)
    {
        return jarsAppMasterMapper.selectJarsAppMasterList(jarsAppMaster);
    }

    @Override
    public int insertJarsAppMaster(JarsAppMaster jarsAppMaster)
    {
        jarsAppMaster.setCreateTime(DateUtils.getNowDate());
        return jarsAppMasterMapper.insertJarsAppMaster(jarsAppMaster);
    }

    @Override
    public int updateJarsAppMaster(JarsAppMaster jarsAppMaster)
    {
        jarsAppMaster.setUpdateTime(DateUtils.getNowDate());
        return jarsAppMasterMapper.updateJarsAppMaster(jarsAppMaster);
    }

    @Override
    public int deleteJarsAppMasterByJarsIds(Long[] jarsIds)
    {
        return jarsAppMasterMapper.deleteJarsAppMasterByJarsIds(jarsIds);
    }

    @Override
    public int deleteJarsAppMasterByJarsId(Long jarsId)
    {
        return jarsAppMasterMapper.deleteJarsAppMasterByJarsId(jarsId);
    }
}