package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 会议室排班对象 labs_meetschedule
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
public class LabsMeetschedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 排班编号 */
    @Excel(name = "排班编号")
    private String schdNo;

    /** 预约编号 */
    @Excel(name = "预约编号")
    private String orderNo;

    /** 会议室编号 */
    @Excel(name = "会议室编号")
    private String meetNo;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enditDate;

    /** 会议主题 */
    @Excel(name = "会议主题")
    private String meetTitle;

    /** 会议内容 */
    @Excel(name = "会议内容")
    private String meetDesc;

    /** 参会人员 */
    @Excel(name = "参会人员")
    private String meetUsers;

    /** 会议状况 */
    @Excel(name = "会议状况")
    private String meetResult;

    /** 会议状态（1正常 2取消 3超时 4调整） */
    @Excel(name = "会议状态", readConverterExp = "1=正常,2=取消,3=超时,4=调整")
    private String meetState;

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
    public void setSchdNo(String schdNo) 
    {
        this.schdNo = schdNo;
    }

    public String getSchdNo() 
    {
        return schdNo;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setMeetNo(String meetNo) 
    {
        this.meetNo = meetNo;
    }

    public String getMeetNo() 
    {
        return meetNo;
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
    public void setMeetTitle(String meetTitle) 
    {
        this.meetTitle = meetTitle;
    }

    public String getMeetTitle() 
    {
        return meetTitle;
    }
    public void setMeetDesc(String meetDesc) 
    {
        this.meetDesc = meetDesc;
    }

    public String getMeetDesc() 
    {
        return meetDesc;
    }
    public void setMeetUsers(String meetUsers) 
    {
        this.meetUsers = meetUsers;
    }

    public String getMeetUsers() 
    {
        return meetUsers;
    }
    public void setMeetResult(String meetResult) 
    {
        this.meetResult = meetResult;
    }

    public String getMeetResult() 
    {
        return meetResult;
    }
    public void setMeetState(String meetState) 
    {
        this.meetState = meetState;
    }

    public String getMeetState() 
    {
        return meetState;
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
            .append("schdNo", getSchdNo())
            .append("orderNo", getOrderNo())
            .append("meetNo", getMeetNo())
            .append("startDate", getStartDate())
            .append("enditDate", getEnditDate())
            .append("meetTitle", getMeetTitle())
            .append("meetDesc", getMeetDesc())
            .append("meetUsers", getMeetUsers())
            .append("meetResult", getMeetResult())
            .append("meetState", getMeetState())
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
