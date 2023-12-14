package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 安全考核对象 labs_safetytest
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsSafetytest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String testNo;

    /** 考核人员 */
    @Excel(name = "考核人员")
    private String userNo;

    /** 试卷编号 */
    @Excel(name = "试卷编号")
    private String paperNo;

    /** 考核资料 */
    @Excel(name = "考核资料")
    private String testUrl;

    /** 考核时间 */
    @Excel(name = "考核时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime testDate;

    /** 考核得分 */
    @Excel(name = "考核得分")
    private Long testScore;

    /** 考核情况 */
    @Excel(name = "考核情况")
    private String testDesc;

    /** 考核状态（1通过 2未通过） */
    @Excel(name = "考核状态", readConverterExp = "1=通过,2=未通过")
    private String testState;

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
    public void setTestNo(String testNo) 
    {
        this.testNo = testNo;
    }

    public String getTestNo() 
    {
        return testNo;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setPaperNo(String paperNo) 
    {
        this.paperNo = paperNo;
    }

    public String getPaperNo() 
    {
        return paperNo;
    }
    public void setTestUrl(String testUrl) 
    {
        this.testUrl = testUrl;
    }

    public String getTestUrl() 
    {
        return testUrl;
    }
    public void setTestDate(LocalDateTime testDate) 
    {
        this.testDate = testDate;
    }

    public LocalDateTime getTestDate() 
    {
        return testDate;
    }
    public void setTestScore(Long testScore) 
    {
        this.testScore = testScore;
    }

    public Long getTestScore() 
    {
        return testScore;
    }
    public void setTestDesc(String testDesc) 
    {
        this.testDesc = testDesc;
    }

    public String getTestDesc() 
    {
        return testDesc;
    }
    public void setTestState(String testState) 
    {
        this.testState = testState;
    }

    public String getTestState() 
    {
        return testState;
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
            .append("testNo", getTestNo())
            .append("userNo", getUserNo())
            .append("paperNo", getPaperNo())
            .append("testUrl", getTestUrl())
            .append("testDate", getTestDate())
            .append("testScore", getTestScore())
            .append("testDesc", getTestDesc())
            .append("testState", getTestState())
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
