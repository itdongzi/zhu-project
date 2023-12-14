package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 安全学习资料对象 labs_safetyfile
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsSafetyfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String fileNo;

    /** 资料分类编号 */
    @Excel(name = "资料分类编号")
    private String classNo;

    /** 资料名称 */
    @Excel(name = "资料名称")
    private String fileTitle;

    /** 类别（1必学 2选学） */
    @Excel(name = "类别", readConverterExp = "1=必学,2=选学")
    private String fileType;

    /** 安全类别 */
    @Excel(name = "安全类别")
    private String safeClass;

    /** 资料格式（文本、视频） */
    @Excel(name = "资料格式", readConverterExp = "文=本、视频")
    private String fileFormat;

    /** 访问地址 */
    @Excel(name = "访问地址")
    private String fileUrl;

    /** 资料简介 */
    @Excel(name = "资料简介")
    private String fileDesc;

    /** 共享类型（1公开 2私有） */
    @Excel(name = "共享类型", readConverterExp = "1=公开,2=私有")
    private String shareType;

    /** 审核状态（1未审核 2已审核） */
    @Excel(name = "审核状态", readConverterExp = "1=未审核,2=已审核")
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
    public void setFileNo(String fileNo) 
    {
        this.fileNo = fileNo;
    }

    public String getFileNo() 
    {
        return fileNo;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setFileTitle(String fileTitle) 
    {
        this.fileTitle = fileTitle;
    }

    public String getFileTitle() 
    {
        return fileTitle;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setSafeClass(String safeClass) 
    {
        this.safeClass = safeClass;
    }

    public String getSafeClass() 
    {
        return safeClass;
    }
    public void setFileFormat(String fileFormat) 
    {
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() 
    {
        return fileFormat;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setFileDesc(String fileDesc) 
    {
        this.fileDesc = fileDesc;
    }

    public String getFileDesc() 
    {
        return fileDesc;
    }
    public void setShareType(String shareType) 
    {
        this.shareType = shareType;
    }

    public String getShareType() 
    {
        return shareType;
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
            .append("fileNo", getFileNo())
            .append("classNo", getClassNo())
            .append("fileTitle", getFileTitle())
            .append("fileType", getFileType())
            .append("safeClass", getSafeClass())
            .append("fileFormat", getFileFormat())
            .append("fileUrl", getFileUrl())
            .append("fileDesc", getFileDesc())
            .append("shareType", getShareType())
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
