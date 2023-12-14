package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 设备报修信息对象 labs_devicerepair
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsDevicerepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String repairNo;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceNo;

    /** 实训室编号 */
    @Excel(name = "实训室编号")
    private String roomNo;

    /** 报修人编号 */
    @Excel(name = "报修人编号")
    private String userNo;

    /** 报修人姓名 */
    @Excel(name = "报修人姓名")
    private String userName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String userTel;

    /** 报修标题 */
    @Excel(name = "报修标题")
    private String repairTitle;

    /** 报修类别 */
    @Excel(name = "报修类别")
    private String repairType;

    /** 故障描述 */
    @Excel(name = "故障描述")
    private String repairDesc;

    /** 故障照片 */
    @Excel(name = "故障照片")
    private String repairPicture;

    /** 故障视频 */
    @Excel(name = "故障视频")
    private String repairVideo;

    /** 报修时间 */
    @Excel(name = "报修时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime repairDate;

    /** 状态（1已处理 2待处理） */
    @Excel(name = "状态", readConverterExp = "1=已处理,2=待处理")
    private String solveState;

    /** 处理人 */
    @Excel(name = "处理人")
    private String solveUser;

    /** 处理时间 */
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime solveDate;

    /** 处理照片 */
    @Excel(name = "处理照片")
    private String solvePicture;

    /** 处理视频 */
    @Excel(name = "处理视频")
    private String solveVideo;

    /** 故障结果 */
    @Excel(name = "故障结果")
    private String solveResult;

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
    public void setRepairNo(String repairNo) 
    {
        this.repairNo = repairNo;
    }

    public String getRepairNo() 
    {
        return repairNo;
    }
    public void setDeviceNo(String deviceNo) 
    {
        this.deviceNo = deviceNo;
    }

    public String getDeviceNo() 
    {
        return deviceNo;
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
    public void setRepairTitle(String repairTitle) 
    {
        this.repairTitle = repairTitle;
    }

    public String getRepairTitle() 
    {
        return repairTitle;
    }
    public void setRepairType(String repairType) 
    {
        this.repairType = repairType;
    }

    public String getRepairType() 
    {
        return repairType;
    }
    public void setRepairDesc(String repairDesc) 
    {
        this.repairDesc = repairDesc;
    }

    public String getRepairDesc() 
    {
        return repairDesc;
    }
    public void setRepairPicture(String repairPicture) 
    {
        this.repairPicture = repairPicture;
    }

    public String getRepairPicture() 
    {
        return repairPicture;
    }
    public void setRepairVideo(String repairVideo) 
    {
        this.repairVideo = repairVideo;
    }

    public String getRepairVideo() 
    {
        return repairVideo;
    }
    public void setRepairDate(LocalDateTime repairDate) 
    {
        this.repairDate = repairDate;
    }

    public LocalDateTime getRepairDate() 
    {
        return repairDate;
    }
    public void setSolveState(String solveState) 
    {
        this.solveState = solveState;
    }

    public String getSolveState() 
    {
        return solveState;
    }
    public void setSolveUser(String solveUser) 
    {
        this.solveUser = solveUser;
    }

    public String getSolveUser() 
    {
        return solveUser;
    }
    public void setSolveDate(LocalDateTime solveDate) 
    {
        this.solveDate = solveDate;
    }

    public LocalDateTime getSolveDate() 
    {
        return solveDate;
    }
    public void setSolvePicture(String solvePicture) 
    {
        this.solvePicture = solvePicture;
    }

    public String getSolvePicture() 
    {
        return solvePicture;
    }
    public void setSolveVideo(String solveVideo) 
    {
        this.solveVideo = solveVideo;
    }

    public String getSolveVideo() 
    {
        return solveVideo;
    }
    public void setSolveResult(String solveResult) 
    {
        this.solveResult = solveResult;
    }

    public String getSolveResult() 
    {
        return solveResult;
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
            .append("repairNo", getRepairNo())
            .append("deviceNo", getDeviceNo())
            .append("roomNo", getRoomNo())
            .append("userNo", getUserNo())
            .append("userName", getUserName())
            .append("userTel", getUserTel())
            .append("repairTitle", getRepairTitle())
            .append("repairType", getRepairType())
            .append("repairDesc", getRepairDesc())
            .append("repairPicture", getRepairPicture())
            .append("repairVideo", getRepairVideo())
            .append("repairDate", getRepairDate())
            .append("solveState", getSolveState())
            .append("solveUser", getSolveUser())
            .append("solveDate", getSolveDate())
            .append("solvePicture", getSolvePicture())
            .append("solveVideo", getSolveVideo())
            .append("solveResult", getSolveResult())
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
