package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

/**
 * 字典类型对象 sys_dicttype
 * 
 * @author yoxking
 * @date 2022-01-03
 */
public class SysDicttype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型编号 */
    @Excel(name = "类型编号")
    private String dtypeNo;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String dtypeName;

    /** 类型编码 */
    @Excel(name = "类型编码")
    private String dtypeCode;

    /** 类型描述 */
    @Excel(name = "类型描述")
    private String dtypeDesc;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private Integer checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

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
    public void setDtypeNo(String dtypeNo) 
    {
        this.dtypeNo = dtypeNo;
    }

    public String getDtypeNo() 
    {
        return dtypeNo;
    }
    public void setDtypeName(String dtypeName) 
    {
        this.dtypeName = dtypeName;
    }

    public String getDtypeName() 
    {
        return dtypeName;
    }
    public void setDtypeCode(String dtypeCode) 
    {
        this.dtypeCode = dtypeCode;
    }

    public String getDtypeCode() 
    {
        return dtypeCode;
    }
    public void setDtypeDesc(String dtypeDesc) 
    {
        this.dtypeDesc = dtypeDesc;
    }

    public String getDtypeDesc() 
    {
        return dtypeDesc;
    }
    public void setCheckState(Integer checkState) 
    {
        this.checkState = checkState;
    }

    public Integer getCheckState() 
    {
        return checkState;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dtypeNo", getDtypeNo())
            .append("dtypeName", getDtypeName())
            .append("dtypeCode", getDtypeCode())
            .append("dtypeDesc", getDtypeDesc())
            .append("checkState", getCheckState())
            .append("branchNo", getBranchNo())
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
