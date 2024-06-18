package com.cloudease.basis.service;

import java.util.List;
import com.cloudease.basis.domain.LandlordInfo;

/**
 * 业主信息Service接口
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
public interface ILandlordInfoService 
{
    /**
     * 查询业主信息
     * 
     * @param id 业主信息主键
     * @return 业主信息
     */
    public LandlordInfo selectLandlordInfoById(Long id);

    /**
     * 查询业主信息列表
     * 
     * @param landlordInfo 业主信息
     * @return 业主信息集合
     */
    public List<LandlordInfo> selectLandlordInfoList(LandlordInfo landlordInfo);

    /**
     * 新增业主信息
     * 
     * @param landlordInfo 业主信息
     * @return 结果
     */
    public int insertLandlordInfo(LandlordInfo landlordInfo);

    /**
     * 修改业主信息
     * 
     * @param landlordInfo 业主信息
     * @return 结果
     */
    public int updateLandlordInfo(LandlordInfo landlordInfo);

    /**
     * 批量删除业主信息
     * 
     * @param ids 需要删除的业主信息主键集合
     * @return 结果
     */
    public int deleteLandlordInfoByIds(Long[] ids);

    /**
     * 删除业主信息信息
     * 
     * @param id 业主信息主键
     * @return 结果
     */
    public int deleteLandlordInfoById(Long id);
}
