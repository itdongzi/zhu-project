package com.benet.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

/**
 * 定时任务调度对象 sys_taskinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysTaskinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskNo;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务组名 */
    @Excel(name = "任务组名")
    private String taskGroup;

    /** cron执行表达式 */
    @Excel(name = "cron执行表达式")
    private String taskExpress;

    /** 调用目标字符串 */
    @Excel(name = "调用目标字符串")
    private String invokeTarget;

    /** 计划执行错误策略（1立即执行 2执行一次 3放弃执行） */
    @Excel(name = "计划执行错误策略", readConverterExp = "1=立即执行,2=执行一次,3=放弃执行")
    private String errorsPolicy;

    /** 是否并发执行（0允许 1禁止） */
    @Excel(name = "是否并发执行", readConverterExp = "0=允许,1=禁止")
    private String concurrent;

    /** 状态（1正常 0停用） */
    @Excel(name = "运行状态", readConverterExp = "1=运行,0=停止")
    private String taskStatus;

    /** 状态（1正常 0停用） */
    @Excel(name = "审核状态", readConverterExp = "1=正常,0=停用")
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
    public void setTaskNo(String taskNo) 
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo() 
    {
        return taskNo;
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

    public String getTaskExpress() {
        return taskExpress;
    }

    public void setTaskExpress(String taskExpress) {
        this.taskExpress = taskExpress;
    }
    public void setInvokeTarget(String invokeTarget) 
    {
        this.invokeTarget = invokeTarget;
    }

    public String getInvokeTarget() 
    {
        return invokeTarget;
    }

    public String getErrorsPolicy() {
        return errorsPolicy;
    }

    public void setErrorsPolicy(String errorsPolicy) {
        this.errorsPolicy = errorsPolicy;
    }

    public void setConcurrent(String concurrent)
    {
        this.concurrent = concurrent;
    }

    public String getConcurrent() 
    {
        return concurrent;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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
            .append("taskNo", getTaskNo())
            .append("taskName", getTaskName())
            .append("taskGroup", getTaskGroup())
                .append("taskExpress", getTaskExpress())
            .append("invokeTarget", getInvokeTarget())
            .append("errorsPolicy", getErrorsPolicy())
            .append("concurrent", getConcurrent())
                .append("taskStatus", getTaskStatus())
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
