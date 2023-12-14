package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 学期学年信息对象 labs_semesterinfo
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsSemesterinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 学期编号 */
    @Excel(name = "学期编号")
    private String semeNo;

    /** 学年学期名称 */
    @Excel(name = "学年学期名称")
    private String semeName;

    /** 学期类型(第几学期) */
    @Excel(name = "学期类型(第几学期)")
    private String semeType;

    /** 学期周次 */
    @Excel(name = "学期周次")
    private Integer semeWeeks;

    /** 开始日期 */
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    /** 结束日期 */
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enditDate;

    /** 开始周次（按年计算） */
    @Excel(name = "开始周次", readConverterExp = "按=年计算")
    private Integer startWeek;

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
    public void setSemeNo(String semeNo) 
    {
        this.semeNo = semeNo;
    }

    public String getSemeNo() 
    {
        return semeNo;
    }
    public void setSemeName(String semeName) 
    {
        this.semeName = semeName;
    }

    public String getSemeName() 
    {
        return semeName;
    }
    public void setSemeType(String semeType) 
    {
        this.semeType = semeType;
    }

    public String getSemeType() 
    {
        return semeType;
    }
    public void setSemeWeeks(Integer semeWeeks) 
    {
        this.semeWeeks = semeWeeks;
    }

    public Integer getSemeWeeks() 
    {
        return semeWeeks;
    }
    public void setStartDate(LocalDateTime startDate) 
    {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDate() 
    {
        return startDate;
    }
    public void setEnditDate(LocalDateTime enditDate) 
    {
        this.enditDate = enditDate;
    }

    public LocalDateTime getEnditDate() 
    {
        return enditDate;
    }
    public void setStartWeek(Integer startWeek) 
    {
        this.startWeek = startWeek;
    }

    public Integer getStartWeek() 
    {
        return startWeek;
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
            .append("semeNo", getSemeNo())
            .append("semeName", getSemeName())
            .append("semeType", getSemeType())
            .append("semeWeeks", getSemeWeeks())
            .append("startDate", getStartDate())
            .append("enditDate", getEnditDate())
            .append("startWeek", getStartWeek())
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
