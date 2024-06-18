package com.cloudease.basis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudease.common.annotation.Excel;
import com.cloudease.common.core.domain.BaseEntity;

/**
 * 小区信息对象 community_info
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
public class CommunityInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 小区编码 */
    @Excel(name = "小区编码")
    private String communityCode;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityName;

    /** 小区经纬度 */
    @Excel(name = "小区经纬度")
    private String communityCoordinate;

    /** 小区地址 */
    @Excel(name = "小区地址")
    private String communityAddress;

    /** 小区联系人 */
    @Excel(name = "小区联系人")
    private String communityContacts;

    /** 小区联系方式 */
    @Excel(name = "小区联系方式")
    private String communityPhone;

    /** 状态 */
    @Excel(name = "状态")
    private String communityStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCommunityCode(String communityCode) 
    {
        this.communityCode = communityCode;
    }

    public String getCommunityCode() 
    {
        return communityCode;
    }
    public void setCommunityName(String communityName) 
    {
        this.communityName = communityName;
    }

    public String getCommunityName() 
    {
        return communityName;
    }
    public void setCommunityCoordinate(String communityCoordinate) 
    {
        this.communityCoordinate = communityCoordinate;
    }

    public String getCommunityCoordinate() 
    {
        return communityCoordinate;
    }
    public void setCommunityAddress(String communityAddress) 
    {
        this.communityAddress = communityAddress;
    }

    public String getCommunityAddress() 
    {
        return communityAddress;
    }
    public void setCommunityContacts(String communityContacts) 
    {
        this.communityContacts = communityContacts;
    }

    public String getCommunityContacts() 
    {
        return communityContacts;
    }
    public void setCommunityPhone(String communityPhone) 
    {
        this.communityPhone = communityPhone;
    }

    public String getCommunityPhone() 
    {
        return communityPhone;
    }
    public void setCommunityStatus(String communityStatus) 
    {
        this.communityStatus = communityStatus;
    }

    public String getCommunityStatus() 
    {
        return communityStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("communityCode", getCommunityCode())
            .append("communityName", getCommunityName())
            .append("communityCoordinate", getCommunityCoordinate())
            .append("communityAddress", getCommunityAddress())
            .append("communityContacts", getCommunityContacts())
            .append("communityPhone", getCommunityPhone())
            .append("communityStatus", getCommunityStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
