package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.VirtualMaster;

/**
 * 虚拟机管理Service接口
 * 
 * @author zly
 * @date 2026-03-17
 */
public interface IVirtualMasterService 
{
    /**
     * 查询虚拟机管理
     * 
     * @param virtualId 虚拟机管理主键
     * @return 虚拟机管理
     */
    public VirtualMaster selectVirtualMasterByVirtualId(Long virtualId);

    /**
     * 查询虚拟机管理列表
     * 
     * @param virtualMaster 虚拟机管理
     * @return 虚拟机管理集合
     */
    public List<VirtualMaster> selectVirtualMasterList(VirtualMaster virtualMaster);

    /**
     * 新增虚拟机管理
     * 
     * @param virtualMaster 虚拟机管理
     * @return 结果
     */
    public int insertVirtualMaster(VirtualMaster virtualMaster);

    /**
     * 修改虚拟机管理
     * 
     * @param virtualMaster 虚拟机管理
     * @return 结果
     */
    public int updateVirtualMaster(VirtualMaster virtualMaster);

    /**
     * 批量删除虚拟机管理
     * 
     * @param virtualIds 需要删除的虚拟机管理主键集合
     * @return 结果
     */
    public int deleteVirtualMasterByVirtualIds(Long[] virtualIds);

    /**
     * 删除虚拟机管理信息
     * 
     * @param virtualId 虚拟机管理主键
     * @return 结果
     */
    public int deleteVirtualMasterByVirtualId(Long virtualId);
}
