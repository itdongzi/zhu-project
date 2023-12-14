package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 设备控制过程对象 labs_deviceflows
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsDeviceflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流水编号 */
    @Excel(name = "流水编号")
    private String dflowNo;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceNo;

    /** 操作时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime opertDtime;

    /** 操作人 */
    @Excel(name = "操作人")
    private String opertUsrno;

    /** 操作内容 */
    @Excel(name = "操作内容")
    private String opertValue;

    /** 操作结果 */
    @Excel(name = "操作结果")
    private String opertResult;

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
    public void setDflowNo(String dflowNo) 
    {
        this.dflowNo = dflowNo;
    }

    public String getDflowNo() 
    {
        return dflowNo;
    }
    public void setDeviceNo(String deviceNo) 
    {
        this.deviceNo = deviceNo;
    }

    public String getDeviceNo() 
    {
        return deviceNo;
    }
    public void setOpertDtime(LocalDateTime opertDtime) 
    {
        this.opertDtime = opertDtime;
    }

    public LocalDateTime getOpertDtime() 
    {
        return opertDtime;
    }
    public void setOpertUsrno(String opertUsrno) 
    {
        this.opertUsrno = opertUsrno;
    }

    public String getOpertUsrno() 
    {
        return opertUsrno;
    }
    public void setOpertValue(String opertValue) 
    {
        this.opertValue = opertValue;
    }

    public String getOpertValue() 
    {
        return opertValue;
    }
    public void setOpertResult(String opertResult) 
    {
        this.opertResult = opertResult;
    }

    public String getOpertResult() 
    {
        return opertResult;
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
            .append("dflowNo", getDflowNo())
            .append("deviceNo", getDeviceNo())
            .append("opertDtime", getOpertDtime())
            .append("opertUsrno", getOpertUsrno())
            .append("opertValue", getOpertValue())
            .append("opertResult", getOpertResult())
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
