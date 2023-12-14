package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 会议室预约对象 labs_meetorders
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
public class LabsMeetorders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 预约编号 */
    @Excel(name = "预约编号")
    private String orderNo;

    /** 会议室编号 */
    @Excel(name = "会议室编号")
    private String meetNo;

    /** 预约人 */
    @Excel(name = "预约人")
    private String userName;

    /** 预约人电话 */
    @Excel(name = "预约人电话")
    private String userTel;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enditDate;

    /** 预约会议主题 */
    @Excel(name = "预约会议主题")
    private String orderTitle;

    /** 预约参会人员 */
    @Excel(name = "预约参会人员")
    private String orderUsers;

    /** 预约会议内容 */
    @Excel(name = "预约会议内容")
    private String orderDesc;

    /** 通知方式(1消息通知 2自行通知) */
    @Excel(name = "通知方式(1消息通知 2自行通知)")
    private String notifyType;

    /** 审核状态（1正常 0停用） */
    @Excel(name = "审核状态", readConverterExp = "1=正常,0=停用")
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
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserTel(String userTel) 
    {
        this.userTel = userTel;
    }

    public String getUserTel() 
    {
        return userTel;
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
    public void setOrderTitle(String orderTitle) 
    {
        this.orderTitle = orderTitle;
    }

    public String getOrderTitle() 
    {
        return orderTitle;
    }
    public void setOrderUsers(String orderUsers) 
    {
        this.orderUsers = orderUsers;
    }

    public String getOrderUsers() 
    {
        return orderUsers;
    }
    public void setOrderDesc(String orderDesc) 
    {
        this.orderDesc = orderDesc;
    }

    public String getOrderDesc() 
    {
        return orderDesc;
    }
    public void setNotifyType(String notifyType) 
    {
        this.notifyType = notifyType;
    }

    public String getNotifyType() 
    {
        return notifyType;
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
            .append("orderNo", getOrderNo())
            .append("meetNo", getMeetNo())
            .append("userName", getUserName())
            .append("userTel", getUserTel())
            .append("startDate", getStartDate())
            .append("enditDate", getEnditDate())
            .append("orderTitle", getOrderTitle())
            .append("orderUsers", getOrderUsers())
            .append("orderDesc", getOrderDesc())
            .append("notifyType", getNotifyType())
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
