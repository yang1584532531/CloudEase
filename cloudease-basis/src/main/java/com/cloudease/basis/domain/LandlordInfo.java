package com.cloudease.basis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudease.common.annotation.Excel;
import com.cloudease.common.core.domain.BaseEntity;

/**
 * 业主信息对象 landlord_info
 * 
 * @author yangchengjie
 * @date 2024-06-13
 */
public class LandlordInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 业主编码 */
    @Excel(name = "业主编码")
    private String landlordCode;

    /** 业主姓名 */
    @Excel(name = "业主姓名")
    private String landlordName;

    /** 业主性别 */
    @Excel(name = "业主性别")
    private String sex;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String landlordIdcard;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String landlordPhone;

    /** 业主类型 */
    @Excel(name = "业主类型")
    private String landlordType;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLandlordCode(String landlordCode) 
    {
        this.landlordCode = landlordCode;
    }

    public String getLandlordCode() 
    {
        return landlordCode;
    }
    public void setLandlordName(String landlordName) 
    {
        this.landlordName = landlordName;
    }

    public String getLandlordName() 
    {
        return landlordName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setLandlordIdcard(String landlordIdcard) 
    {
        this.landlordIdcard = landlordIdcard;
    }

    public String getLandlordIdcard() 
    {
        return landlordIdcard;
    }
    public void setLandlordPhone(String landlordPhone) 
    {
        this.landlordPhone = landlordPhone;
    }

    public String getLandlordPhone() 
    {
        return landlordPhone;
    }
    public void setLandlordType(String landlordType) 
    {
        this.landlordType = landlordType;
    }

    public String getLandlordType() 
    {
        return landlordType;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("landlordCode", getLandlordCode())
            .append("landlordName", getLandlordName())
            .append("sex", getSex())
            .append("landlordIdcard", getLandlordIdcard())
            .append("landlordPhone", getLandlordPhone())
            .append("landlordType", getLandlordType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
