package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 竞赛活动信息对象 labs_contestinfo
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsContestinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String contestNo;

    /** 分类 */
    @Excel(name = "分类")
    private String classNo;

    /** 竞赛名称 */
    @Excel(name = "竞赛名称")
    private String contestName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String contestAdmin;

    /** 指导教师 */
    @Excel(name = "指导教师")
    private String contestGuider;

    /** 竞赛时间 */
    @Excel(name = "竞赛时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime contestDate;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String contestUrl;

    /** 答题内容 */
    @Excel(name = "答题内容")
    private String contestDesc;

    /** 培训开始时间 */
    @Excel(name = "培训开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trainStart;

    /** 培训结束时间 */
    @Excel(name = "培训结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trainEndit;

    /** 培训计划 */
    @Excel(name = "培训计划")
    private String trainPlan;

    /** 状态（1通过 2未通过） */
    @Excel(name = "状态", readConverterExp = "1=通过,2=未通过")
    private String contestState;

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
    public void setContestName(String contestName) 
    {
        this.contestName = contestName;
    }

    public String getContestName() 
    {
        return contestName;
    }
    public void setContestAdmin(String contestAdmin) 
    {
        this.contestAdmin = contestAdmin;
    }

    public String getContestAdmin() 
    {
        return contestAdmin;
    }
    public void setContestGuider(String contestGuider) 
    {
        this.contestGuider = contestGuider;
    }

    public String getContestGuider() 
    {
        return contestGuider;
    }
    public void setContestDate(LocalDateTime contestDate) 
    {
        this.contestDate = contestDate;
    }

    public LocalDateTime getContestDate() 
    {
        return contestDate;
    }
    public void setContestUrl(String contestUrl) 
    {
        this.contestUrl = contestUrl;
    }

    public String getContestUrl() 
    {
        return contestUrl;
    }
    public void setContestDesc(String contestDesc) 
    {
        this.contestDesc = contestDesc;
    }

    public String getContestDesc() 
    {
        return contestDesc;
    }
    public void setTrainStart(LocalDateTime trainStart) 
    {
        this.trainStart = trainStart;
    }

    public LocalDateTime getTrainStart() 
    {
        return trainStart;
    }
    public void setTrainEndit(LocalDateTime trainEndit) 
    {
        this.trainEndit = trainEndit;
    }

    public LocalDateTime getTrainEndit() 
    {
        return trainEndit;
    }
    public void setTrainPlan(String trainPlan) 
    {
        this.trainPlan = trainPlan;
    }

    public String getTrainPlan() 
    {
        return trainPlan;
    }
    public void setContestState(String contestState) 
    {
        this.contestState = contestState;
    }

    public String getContestState() 
    {
        return contestState;
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
            .append("contestNo", getContestNo())
            .append("classNo", getClassNo())
            .append("contestName", getContestName())
            .append("contestAdmin", getContestAdmin())
            .append("contestGuider", getContestGuider())
            .append("contestDate", getContestDate())
            .append("contestUrl", getContestUrl())
            .append("contestDesc", getContestDesc())
            .append("trainStart", getTrainStart())
            .append("trainEndit", getTrainEndit())
            .append("trainPlan", getTrainPlan())
            .append("contestState", getContestState())
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
