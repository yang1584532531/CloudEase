package com.cloudease.basis.service;

import java.util.List;
import com.cloudease.basis.domain.CommunityInfo;

/**
 * 小区信息Service接口
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
public interface ICommunityInfoService 
{
    /**
     * 查询小区信息
     * 
     * @param id 小区信息主键
     * @return 小区信息
     */
    public CommunityInfo selectCommunityInfoById(Long id);

    /**
     * 查询小区信息列表
     * 
     * @param communityInfo 小区信息
     * @return 小区信息集合
     */
    public List<CommunityInfo> selectCommunityInfoList(CommunityInfo communityInfo);

    /**
     * 新增小区信息
     * 
     * @param communityInfo 小区信息
     * @return 结果
     */
    public int insertCommunityInfo(CommunityInfo communityInfo);

    /**
     * 修改小区信息
     * 
     * @param communityInfo 小区信息
     * @return 结果
     */
    public int updateCommunityInfo(CommunityInfo communityInfo);

    /**
     * 批量删除小区信息
     * 
     * @param ids 需要删除的小区信息主键集合
     * @return 结果
     */
    public int deleteCommunityInfoByIds(Long[] ids);

    /**
     * 删除小区信息信息
     * 
     * @param id 小区信息主键
     * @return 结果
     */
    public int deleteCommunityInfoById(Long id);
}
