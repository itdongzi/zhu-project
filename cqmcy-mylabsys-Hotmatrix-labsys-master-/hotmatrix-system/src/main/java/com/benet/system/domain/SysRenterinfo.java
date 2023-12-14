package com.benet.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 租户信息对象 sys_appinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysRenterinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 分支编号 */
    @Excel(name = "租户编号")
    private String rentNo;

    /** 应用中文名称 */
    @Excel(name = "租户中文名称")
    private String rcnname;

    /** 应用英文名称 */
    @Excel(name = "租户英文名称")
    private String renname;

    /** 类型编号 */
    @Excel(name = "类型编号")
    private String classNo;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

    /** 服务Url */
    @Excel(name = "服务Url")
    private String appUrl;

    /** 服务版本 */
    @Excel(name = "服务版本")
    private String appVer;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String telephone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 简介 */
    @Excel(name = "简介")
    private String summary;

    /** 加密狗id */
    @Excel(name = "加密狗id")
    private String edogNo;

    /** 加密狗类型 */
    @Excel(name = "加密狗类型")
    private String edogType;

    /** 注册时间 */
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registDate;

    /** 有效时间 */
    @Excel(name = "有效时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeDate;

    /** 授权数量 */
    @Excel(name = "授权数量")
    private Long activeCount;

    /** 授权码 */
    @Excel(name = "授权码")
    private String activeCode;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 删除标志（1代表存在 0代表删除） */
    @Excel(name = "删除标志", readConverterExp = "1=代表存在,0=代表删除")
    private String deleteFlag;

    /** 更新者 */
    @Excel(name = "更新者")
    private String comments;

    /** 应用编号 */
    @Excel(name = "应用编号")
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

    public String getRentNo() {
        return rentNo;
    }

    public void setRentNo(String rentNo) {
        this.rentNo = rentNo;
    }

    public String getRcnname() {
        return rcnname;
    }

    public void setRcnname(String rcnname) {
        this.rcnname = rcnname;
    }

    public String getRenname() {
        return renname;
    }

    public void setRenname(String renname) {
        this.renname = renname;
    }

    public void setClassNo(String classNo)
    {
        this.classNo = classNo;
    }

    public String getClassNo()
    {
        return classNo;
    }
    public void setOrderNo(Integer orderNo)
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo()
    {
        return orderNo;
    }
    public void setAppUrl(String appUrl)
    {
        this.appUrl = appUrl;
    }

    public String getAppUrl()
    {
        return appUrl;
    }
    public void setAppVer(String appVer)
    {
        this.appVer = appVer;
    }

    public String getAppVer()
    {
        return appVer;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getSummary()
    {
        return summary;
    }
    public void setEdogNo(String edogNo)
    {
        this.edogNo = edogNo;
    }

    public String getEdogNo()
    {
        return edogNo;
    }
    public void setEdogType(String edogType)
    {
        this.edogType = edogType;
    }

    public String getEdogType()
    {
        return edogType;
    }
    public void setRegistDate(LocalDateTime registDate)
    {
        this.registDate = registDate;
    }

    public LocalDateTime getRegistDate()
    {
        return registDate;
    }
    public void setActiveDate(LocalDateTime activeDate)
    {
        this.activeDate = activeDate;
    }

    public LocalDateTime getActiveDate()
    {
        return activeDate;
    }
    public void setActiveCount(Long activeCount)
    {
        this.activeCount = activeCount;
    }

    public Long getActiveCount()
    {
        return activeCount;
    }
    public void setActiveCode(String activeCode)
    {
        this.activeCode = activeCode;
    }

    public String getActiveCode()
    {
        return activeCode;
    }
    public void setCheckState(String checkState)
    {
        this.checkState = checkState;
    }

    public String getCheckState()
    {
        return checkState;
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
            .append("rentNo", getRentNo())
            .append("rcnname", getRcnname())
            .append("renname", getRenname())
            .append("classNo", getClassNo())
            .append("orderNo", getOrderNo())
            .append("appUrl", getAppUrl())
            .append("appVer", getAppVer())
                .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("summary", getSummary())
            .append("edogNo", getEdogNo())
            .append("edogType", getEdogType())
            .append("registDate", getRegistDate())
            .append("activeDate", getActiveDate())
            .append("activeCount", getActiveCount())
            .append("activeCode", getActiveCode())
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
