package com.cloudease.basis.service.impl;

import java.util.List;

import com.cloudease.common.constant.UserConstants;
import com.cloudease.common.exception.ServiceException;
import com.cloudease.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudease.basis.mapper.CommunityOrgInfoMapper;
import com.cloudease.basis.domain.CommunityOrgInfo;
import com.cloudease.basis.service.ICommunityOrgInfoService;

/**
 * 小区组织结构Service业务层处理
 * 
 * @author yangchengjie
 * @date 2024-06-19
 */
@Service
public class CommunityOrgInfoServiceImpl implements ICommunityOrgInfoService 
{
    @Autowired
    private CommunityOrgInfoMapper communityOrgInfoMapper;

    /**
     * 查询小区组织结构
     * 
     * @param communityOrgId 小区组织结构主键
     * @return 小区组织结构
     */
    @Override
    public CommunityOrgInfo selectCommunityOrgInfoBycommunityOrgId(Long communityOrgId)
    {
        return communityOrgInfoMapper.selectCommunityOrgInfoBycommunityOrgId(communityOrgId);
    }

    /**
     * 查询小区组织结构列表
     * 
     * @param communityOrgInfo 小区组织结构
     * @return 小区组织结构
     */
    @Override
    public List<CommunityOrgInfo> selectCommunityOrgInfoList(CommunityOrgInfo communityOrgInfo)
    {
        return communityOrgInfoMapper.selectCommunityOrgInfoList(communityOrgInfo);
    }

    /**
     * 新增小区组织结构
     * 
     * @param communityOrgInfo 小区组织结构
     * @return 结果
     */
    @Override
    public int insertCommunityOrgInfo(CommunityOrgInfo communityOrgInfo)
    {
        CommunityOrgInfo info = communityOrgInfoMapper.selectCommunityOrgInfoBycommunityOrgId(communityOrgInfo.getCommunityOrgParentId());
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new ServiceException("组织停用，不允许新增");
        }
        communityOrgInfo.setCreateTime(DateUtils.getNowDate());
        communityOrgInfo.setCommunityAncestors(info.getCommunityAncestors() + "," + communityOrgInfo.getCommunityOrgParentId());
        return communityOrgInfoMapper.insertCommunityOrgInfo(communityOrgInfo);
    }

    /**
     * 修改小区组织结构
     * 
     * @param communityOrgInfo 小区组织结构
     * @return 结果
     */
    @Override
    public int updateCommunityOrgInfo(CommunityOrgInfo communityOrgInfo)
    {
        communityOrgInfo.setUpdateTime(DateUtils.getNowDate());
        return communityOrgInfoMapper.updateCommunityOrgInfo(communityOrgInfo);
    }

    /**
     * 批量删除小区组织结构
     * 
     * @param communityOrgIds 需要删除的小区组织结构主键
     * @return 结果
     */
    @Override
    public int deleteCommunityOrgInfoBycommunityOrgIds(Long[] communityOrgIds)
    {
        return communityOrgInfoMapper.deleteCommunityOrgInfoBycommunityOrgIds(communityOrgIds);
    }

    /**
     * 删除小区组织结构信息
     * 
     * @param communityOrgId 小区组织结构主键
     * @return 结果
     */
    @Override
    public int deleteCommunityOrgInfoBycommunityOrgId(Long communityOrgId)
    {
        return communityOrgInfoMapper.deleteCommunityOrgInfoBycommunityOrgId(communityOrgId);
    }
}
