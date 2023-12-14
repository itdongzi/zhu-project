package com.benet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 操作日志记录对象 sys_operatelogs
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysOperatelog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 日志主键 */
    @Excel(name = "日志主键")
    private String oplogNo;

    /** 模块标题 */
    @Excel(name = "模块标题")
    private String oplogTitle;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    @Excel(name = "业务类型", readConverterExp = "0=其它,1=新增,2=修改,3=删除")
    private String oplogType;

    /** 方法名称 */
    @Excel(name = "方法名称")
    private String methodName;

    /** 请求方式 */
    @Excel(name = "请求方式")
    private String requestType;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    @Excel(name = "操作类别", readConverterExp = "0=其它,1=后台用户,2=手机端用户")
    private String opertType;

    /** 操作人员 */
    @Excel(name = "操作人员")
    private String opertName;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 请求URL */
    @Excel(name = "请求URL")
    private String opertUrl;

    /** 主机地址 */
    @Excel(name = "主机地址")
    private String opertIp;

    /** 操作地点 */
    @Excel(name = "操作地点")
    private String opertLocation;

    /** 请求参数 */
    @Excel(name = "请求参数")
    private String opertParams;

    /** 返回参数 */
    @Excel(name = "返回参数")
    private String jsonResult;

    /** 操作状态（0正常 1异常） */
    @Excel(name = "操作状态", readConverterExp = "0=正常,1=异常")
    private String opertStatus;

    /** 错误消息 */
    @Excel(name = "错误消息")
    private String errorMsg;

    /** 操作时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime opertTime;

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
    public void setOplogNo(String oplogNo) 
    {
        this.oplogNo = oplogNo;
    }

    public String getOplogNo() 
    {
        return oplogNo;
    }
    public void setOplogType(String oplogType) 
    {
        this.oplogType = oplogType;
    }

    public String getOplogType() 
    {
        return oplogType;
    }

    public String getOplogTitle() {
        return oplogTitle;
    }

    public void setOplogTitle(String oplogTitle) {
        this.oplogTitle = oplogTitle;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getMethodName() 
    {
        return methodName;
    }
    public void setRequestType(String requestType) 
    {
        this.requestType = requestType;
    }

    public String getRequestType() 
    {
        return requestType;
    }
    public void setOpertType(String opertType) 
    {
        this.opertType = opertType;
    }

    public String getOpertType() 
    {
        return opertType;
    }
    public void setOpertName(String opertName) 
    {
        this.opertName = opertName;
    }

    public String getOpertName() 
    {
        return opertName;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setOpertUrl(String opertUrl) 
    {
        this.opertUrl = opertUrl;
    }

    public String getOpertUrl() 
    {
        return opertUrl;
    }
    public void setOpertIp(String opertIp) 
    {
        this.opertIp = opertIp;
    }

    public String getOpertIp() 
    {
        return opertIp;
    }
    public void setOpertLocation(String opertLocation) 
    {
        this.opertLocation = opertLocation;
    }

    public String getOpertLocation() 
    {
        return opertLocation;
    }
    public void setOpertParams(String opertParams) 
    {
        this.opertParams = opertParams;
    }

    public String getOpertParams() 
    {
        return opertParams;
    }
    public void setJsonResult(String jsonResult) 
    {
        this.jsonResult = jsonResult;
    }

    public String getJsonResult() 
    {
        return jsonResult;
    }
    public void setOpertStatus(String opertStatus) 
    {
        this.opertStatus = opertStatus;
    }

    public String getOpertStatus() 
    {
        return opertStatus;
    }
    public void setErrorMsg(String errorMsg) 
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() 
    {
        return errorMsg;
    }
    public void setOpertTime(LocalDateTime opertTime) 
    {
        this.opertTime = opertTime;
    }

    public LocalDateTime getOpertTime() 
    {
        return opertTime;
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
            .append("oplogNo", getOplogNo())
            .append("oplogType", getOplogType())
            .append("title", getOplogTitle())
            .append("methodName", getMethodName())
            .append("requestType", getRequestType())
            .append("opertType", getOpertType())
            .append("opertName", getOpertName())
            .append("deptName", getDeptName())
            .append("opertUrl", getOpertUrl())
            .append("opertIp", getOpertIp())
            .append("opertLocation", getOpertLocation())
            .append("opertParams", getOpertParams())
            .append("jsonResult", getJsonResult())
            .append("opertStatus", getOpertStatus())
            .append("errorMsg", getErrorMsg())
            .append("opertTime", getOpertTime())
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
