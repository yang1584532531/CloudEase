package com.cloudease.basis.service.impl;

import java.util.List;
import com.cloudease.common.utils.DateUtils;
import com.cloudease.common.utils.SnowFlake.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudease.basis.mapper.CommunityInfoMapper;
import com.cloudease.basis.domain.CommunityInfo;
import com.cloudease.basis.service.ICommunityInfoService;

/**
 * 小区信息Service业务层处理
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
@Service
public class CommunityInfoServiceImpl implements ICommunityInfoService 
{
    @Autowired
    private CommunityInfoMapper communityInfoMapper;

    /**
     * 查询小区信息
     * 
     * @param id 小区信息主键
     * @return 小区信息
     */
    @Override
    public CommunityInfo selectCommunityInfoById(Long id)
    {
        return communityInfoMapper.selectCommunityInfoById(id);
    }

    /**
     * 查询小区信息列表
     * 
     * @param communityInfo 小区信息
     * @return 小区信息
     */
    @Override
    public List<CommunityInfo> selectCommunityInfoList(CommunityInfo communityInfo)
    {
        return communityInfoMapper.selectCommunityInfoList(communityInfo);
    }

    /**
     * 新增小区信息
     * 
     * @param communityInfo 小区信息
     * @return 结果
     */
    @Override
    public int insertCommunityInfo(CommunityInfo communityInfo)
    {
        communityInfo.setCreateTime(DateUtils.getNowDate());
        communityInfo.setCommunityCode(IdWorker.getNextCode());
        return communityInfoMapper.insertCommunityInfo(communityInfo);
    }

    /**
     * 修改小区信息
     * 
     * @param communityInfo 小区信息
     * @return 结果
     */
    @Override
    public int updateCommunityInfo(CommunityInfo communityInfo)
    {
        communityInfo.setUpdateTime(DateUtils.getNowDate());
        return communityInfoMapper.updateCommunityInfo(communityInfo);
    }

    /**
     * 批量删除小区信息
     * 
     * @param ids 需要删除的小区信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityInfoByIds(Long[] ids)
    {
        return communityInfoMapper.deleteCommunityInfoByIds(ids);
    }

    /**
     * 删除小区信息信息
     * 
     * @param id 小区信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityInfoById(Long id)
    {
        return communityInfoMapper.deleteCommunityInfoById(id);
    }
}
