package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 实训室工作/检查记录对象 labs_worklog
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsWorklog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String workNo;

    /** 实训室编号 */
    @Excel(name = "实训室编号")
    private String roomNo;

    /** 检查员编号 */
    @Excel(name = "检查员编号")
    private String userNo;

    /** 检查类别(1:领导巡查,2:管理员自查,3:随手举报,4:卫生检查,5:安全检查,6:假期检查) */
    @Excel(name = "检查类别(1:领导巡查,2:管理员自查,3:随手举报,4:卫生检查,5:安全检查,6:假期检查)")
    private String dataType;

    /** 工作/检查分类 */
    @Excel(name = "工作/检查分类")
    private String classNo;

    /** 工作/检查标题 */
    @Excel(name = "工作/检查标题")
    private String workTitle;

    /** 工作/检查方式 */
    @Excel(name = "工作/检查方式")
    private String workMode;

    /** 工作/检查内容 */
    @Excel(name = "工作/检查内容")
    private String workContent;

    /** 工作/检查照片 */
    @Excel(name = "工作/检查照片")
    private String workPicture;

    /** 工作/检查语音 */
    @Excel(name = "工作/检查语音")
    private String workSound;

    /** 工作/检查视频 */
    @Excel(name = "工作/检查视频")
    private String workVideo;

    /** 工作/检查结果 */
    @Excel(name = "工作/检查结果")
    private String workResult;

    /** 工作/检查时间 */
    @Excel(name = "工作/检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workDate;

    /** 工作/检查总得分 */
    @Excel(name = "工作/检查总得分")
    private Long workTscore;

    /** 状态（1检查中 2已检查） */
    @Excel(name = "状态", readConverterExp = "1=检查中,2=已检查")
    private String workState;

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
    public void setWorkNo(String workNo) 
    {
        this.workNo = workNo;
    }

    public String getWorkNo() 
    {
        return workNo;
    }
    public void setRoomNo(String roomNo) 
    {
        this.roomNo = roomNo;
    }

    public String getRoomNo() 
    {
        return roomNo;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setWorkTitle(String workTitle) 
    {
        this.workTitle = workTitle;
    }

    public String getWorkTitle() 
    {
        return workTitle;
    }
    public void setWorkMode(String workMode) 
    {
        this.workMode = workMode;
    }

    public String getWorkMode() 
    {
        return workMode;
    }
    public void setWorkContent(String workContent) 
    {
        this.workContent = workContent;
    }

    public String getWorkContent() 
    {
        return workContent;
    }
    public void setWorkPicture(String workPicture) 
    {
        this.workPicture = workPicture;
    }

    public String getWorkPicture() 
    {
        return workPicture;
    }
    public void setWorkSound(String workSound) 
    {
        this.workSound = workSound;
    }

    public String getWorkSound() 
    {
        return workSound;
    }
    public void setWorkVideo(String workVideo) 
    {
        this.workVideo = workVideo;
    }

    public String getWorkVideo() 
    {
        return workVideo;
    }
    public void setWorkResult(String workResult) 
    {
        this.workResult = workResult;
    }

    public String getWorkResult() 
    {
        return workResult;
    }
    public void setWorkDate(LocalDateTime workDate) 
    {
        this.workDate = workDate;
    }

    public LocalDateTime getWorkDate() 
    {
        return workDate;
    }
    public void setWorkTscore(Long workTscore) 
    {
        this.workTscore = workTscore;
    }

    public Long getWorkTscore() 
    {
        return workTscore;
    }
    public void setWorkState(String workState) 
    {
        this.workState = workState;
    }

    public String getWorkState() 
    {
        return workState;
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
            .append("workNo", getWorkNo())
            .append("roomNo", getRoomNo())
            .append("userNo", getUserNo())
            .append("dataType", getDataType())
            .append("classNo", getClassNo())
            .append("workTitle", getWorkTitle())
            .append("workMode", getWorkMode())
            .append("workContent", getWorkContent())
            .append("workPicture", getWorkPicture())
            .append("workSound", getWorkSound())
            .append("workVideo", getWorkVideo())
            .append("workResult", getWorkResult())
            .append("workDate", getWorkDate())
            .append("workTscore", getWorkTscore())
            .append("workState", getWorkState())
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
