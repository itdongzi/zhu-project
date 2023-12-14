package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 注册用户排班对象 sys_ruserrota
 * 
 * @author yoxking
 * @date 2020-10-27
 */
public class SysRuserrota extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 排班ID */
    @Excel(name = "排班ID")
    private String rotaNo;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userNo;

    /** 1正常排班 2临时排班 */
    @Excel(name = "1正常排班 2临时排班")
    private String rotaType;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime enditTime;

    /** 周一(0休息 1上午 2下午 3全天) */
    @Excel(name = "周一(0休息 1上午 2下午 3全天)")
    private String weekDay1;

    /** 周二(0休息 1上午 2下午 3全天) */
    @Excel(name = "周二(0休息 1上午 2下午 3全天)")
    private String weekDay2;

    /** 周三(0休息 1上午 2下午 3全天) */
    @Excel(name = "周三(0休息 1上午 2下午 3全天)")
    private String weekDay3;

    /** 周四(0休息 1上午 2下午 3全天) */
    @Excel(name = "周四(0休息 1上午 2下午 3全天)")
    private String weekDay4;

    /** 周五(0休息 1上午 2下午 3全天) */
    @Excel(name = "周五(0休息 1上午 2下午 3全天)")
    private String weekDay5;

    /** 周六(0休息 1上午 2下午 3全天) */
    @Excel(name = "周六(0休息 1上午 2下午 3全天)")
    private String weekDay6;

    /** 周日(0休息 1上午 2下午 3全天) */
    @Excel(name = "周日(0休息 1上午 2下午 3全天)")
    private String weekDay7;

    /** 排班格式 */
    @Excel(name = "排班格式")
    private String rotaFormat;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

    /** 删除标志（1代表存在 0代表删除） */
    @Excel(name = "删除标志", readConverterExp = "1=代表存在,0=代表删除")
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
    public void setRotaNo(String rotaNo) 
    {
        this.rotaNo = rotaNo;
    }

    public String getRotaNo() 
    {
        return rotaNo;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setRotaType(String rotaType) 
    {
        this.rotaType = rotaType;
    }

    public String getRotaType() 
    {
        return rotaType;
    }
    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime()
    {
        return startTime;
    }
    public void setEnditTime(LocalDateTime enditTime)
    {
        this.enditTime = enditTime;
    }

    public LocalDateTime getEnditTime()
    {
        return enditTime;
    }
    public void setWeekDay1(String weekDay1) 
    {
        this.weekDay1 = weekDay1;
    }

    public String getWeekDay1() 
    {
        return weekDay1;
    }
    public void setWeekDay2(String weekDay2) 
    {
        this.weekDay2 = weekDay2;
    }

    public String getWeekDay2() 
    {
        return weekDay2;
    }
    public void setWeekDay3(String weekDay3) 
    {
        this.weekDay3 = weekDay3;
    }

    public String getWeekDay3() 
    {
        return weekDay3;
    }
    public void setWeekDay4(String weekDay4) 
    {
        this.weekDay4 = weekDay4;
    }

    public String getWeekDay4() 
    {
        return weekDay4;
    }
    public void setWeekDay5(String weekDay5) 
    {
        this.weekDay5 = weekDay5;
    }

    public String getWeekDay5() 
    {
        return weekDay5;
    }
    public void setWeekDay6(String weekDay6) 
    {
        this.weekDay6 = weekDay6;
    }

    public String getWeekDay6() 
    {
        return weekDay6;
    }
    public void setWeekDay7(String weekDay7) 
    {
        this.weekDay7 = weekDay7;
    }

    public String getWeekDay7() 
    {
        return weekDay7;
    }
    public void setRotaFormat(String rotaFormat) 
    {
        this.rotaFormat = rotaFormat;
    }

    public String getRotaFormat() 
    {
        return rotaFormat;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
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
            .append("rotaNo", getRotaNo())
            .append("userNo", getUserNo())
            .append("rotaType", getRotaType())
            .append("startTime", getStartTime())
            .append("enditTime", getEnditTime())
            .append("weekDay1", getWeekDay1())
            .append("weekDay2", getWeekDay2())
            .append("weekDay3", getWeekDay3())
            .append("weekDay4", getWeekDay4())
            .append("weekDay5", getWeekDay5())
            .append("weekDay6", getWeekDay6())
            .append("weekDay7", getWeekDay7())
            .append("rotaFormat", getRotaFormat())
            .append("checkState", getCheckState())
            .append("branchNo", getBranchNo())
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
