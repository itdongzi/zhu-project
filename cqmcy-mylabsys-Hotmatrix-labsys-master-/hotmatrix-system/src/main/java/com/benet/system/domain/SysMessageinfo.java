package com.benet.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 消息信息对象 sys_messageinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysMessageinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 消息id */
    @Excel(name = "消息id")
    private String mssgNo;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String mssgType;

    /** 接收人 */
    @Excel(name = "接收人")
    private String mreceiver;

    /** 发送人 */
    @Excel(name = "发送人")
    private String msender;

    /** 消息标题 */
    @Excel(name = "消息标题")
    private String mtitle;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String mcontent;

    /** 附件 */
    @Excel(name = "附件")
    private String attachfile;

    /** 阅读状态（1已阅 0未阅） */
    @Excel(name = "阅读状态", readConverterExp = "1=已阅,0=未阅")
    private String readState;

    /** 接收时间 */
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

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
    public void setMssgNo(String mssgNo) 
    {
        this.mssgNo = mssgNo;
    }

    public String getMssgNo() 
    {
        return mssgNo;
    }
    public void setMssgType(String mssgType) 
    {
        this.mssgType = mssgType;
    }

    public String getMssgType() 
    {
        return mssgType;
    }
    public void setMreceiver(String mreceiver) 
    {
        this.mreceiver = mreceiver;
    }

    public String getMreceiver() 
    {
        return mreceiver;
    }
    public void setMsender(String msender) 
    {
        this.msender = msender;
    }

    public String getMsender() 
    {
        return msender;
    }
    public void setMtitle(String mtitle) 
    {
        this.mtitle = mtitle;
    }

    public String getMtitle() 
    {
        return mtitle;
    }
    public void setMcontent(String mcontent) 
    {
        this.mcontent = mcontent;
    }

    public String getMcontent() 
    {
        return mcontent;
    }
    public void setAttachfile(String attachfile) 
    {
        this.attachfile = attachfile;
    }

    public String getAttachfile() 
    {
        return attachfile;
    }
    public void setReadState(String readState) 
    {
        this.readState = readState;
    }

    public String getReadState() 
    {
        return readState;
    }
    public void setReceiveTime(LocalDateTime receiveTime) 
    {
        this.receiveTime = receiveTime;
    }

    public LocalDateTime getReceiveTime() 
    {
        return receiveTime;
    }
    public void setSendTime(LocalDateTime sendTime) 
    {
        this.sendTime = sendTime;
    }

    public LocalDateTime getSendTime() 
    {
        return sendTime;
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
            .append("mssgNo", getMssgNo())
            .append("mssgType", getMssgType())
            .append("mreceiver", getMreceiver())
            .append("msender", getMsender())
            .append("mtitle", getMtitle())
            .append("mcontent", getMcontent())
            .append("attachfile", getAttachfile())
            .append("readState", getReadState())
            .append("receiveTime", getReceiveTime())
            .append("sendTime", getSendTime())
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
