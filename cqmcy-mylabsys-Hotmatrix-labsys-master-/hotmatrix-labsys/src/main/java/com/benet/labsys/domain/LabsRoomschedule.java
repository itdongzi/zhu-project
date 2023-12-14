package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 实验室排课对象 labs_roomschedule
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsRoomschedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 排课编号 */
    @Excel(name = "排课编号")
    private String schdNo;

    /** 预约编号 */
    @Excel(name = "预约编号")
    private String orderNo;

    /** 实验室编号 */
    @Excel(name = "实验室编号")
    private String roomNo;

    /** 学年学期编号 */
    @Excel(name = "学年学期编号")
    private String semeNo;

    /** 排课类型(1:行政排课，2:暂时排课，3:预约排课) */
    @Excel(name = "排课类型(1:行政排课，2:暂时排课，3:预约排课)")
    private String schdType;

    /** 排课信息（第几周+第几节） */
    @Excel(name = "排课信息", readConverterExp = "第=几周+第几节")
    private String schdValue;

    /** 使用信息（课程名称+教师名称+班级信息） */
    @Excel(name = "使用信息", readConverterExp = "课=程名称+教师名称+班级信息")
    private String usesValue;

    /** 学年学期信息 */
    @Excel(name = "学年学期信息")
    private String xnxqbh;

    /** 开课周次 */
    @Excel(name = "开课周次")
    private String kkzc;

    /** 单双周标志 */
    @Excel(name = "单双周标志")
    private String dszbz;

    /** 教学节次编号 */
    @Excel(name = "教学节次编号")
    private String jxjcbh;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String dwmc;

    /** 教职工号 */
    @Excel(name = "教职工号")
    private String jzgh;

    /** 教职工姓名 */
    @Excel(name = "教职工姓名")
    private String xm;

    /** 课程代码 */
    @Excel(name = "课程代码")
    private String kcdm;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String kcmc;

    /** 开课合班编号 */
    @Excel(name = "开课合班编号")
    private String kkhbxxbh;

    /** 合班名称 */
    @Excel(name = "合班名称")
    private String hbmc;

    /** 班级代码 */
    @Excel(name = "班级代码")
    private String bjdm;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String bjmc;

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
    public void setRoomNo(String roomNo) 
    {
        this.roomNo = roomNo;
    }

    public String getRoomNo() 
    {
        return roomNo;
    }
    public void setSemeNo(String semeNo) 
    {
        this.semeNo = semeNo;
    }

    public String getSemeNo() 
    {
        return semeNo;
    }
    public void setSchdType(String schdType) 
    {
        this.schdType = schdType;
    }

    public String getSchdType() 
    {
        return schdType;
    }
    public void setSchdValue(String schdValue) 
    {
        this.schdValue = schdValue;
    }

    public String getSchdValue() 
    {
        return schdValue;
    }
    public void setUsesValue(String usesValue) 
    {
        this.usesValue = usesValue;
    }

    public String getUsesValue() 
    {
        return usesValue;
    }
    public void setXnxqbh(String xnxqbh) 
    {
        this.xnxqbh = xnxqbh;
    }

    public String getXnxqbh() 
    {
        return xnxqbh;
    }
    public void setKkzc(String kkzc) 
    {
        this.kkzc = kkzc;
    }

    public String getKkzc() 
    {
        return kkzc;
    }
    public void setDszbz(String dszbz) 
    {
        this.dszbz = dszbz;
    }

    public String getDszbz() 
    {
        return dszbz;
    }
    public void setJxjcbh(String jxjcbh) 
    {
        this.jxjcbh = jxjcbh;
    }

    public String getJxjcbh() 
    {
        return jxjcbh;
    }
    public void setDwmc(String dwmc) 
    {
        this.dwmc = dwmc;
    }

    public String getDwmc() 
    {
        return dwmc;
    }
    public void setJzgh(String jzgh) 
    {
        this.jzgh = jzgh;
    }

    public String getJzgh() 
    {
        return jzgh;
    }
    public void setXm(String xm) 
    {
        this.xm = xm;
    }

    public String getXm() 
    {
        return xm;
    }
    public void setKcdm(String kcdm) 
    {
        this.kcdm = kcdm;
    }

    public String getKcdm() 
    {
        return kcdm;
    }
    public void setKcmc(String kcmc) 
    {
        this.kcmc = kcmc;
    }

    public String getKcmc() 
    {
        return kcmc;
    }
    public void setKkhbxxbh(String kkhbxxbh) 
    {
        this.kkhbxxbh = kkhbxxbh;
    }

    public String getKkhbxxbh() 
    {
        return kkhbxxbh;
    }
    public void setHbmc(String hbmc) 
    {
        this.hbmc = hbmc;
    }

    public String getHbmc() 
    {
        return hbmc;
    }
    public void setBjdm(String bjdm) 
    {
        this.bjdm = bjdm;
    }

    public String getBjdm() 
    {
        return bjdm;
    }
    public void setBjmc(String bjmc) 
    {
        this.bjmc = bjmc;
    }

    public String getBjmc() 
    {
        return bjmc;
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
            .append("roomNo", getRoomNo())
            .append("semeNo", getSemeNo())
            .append("schdType", getSchdType())
            .append("schdValue", getSchdValue())
            .append("usesValue", getUsesValue())
            .append("xnxqbh", getXnxqbh())
            .append("kkzc", getKkzc())
            .append("dszbz", getDszbz())
            .append("jxjcbh", getJxjcbh())
            .append("dwmc", getDwmc())
            .append("jzgh", getJzgh())
            .append("xm", getXm())
            .append("kcdm", getKcdm())
            .append("kcmc", getKcmc())
            .append("kkhbxxbh", getKkhbxxbh())
            .append("hbmc", getHbmc())
            .append("bjdm", getBjdm())
            .append("bjmc", getBjmc())
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
