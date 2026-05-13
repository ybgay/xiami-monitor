package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.device.utils.PasswordEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.PhysicalMasterMapper;
import com.ruoyi.device.domain.PhysicalMaster;
import com.ruoyi.device.service.IPhysicalMasterService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-11
 */
@Service
public class PhysicalMasterServiceImpl implements IPhysicalMasterService 
{
    @Autowired
    private PhysicalMasterMapper physicalMasterMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param physicalId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public PhysicalMaster selectPhysicalMasterByPhysicalId(Long physicalId)
    {
        return physicalMasterMapper.selectPhysicalMasterByPhysicalId(physicalId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param physicalMaster 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PhysicalMaster> selectPhysicalMasterList(PhysicalMaster physicalMaster)
    {
        return physicalMasterMapper.selectPhysicalMasterList(physicalMaster);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param physicalMaster 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPhysicalMaster(PhysicalMaster physicalMaster)
    {
        String s = PasswordEncryptUtil.encryptPassword(physicalMaster.getPassword());
        physicalMaster.setPassword(s);
        physicalMaster.setCreateTime(DateUtils.getNowDate());
        return physicalMasterMapper.insertPhysicalMaster(physicalMaster);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param physicalMaster 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePhysicalMaster(PhysicalMaster physicalMaster)
    {
        String s = PasswordEncryptUtil.encryptPassword(physicalMaster.getPassword());
        physicalMaster.setPassword(s);
        return physicalMasterMapper.updatePhysicalMaster(physicalMaster);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param physicalIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePhysicalMasterByPhysicalIds(Long[] physicalIds)
    {
        return physicalMasterMapper.deletePhysicalMasterByPhysicalIds(physicalIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param physicalId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePhysicalMasterByPhysicalId(Long physicalId)
    {
        return physicalMasterMapper.deletePhysicalMasterByPhysicalId(physicalId);
    }

     @Override
    public List<PhysicalMaster> selectPhysicalMasterByDeviceId(int deviceId) {
        List<PhysicalMaster> physicalMasters = physicalMasterMapper.selectPhysicalMasterByDeviceId(deviceId);
//        for (PhysicalMaster physicalMaster : physicalMasters) {
//            System.out.println(physicalMaster);
//        }
        return physicalMasters;
    }

}
