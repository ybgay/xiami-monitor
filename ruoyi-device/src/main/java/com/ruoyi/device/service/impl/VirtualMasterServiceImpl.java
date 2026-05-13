package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.VirtualMasterMapper;
import com.ruoyi.device.domain.VirtualMaster;
import com.ruoyi.device.service.IVirtualMasterService;

/**
 * 虚拟机管理Service业务层处理
 * 
 * @author zly
 * @date 2026-03-17
 */
@Service
public class VirtualMasterServiceImpl implements IVirtualMasterService 
{
    @Autowired
    private VirtualMasterMapper virtualMasterMapper;

    /**
     * 查询虚拟机管理
     * 
     * @param virtualId 虚拟机管理主键
     * @return 虚拟机管理
     */
    @Override
    public VirtualMaster selectVirtualMasterByVirtualId(Long virtualId)
    {
        return virtualMasterMapper.selectVirtualMasterByVirtualId(virtualId);
    }

    /**
     * 查询虚拟机管理列表
     * 
     * @param virtualMaster 虚拟机管理
     * @return 虚拟机管理
     */
    @Override
    public List<VirtualMaster> selectVirtualMasterList(VirtualMaster virtualMaster)
    {
        return virtualMasterMapper.selectVirtualMasterList(virtualMaster);
    }

    /**
     * 新增虚拟机管理
     * 
     * @param virtualMaster 虚拟机管理
     * @return 结果
     */
    @Override
    public int insertVirtualMaster(VirtualMaster virtualMaster)
    {
        virtualMaster.setCreateTime(DateUtils.getNowDate());
        return virtualMasterMapper.insertVirtualMaster(virtualMaster);
    }

    /**
     * 修改虚拟机管理
     * 
     * @param virtualMaster 虚拟机管理
     * @return 结果
     */
    @Override
    public int updateVirtualMaster(VirtualMaster virtualMaster)
    {
        return virtualMasterMapper.updateVirtualMaster(virtualMaster);
    }

    /**
     * 批量删除虚拟机管理
     * 
     * @param virtualIds 需要删除的虚拟机管理主键
     * @return 结果
     */
    @Override
    public int deleteVirtualMasterByVirtualIds(Long[] virtualIds)
    {
        return virtualMasterMapper.deleteVirtualMasterByVirtualIds(virtualIds);
    }

    /**
     * 删除虚拟机管理信息
     * 
     * @param virtualId 虚拟机管理主键
     * @return 结果
     */
    @Override
    public int deleteVirtualMasterByVirtualId(Long virtualId)
    {
        return virtualMasterMapper.deleteVirtualMasterByVirtualId(virtualId);
    }
}
