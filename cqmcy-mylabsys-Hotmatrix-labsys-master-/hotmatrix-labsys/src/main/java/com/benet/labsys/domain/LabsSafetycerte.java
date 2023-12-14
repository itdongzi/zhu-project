package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 安全证书对象 labs_safetycerte
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsSafetycerte extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String certeNo;

    /** 考试人员 */
    @Excel(name = "考试人员")
    private String cuserName;

    /** 证书名称 */
    @Excel(name = "证书名称")
    private String certeName;

    /** 证书地址 */
    @Excel(name = "证书地址")
    private String certeUrl;

    /** 证书简介 */
    @Excel(name = "证书简介")
    private String certeDesc;

    /** 发证时间 */
    @Excel(name = "发证时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime certeDate;

    /** 有效期时间 */
    @Excel(name = "有效期时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireDate;

    /** 证书状态（1通过 2未通过） */
    @Excel(name = "证书状态", readConverterExp = "1=通过,2=未通过")
    private String certeState;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 删除标志（1代表存在 0代表删除） */
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
    public void setCerteNo(String certeNo) 
    {
        this.certeNo = certeNo;
    }

    public String getCerteNo() 
    {
        return certeNo;
    }
    public void setCuserName(String cuserName) 
    {
        this.cuserName = cuserName;
    }

    public String getCuserName() 
    {
        return cuserName;
    }
    public void setCerteName(String certeName) 
    {
        this.certeName = certeName;
    }

    public String getCerteName() 
    {
        return certeName;
    }
    public void setCerteUrl(String certeUrl) 
    {
        this.certeUrl = certeUrl;
    }

    public String getCerteUrl() 
    {
        return certeUrl;
    }
    public void setCerteDesc(String certeDesc) 
    {
        this.certeDesc = certeDesc;
    }

    public String getCerteDesc() 
    {
        return certeDesc;
    }
    public void setCerteDate(LocalDateTime certeDate) 
    {
        this.certeDate = certeDate;
    }

    public LocalDateTime getCerteDate() 
    {
        return certeDate;
    }
    public void setExpireDate(LocalDateTime expireDate) 
    {
        this.expireDate = expireDate;
    }

    public LocalDateTime getExpireDate() 
    {
        return expireDate;
    }
    public void setCerteState(String certeState) 
    {
        this.certeState = certeState;
    }

    public String getCerteState() 
    {
        return certeState;
    }
    public void setCreateBy(String createBy) 
    {
        this.createBy = createBy;
    }

    public String getCreateBy() 
    {
        return createBy;
    }
    public void setCreateTime(LocalDateTime createTime) 
    {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateBy(String updateBy) 
    {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() 
    {
        return updateBy;
    }
    public void setUpdateTime(LocalDateTime updateTime) 
    {
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime() 
    {
        return updateTime;
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
            .append("certeNo", getCerteNo())
            .append("cuserName", getCuserName())
            .append("certeName", getCerteName())
            .append("certeUrl", getCerteUrl())
            .append("certeDesc", getCerteDesc())
            .append("certeDate", getCerteDate())
            .append("expireDate", getExpireDate())
            .append("certeState", getCerteState())
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
