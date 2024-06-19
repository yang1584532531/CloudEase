package com.cloudease.basis.mapper;

import java.util.List;
import com.cloudease.basis.domain.CommunityOrgInfo;

/**
 * 小区组织结构Mapper接口
 * 
 * @author yangchengjie
 * @date 2024-06-19
 */
public interface CommunityOrgInfoMapper 
{
    /**
     * 查询小区组织结构
     * 
     * @param communityOrgId 小区组织结构主键
     * @return 小区组织结构
     */
    public CommunityOrgInfo selectCommunityOrgInfoBycommunityOrgId(Long communityOrgId);

    /**
     * 查询小区组织结构列表
     * 
     * @param communityOrgInfo 小区组织结构
     * @return 小区组织结构集合
     */
    public List<CommunityOrgInfo> selectCommunityOrgInfoList(CommunityOrgInfo communityOrgInfo);

    /**
     * 新增小区组织结构
     * 
     * @param communityOrgInfo 小区组织结构
     * @return 结果
     */
    public int insertCommunityOrgInfo(CommunityOrgInfo communityOrgInfo);

    /**
     * 修改小区组织结构
     * 
     * @param communityOrgInfo 小区组织结构
     * @return 结果
     */
    public int updateCommunityOrgInfo(CommunityOrgInfo communityOrgInfo);

    /**
     * 删除小区组织结构
     * 
     * @param communityOrgId 小区组织结构主键
     * @return 结果
     */
    public int deleteCommunityOrgInfoBycommunityOrgId(Long communityOrgId);

    /**
     * 批量删除小区组织结构
     * 
     * @param communityOrgIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityOrgInfoBycommunityOrgIds(Long[] communityOrgIds);
}
