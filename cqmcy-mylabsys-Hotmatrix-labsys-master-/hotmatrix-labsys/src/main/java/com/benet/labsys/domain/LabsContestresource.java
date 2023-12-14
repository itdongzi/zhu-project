package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 竞赛作品信息对象 labs_contestresource
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:30
 */
public class LabsContestresource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String rescNo;

    /** 竞赛编号 */
    @Excel(name = "竞赛编号")
    private String contentNo;

    /** 人员编号 */
    @Excel(name = "人员编号")
    private String userNo;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String classNo;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String rescTitle;

    /** 资源地址 */
    @Excel(name = "资源地址")
    private String rescUrl;

    /** 文件扩展名 */
    @Excel(name = "文件扩展名")
    private String rescExt;

    /** 资源描述 */
    @Excel(name = "资源描述")
    private String rescDesc;

    /** 资源上传时间 */
    @Excel(name = "资源上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rescDate;

    /** 资源总分 */
    @Excel(name = "资源总分")
    private Long rescScore;

    /** 状态（1通过 2未通过） */
    @Excel(name = "状态", readConverterExp = "1=通过,2=未通过")
    private String rescState;

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
    public void setRescNo(String rescNo) 
    {
        this.rescNo = rescNo;
    }

    public String getRescNo() 
    {
        return rescNo;
    }
    public void setContentNo(String contentNo) 
    {
        this.contentNo = contentNo;
    }

    public String getContentNo() 
    {
        return contentNo;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setRescTitle(String rescTitle) 
    {
        this.rescTitle = rescTitle;
    }

    public String getRescTitle() 
    {
        return rescTitle;
    }
    public void setRescUrl(String rescUrl) 
    {
        this.rescUrl = rescUrl;
    }

    public String getRescUrl() 
    {
        return rescUrl;
    }
    public void setRescExt(String rescExt) 
    {
        this.rescExt = rescExt;
    }

    public String getRescExt() 
    {
        return rescExt;
    }
    public void setRescDesc(String rescDesc) 
    {
        this.rescDesc = rescDesc;
    }

    public String getRescDesc() 
    {
        return rescDesc;
    }
    public void setRescDate(LocalDateTime rescDate) 
    {
        this.rescDate = rescDate;
    }

    public LocalDateTime getRescDate() 
    {
        return rescDate;
    }
    public void setRescScore(Long rescScore) 
    {
        this.rescScore = rescScore;
    }

    public Long getRescScore() 
    {
        return rescScore;
    }
    public void setRescState(String rescState) 
    {
        this.rescState = rescState;
    }

    public String getRescState() 
    {
        return rescState;
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
            .append("rescNo", getRescNo())
            .append("contentNo", getContentNo())
            .append("userNo", getUserNo())
            .append("classNo", getClassNo())
            .append("rescTitle", getRescTitle())
            .append("rescUrl", getRescUrl())
            .append("rescExt", getRescExt())
            .append("rescDesc", getRescDesc())
            .append("rescDate", getRescDate())
            .append("rescScore", getRescScore())
            .append("rescState", getRescState())
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
