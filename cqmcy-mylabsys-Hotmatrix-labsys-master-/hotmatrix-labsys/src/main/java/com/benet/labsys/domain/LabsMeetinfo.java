package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 会议室信息对象 labs_meetinfo
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
public class LabsMeetinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 会议室编号 */
    @Excel(name = "会议室编号")
    private String meetNo;

    /** 会议室名称 */
    @Excel(name = "会议室名称")
    private String meetName;

    /** 会议室编码 */
    @Excel(name = "会议室编码")
    private String meetCode;

    /** 所属区域编号 */
    @Excel(name = "所属区域编号")
    private String areaNo;

    /** 会议室类别 */
    @Excel(name = "会议室类别")
    private String classNo;

    /** 会议室管理员 */
    @Excel(name = "会议室管理员")
    private String meetAdmin;

    /** 会议室规模/大小 */
    @Excel(name = "会议室规模/大小")
    private Integer meetScale;

    /** 会议室简介 */
    @Excel(name = "会议室简介")
    private String meetDesc;

    /** 可预约状态（1可预约 0不可预约） */
    @Excel(name = "可预约状态", readConverterExp = "1=可预约,0=不可预约")
    private String orderState;

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
    public void setMeetNo(String meetNo) 
    {
        this.meetNo = meetNo;
    }

    public String getMeetNo() 
    {
        return meetNo;
    }
    public void setMeetName(String meetName) 
    {
        this.meetName = meetName;
    }

    public String getMeetName() 
    {
        return meetName;
    }
    public void setMeetCode(String meetCode) 
    {
        this.meetCode = meetCode;
    }

    public String getMeetCode() 
    {
        return meetCode;
    }
    public void setAreaNo(String areaNo) 
    {
        this.areaNo = areaNo;
    }

    public String getAreaNo() 
    {
        return areaNo;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setMeetAdmin(String meetAdmin) 
    {
        this.meetAdmin = meetAdmin;
    }

    public String getMeetAdmin() 
    {
        return meetAdmin;
    }
    public void setMeetScale(Integer meetScale) 
    {
        this.meetScale = meetScale;
    }

    public Integer getMeetScale() 
    {
        return meetScale;
    }
    public void setMeetDesc(String meetDesc) 
    {
        this.meetDesc = meetDesc;
    }

    public String getMeetDesc() 
    {
        return meetDesc;
    }
    public void setOrderState(String orderState) 
    {
        this.orderState = orderState;
    }

    public String getOrderState() 
    {
        return orderState;
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
            .append("meetNo", getMeetNo())
            .append("meetName", getMeetName())
            .append("meetCode", getMeetCode())
            .append("areaNo", getAreaNo())
            .append("classNo", getClassNo())
            .append("meetAdmin", getMeetAdmin())
            .append("meetScale", getMeetScale())
            .append("meetDesc", getMeetDesc())
            .append("orderState", getOrderState())
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
