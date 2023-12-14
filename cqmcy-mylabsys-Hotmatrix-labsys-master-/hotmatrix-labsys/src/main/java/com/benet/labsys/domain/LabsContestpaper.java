package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 竞赛试题信息对象 labs_contestpaper
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsContestpaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String paperNo;

    /** 竞赛编号 */
    @Excel(name = "竞赛编号")
    private String contestNo;

    /** 试卷分类 */
    @Excel(name = "试卷分类")
    private String classNo;

    /** 试卷名称 */
    @Excel(name = "试卷名称")
    private String paperTitle;

    /** 类别 */
    @Excel(name = "类别")
    private String paperType;

    /** 竞赛年度 */
    @Excel(name = "竞赛年度")
    private String paperYear;

    /** 考核时长（分钟） */
    @Excel(name = "考核时长", readConverterExp = "分=钟")
    private Long paperDuration;

    /** 试卷地址 */
    @Excel(name = "试卷地址")
    private String paperUrl;

    /** 试卷描述 */
    @Excel(name = "试卷描述")
    private String paperDesc;

    /** 审核状态（1未审核 2已审核） */
    @Excel(name = "审核状态", readConverterExp = "1=未审核,2=已审核")
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
    public void setPaperNo(String paperNo) 
    {
        this.paperNo = paperNo;
    }

    public String getPaperNo() 
    {
        return paperNo;
    }
    public void setContestNo(String contestNo) 
    {
        this.contestNo = contestNo;
    }

    public String getContestNo() 
    {
        return contestNo;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setPaperTitle(String paperTitle) 
    {
        this.paperTitle = paperTitle;
    }

    public String getPaperTitle() 
    {
        return paperTitle;
    }
    public void setPaperType(String paperType) 
    {
        this.paperType = paperType;
    }

    public String getPaperType() 
    {
        return paperType;
    }
    public void setPaperYear(String paperYear) 
    {
        this.paperYear = paperYear;
    }

    public String getPaperYear() 
    {
        return paperYear;
    }
    public void setPaperDuration(Long paperDuration) 
    {
        this.paperDuration = paperDuration;
    }

    public Long getPaperDuration() 
    {
        return paperDuration;
    }
    public void setPaperUrl(String paperUrl) 
    {
        this.paperUrl = paperUrl;
    }

    public String getPaperUrl() 
    {
        return paperUrl;
    }
    public void setPaperDesc(String paperDesc) 
    {
        this.paperDesc = paperDesc;
    }

    public String getPaperDesc() 
    {
        return paperDesc;
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
            .append("paperNo", getPaperNo())
            .append("contestNo", getContestNo())
            .append("classNo", getClassNo())
            .append("paperTitle", getPaperTitle())
            .append("paperType", getPaperType())
            .append("paperYear", getPaperYear())
            .append("paperDuration", getPaperDuration())
            .append("paperUrl", getPaperUrl())
            .append("paperDesc", getPaperDesc())
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
