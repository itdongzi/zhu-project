package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 申报项目信息对象 labs_projectinfo
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsProjectinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String projectNo;

    /** 人员编号 */
    @Excel(name = "人员编号")
    private String userNo;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String classNo;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectTitle;

    /** 项目资源 */
    @Excel(name = "项目资源")
    private String projectUrl;

    /** 项目描述 */
    @Excel(name = "项目描述")
    private String projectDesc;

    /** 项目申请时间 */
    @Excel(name = "项目申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishDate;

    /** 处理状态（1通过 2未通过） */
    @Excel(name = "处理状态", readConverterExp = "1=通过,2=未通过")
    private String processState;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

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
    public void setProjectNo(String projectNo) 
    {
        this.projectNo = projectNo;
    }

    public String getProjectNo() 
    {
        return projectNo;
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
    public void setProjectTitle(String projectTitle) 
    {
        this.projectTitle = projectTitle;
    }

    public String getProjectTitle() 
    {
        return projectTitle;
    }
    public void setProjectUrl(String projectUrl) 
    {
        this.projectUrl = projectUrl;
    }

    public String getProjectUrl() 
    {
        return projectUrl;
    }
    public void setProjectDesc(String projectDesc) 
    {
        this.projectDesc = projectDesc;
    }

    public String getProjectDesc() 
    {
        return projectDesc;
    }
    public void setPublishDate(LocalDateTime publishDate) 
    {
        this.publishDate = publishDate;
    }

    public LocalDateTime getPublishDate() 
    {
        return publishDate;
    }
    public void setProcessState(String processState) 
    {
        this.processState = processState;
    }

    public String getProcessState() 
    {
        return processState;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
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
            .append("projectNo", getProjectNo())
            .append("userNo", getUserNo())
            .append("classNo", getClassNo())
            .append("projectTitle", getProjectTitle())
            .append("projectUrl", getProjectUrl())
            .append("projectDesc", getProjectDesc())
            .append("publishDate", getPublishDate())
            .append("processState", getProcessState())
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
