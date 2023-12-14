package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 竞赛报名信息对象 labs_contestenroll
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsContestenroll extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 签到编号 */
    @Excel(name = "签到编号")
    private String enrollNo;

    /** 竞赛编号 */
    @Excel(name = "竞赛编号")
    private String contestNo;

    /** 小组编号 */
    @Excel(name = "小组编号")
    private String groupNo;

    /** 人员编号 */
    @Excel(name = "人员编号")
    private String userNo;

    /** 特长介绍 */
    @Excel(name = "特长介绍")
    private String userProfile;

    /** 签到时间 */
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enrollDate;

    /** 审核状态（1通过 2未通过） */
    @Excel(name = "审核状态", readConverterExp = "1=通过,2=未通过")
    private String checkState;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String checkDesc;

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
    public void setEnrollNo(String enrollNo) 
    {
        this.enrollNo = enrollNo;
    }

    public String getEnrollNo() 
    {
        return enrollNo;
    }
    public void setContestNo(String contestNo) 
    {
        this.contestNo = contestNo;
    }

    public String getContestNo() 
    {
        return contestNo;
    }
    public void setGroupNo(String groupNo) 
    {
        this.groupNo = groupNo;
    }

    public String getGroupNo() 
    {
        return groupNo;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setUserProfile(String userProfile) 
    {
        this.userProfile = userProfile;
    }

    public String getUserProfile() 
    {
        return userProfile;
    }
    public void setEnrollDate(LocalDateTime enrollDate) 
    {
        this.enrollDate = enrollDate;
    }

    public LocalDateTime getEnrollDate() 
    {
        return enrollDate;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setCheckDesc(String checkDesc) 
    {
        this.checkDesc = checkDesc;
    }

    public String getCheckDesc() 
    {
        return checkDesc;
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
            .append("enrollNo", getEnrollNo())
            .append("contestNo", getContestNo())
            .append("groupNo", getGroupNo())
            .append("userNo", getUserNo())
            .append("userProfile", getUserProfile())
            .append("enrollDate", getEnrollDate())
            .append("checkState", getCheckState())
            .append("checkDesc", getCheckDesc())
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
