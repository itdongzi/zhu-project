package com.benet.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 定时任务调度日志对象 sys_tasklogs
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysTaskelog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 任务日志ID */
    @Excel(name = "任务日志ID")
    private String taskLogno;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务组名 */
    @Excel(name = "任务组名")
    private String taskGroup;

    /** 日志信息 */
    @Excel(name = "日志信息")
    private String taskMessage;

    /** 调用目标字符串 */
    @Excel(name = "调用目标字符串")
    private String invokeTarget;

    /** 执行状态（0正常 1失败） */
    @Excel(name = "执行状态", readConverterExp = "0=正常,1=失败")
    private String resultStatus;

    /** 异常信息 */
    @Excel(name = "异常信息")
    private String errorsMessage;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime startTime;

    /** 完成时间 */
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime enditTime;

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
    public void setTaskLogno(String taskLogno) 
    {
        this.taskLogno = taskLogno;
    }

    public String getTaskLogno() 
    {
        return taskLogno;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskGroup(String taskGroup) 
    {
        this.taskGroup = taskGroup;
    }

    public String getTaskGroup() 
    {
        return taskGroup;
    }
    public void setInvokeTarget(String invokeTarget) 
    {
        this.invokeTarget = invokeTarget;
    }

    public String getInvokeTarget() 
    {
        return invokeTarget;
    }
    public void setTaskMessage(String taskMessage) 
    {
        this.taskMessage = taskMessage;
    }

    public String getTaskMessage() 
    {
        return taskMessage;
    }
    public void setResultStatus(String resultStatus) 
    {
        this.resultStatus = resultStatus;
    }

    public String getResultStatus() 
    {
        return resultStatus;
    }

    public String getErrorsMessage() {
        return errorsMessage;
    }

    public void setErrorsMessage(String errorsMessage) {
        this.errorsMessage = errorsMessage;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEnditTime() {
        return enditTime;
    }

    public void setEnditTime(LocalDateTime enditTime) {
        this.enditTime = enditTime;
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
            .append("taskLogno", getTaskLogno())
            .append("taskName", getTaskName())
            .append("taskGroup", getTaskGroup())
            .append("invokeTarget", getInvokeTarget())
            .append("taskMessage", getTaskMessage())
            .append("resultStatus", getResultStatus())
                .append("errorsMessage", getErrorsMessage())
                .append("startTime", getStartTime())
                .append("enditTime", getEnditTime())
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
