package com.cloudease.basis.service.impl;

import java.util.List;
import com.cloudease.common.utils.DateUtils;
import com.cloudease.common.utils.SnowFlake.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudease.basis.mapper.LandlordInfoMapper;
import com.cloudease.basis.domain.LandlordInfo;
import com.cloudease.basis.service.ILandlordInfoService;

/**
 * 业主信息Service业务层处理
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
@Service
public class LandlordInfoServiceImpl implements ILandlordInfoService 
{
    @Autowired
    private LandlordInfoMapper landlordInfoMapper;

    /**
     * 查询业主信息
     * 
     * @param id 业主信息主键
     * @return 业主信息
     */
    @Override
    public LandlordInfo selectLandlordInfoById(Long id)
    {
        return landlordInfoMapper.selectLandlordInfoById(id);
    }

    /**
     * 查询业主信息列表
     * 
     * @param landlordInfo 业主信息
     * @return 业主信息
     */
    @Override
    public List<LandlordInfo> selectLandlordInfoList(LandlordInfo landlordInfo)
    {
        return landlordInfoMapper.selectLandlordInfoList(landlordInfo);
    }

    /**
     * 新增业主信息
     * 
     * @param landlordInfo 业主信息
     * @return 结果
     */
    @Override
    public int insertLandlordInfo(LandlordInfo landlordInfo)
    {
        landlordInfo.setCreateTime(DateUtils.getNowDate());
        landlordInfo.setLandlordCode(IdWorker.getNextCode());
        return landlordInfoMapper.insertLandlordInfo(landlordInfo);
    }

    /**
     * 修改业主信息
     * 
     * @param landlordInfo 业主信息
     * @return 结果
     */
    @Override
    public int updateLandlordInfo(LandlordInfo landlordInfo)
    {
        landlordInfo.setUpdateTime(DateUtils.getNowDate());
        return landlordInfoMapper.updateLandlordInfo(landlordInfo);
    }

    /**
     * 批量删除业主信息
     * 
     * @param ids 需要删除的业主信息主键
     * @return 结果
     */
    @Override
    public int deleteLandlordInfoByIds(Long[] ids)
    {
        return landlordInfoMapper.deleteLandlordInfoByIds(ids);
    }

    /**
     * 删除业主信息信息
     * 
     * @param id 业主信息主键
     * @return 结果
     */
    @Override
    public int deleteLandlordInfoById(Long id)
    {
        return landlordInfoMapper.deleteLandlordInfoById(id);
    }
}
