package com.cloudease.basis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudease.common.annotation.Excel;
import com.cloudease.common.core.domain.BaseEntity;

/**
 * 小区组织结构对象 community_org_info
 * 
 * @author yangchengjie
 * @date 2024-06-19
 */
public class CommunityOrgInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 组织id */
    private Long communityOrgId;

    /** 父组织id */
    @Excel(name = "父组织id")
    private Long communityOrgParentId;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String communityAncestors;

    /** 组织名称 */
    @Excel(name = "组织名称")
    private String communityOrgName;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 组织状态（0正常 1停用） */
    @Excel(name = "组织状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


    public void setCommunityOrgId(Long communityOrgId) {
        this.communityOrgId = communityOrgId;
    }

    public Long getcommunityOrgId() 
    {
        return communityOrgId;
    }
    public void setCommunityOrgParentId(Long communityOrgParentId) 
    {
        this.communityOrgParentId = communityOrgParentId;
    }

    public Long getCommunityOrgParentId() 
    {
        return communityOrgParentId;
    }
    public void setCommunityAncestors(String communityAncestors) 
    {
        this.communityAncestors = communityAncestors;
    }

    public String getCommunityAncestors() 
    {
        return communityAncestors;
    }
    public void setCommunityOrgName(String communityOrgName) 
    {
        this.communityOrgName = communityOrgName;
    }

    public String getCommunityOrgName() 
    {
        return communityOrgName;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("communityOrgId", getcommunityOrgId())
            .append("communityOrgParentId", getCommunityOrgParentId())
            .append("communityAncestors", getCommunityAncestors())
            .append("communityOrgName", getCommunityOrgName())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
