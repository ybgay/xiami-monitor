package com.ruoyi.device.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.device.domain.DeviceManager;
import com.ruoyi.device.domain.PhysicalMaster;
import com.ruoyi.device.utils.PasswordEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.MiddlewareMasterMapper;
import com.ruoyi.device.domain.MiddlewareMaster;
import com.ruoyi.device.service.IMiddlewareMasterService;

/**
 * 中间件管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
@Service
public class MiddlewareMasterServiceImpl implements IMiddlewareMasterService 
{
    @Autowired
    private MiddlewareMasterMapper middlewareMasterMapper;

    /**
     * 查询中间件管理
     * 
     * @param middlewareId 中间件管理主键
     * @return 中间件管理
     */
    @Override
    public MiddlewareMaster selectMiddlewareMasterByMiddlewareId(Long middlewareId)
    {
        return middlewareMasterMapper.selectMiddlewareMasterByMiddlewareId(middlewareId);
    }

    /**
     * 查询中间件管理列表
     * 
     * @param middlewareMaster 中间件管理
     * @return 中间件管理
     */
    @Override
    public List<MiddlewareMaster> selectMiddlewareMasterList(MiddlewareMaster middlewareMaster)
    {
        return middlewareMasterMapper.selectMiddlewareMasterList(middlewareMaster);
    }

    /**
     * 新增中间件管理
     * 
     * @param middlewareMaster 中间件管理
     * @return 结果
     */
    @Override
    public int insertMiddlewareMaster(MiddlewareMaster middlewareMaster)
    {
        middlewareMaster.setCreateTime(DateUtils.getNowDate());
        String password = middlewareMaster.getPassword();
        String s = PasswordEncryptUtil.encryptPassword(password);
        middlewareMaster.setPassword(s);
        return middlewareMasterMapper.insertMiddlewareMaster(middlewareMaster);
    }

    /**
     * 修改中间件管理
     * 
     * @param middlewareMaster 中间件管理
     * @return 结果
     */
    @Override
    public int updateMiddlewareMaster(MiddlewareMaster middlewareMaster)
    {
        String password = middlewareMaster.getPassword();
        String s = PasswordEncryptUtil.encryptPassword(password);
        middlewareMaster.setPassword(s);
        return middlewareMasterMapper.updateMiddlewareMaster(middlewareMaster);
    }

    /**
     * 批量删除中间件管理
     * 
     * @param middlewareIds 需要删除的中间件管理主键
     * @return 结果
     */
    @Override
    public int deleteMiddlewareMasterByMiddlewareIds(Long[] middlewareIds)
    {
        return middlewareMasterMapper.deleteMiddlewareMasterByMiddlewareIds(middlewareIds);
    }

    /**
     * 删除中间件管理信息
     * 
     * @param middlewareId 中间件管理主键
     * @return 结果
     */
    @Override
    public int deleteMiddlewareMasterByMiddlewareId(Long middlewareId)
    {
        return middlewareMasterMapper.deleteMiddlewareMasterByMiddlewareId(middlewareId);
    }

    @Override
        public List<MiddlewareMaster> selectMiddwareMasterByDeviceId(int deviceId) {
        List<MiddlewareMaster> middlewareMasters = middlewareMasterMapper.selectMiddwareMasterByDeviceId(deviceId);
//        for (PhysicalMaster physicalMaster : physicalMasters) {
//            System.out.println(physicalMaster);
//        }
        return middlewareMasters;
    }

}
