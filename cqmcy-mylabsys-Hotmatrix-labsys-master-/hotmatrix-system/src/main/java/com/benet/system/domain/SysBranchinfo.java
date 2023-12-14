package com.benet.system.domain;

import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分支信息对象 sys_branchinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysBranchinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 分支id */
    @Excel(name = "分支id")
    private String branchNo;

    /** 分支名称 */
    @Excel(name = "分支名称")
    private String branchName;

    /** 分支编码 */
    @Excel(name = "分支编码")
    private String branchCode;

    /** 分支类型 */
    @Excel(name = "分支类型")
    private String branchType;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 负责人 */
    @Excel(name = "负责人")
    private String master;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String telephone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 简介 */
    @Excel(name = "简介")
    private String summary;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 删除标志（1代表存在 0代表删除） */
    @Excel(name = "删除标志", readConverterExp = "1=代表存在,0=代表删除")
    private String deleteFlag;

    /** 更新者 */
    @Excel(name = "更新者")
    private String comments;

    /** 应用编码 */
    @Excel(name = "应用编码")
    private String appCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
    }
    public void setBranchName(String branchName) 
    {
        this.branchName = branchName;
    }

    public String getBranchName() 
    {
        return branchName;
    }
    public void setBranchCode(String branchCode) 
    {
        this.branchCode = branchCode;
    }

    public String getBranchCode() 
    {
        return branchCode;
    }
    public void setBranchType(String branchType) 
    {
        this.branchType = branchType;
    }

    public String getBranchType() 
    {
        return branchType;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
    }
    public void setMaster(String master) 
    {
        this.master = master;
    }

    public String getMaster() 
    {
        return master;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setDeleteFlag(String deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() 
    {
        return deleteFlag;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setAppCode(String appCode) 
    {
        this.appCode = appCode;
    }

    public String getAppCode() 
    {
        return appCode;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("branchNo", getBranchNo())
            .append("branchName", getBranchName())
            .append("branchCode", getBranchCode())
            .append("branchType", getBranchType())
            .append("orderNo", getOrderNo())
            .append("master", getMaster())
            .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("summary", getSummary())
            .append("checkState", getCheckState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deleteFlag", getDeleteFlag())
            .append("comments", getComments())
            .append("appCode", getAppCode())
            .append("version", getVersion())
            .toString();
    }
}
