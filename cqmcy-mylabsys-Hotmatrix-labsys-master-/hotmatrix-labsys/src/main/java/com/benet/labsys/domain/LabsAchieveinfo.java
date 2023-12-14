package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 实训成果对象 labs_achieveinfo
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsAchieveinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String achieNo;

    /** 人员编号 */
    @Excel(name = "人员编号")
    private String userNo;

    /** 成果类型 */
    @Excel(name = "成果类型")
    private String classNo;

    /** 成果名称 */
    @Excel(name = "成果名称")
    private String achieTitle;

    /** 成果地址 */
    @Excel(name = "成果地址")
    private String achieUrl;

    /** 成果描述 */
    @Excel(name = "成果描述")
    private String achieDesc;

    /** 成果上传时间 */
    @Excel(name = "成果上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime achieDate;

    /** 状态（1通过 2未通过） */
    @Excel(name = "状态", readConverterExp = "1=通过,2=未通过")
    private String achieState;

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
    public void setAchieNo(String achieNo) 
    {
        this.achieNo = achieNo;
    }

    public String getAchieNo() 
    {
        return achieNo;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setAchieTitle(String achieTitle) 
    {
        this.achieTitle = achieTitle;
    }

    public String getAchieTitle() 
    {
        return achieTitle;
    }
    public void setAchieUrl(String achieUrl) 
    {
        this.achieUrl = achieUrl;
    }

    public String getAchieUrl() 
    {
        return achieUrl;
    }
    public void setAchieDesc(String achieDesc) 
    {
        this.achieDesc = achieDesc;
    }

    public String getAchieDesc() 
    {
        return achieDesc;
    }
    public void setAchieDate(LocalDateTime achieDate) 
    {
        this.achieDate = achieDate;
    }

    public LocalDateTime getAchieDate() 
    {
        return achieDate;
    }
    public void setAchieState(String achieState) 
    {
        this.achieState = achieState;
    }

    public String getAchieState() 
    {
        return achieState;
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
            .append("achieNo", getAchieNo())
            .append("userNo", getUserNo())
            .append("classNo", getClassNo())
            .append("achieTitle", getAchieTitle())
            .append("achieUrl", getAchieUrl())
            .append("achieDesc", getAchieDesc())
            .append("achieDate", getAchieDate())
            .append("achieState", getAchieState())
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
