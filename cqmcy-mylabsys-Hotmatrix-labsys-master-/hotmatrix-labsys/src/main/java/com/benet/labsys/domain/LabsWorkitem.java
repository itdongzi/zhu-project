package com.benet.labsys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 实训室工作/检查项目对象 labs_workitem
 * 
 * @author yoxking
 * @date 2023-01-29 09:55:31
 */
public class LabsWorkitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String itemNo;

    /** 工作/检查编号 */
    @Excel(name = "工作/检查编号")
    private String workNo;

    /** 检查类别(1:领导巡查,2:管理员自查,3:随手举报,4:卫生检查,5:安全检查,6:假期检查) */
    @Excel(name = "检查类别(1:领导巡查,2:管理员自查,3:随手举报,4:卫生检查,5:安全检查,6:假期检查)")
    private String dataType;

    /** 检查项目名称 */
    @Excel(name = "检查项目名称")
    private String itemTitle;

    /** 检查要点 */
    @Excel(name = "检查要点")
    private String itemPoint;

    /** 检查项目描述 */
    @Excel(name = "检查项目描述")
    private String itemDesc;

    /** 检查项目结果 */
    @Excel(name = "检查项目结果")
    private String itemResult;

    /** 检查时间 */
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime itemDate;

    /** 检查得分 */
    @Excel(name = "检查得分")
    private Long itemScore;

    /** 状态（1符合要求 2不符合要求 3需整改） */
    @Excel(name = "状态", readConverterExp = "1=符合要求,2=不符合要求,3=需整改")
    private String itemState;

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
    public void setItemNo(String itemNo) 
    {
        this.itemNo = itemNo;
    }

    public String getItemNo() 
    {
        return itemNo;
    }
    public void setWorkNo(String workNo) 
    {
        this.workNo = workNo;
    }

    public String getWorkNo() 
    {
        return workNo;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setItemTitle(String itemTitle) 
    {
        this.itemTitle = itemTitle;
    }

    public String getItemTitle() 
    {
        return itemTitle;
    }
    public void setItemPoint(String itemPoint) 
    {
        this.itemPoint = itemPoint;
    }

    public String getItemPoint() 
    {
        return itemPoint;
    }
    public void setItemDesc(String itemDesc) 
    {
        this.itemDesc = itemDesc;
    }

    public String getItemDesc() 
    {
        return itemDesc;
    }
    public void setItemResult(String itemResult) 
    {
        this.itemResult = itemResult;
    }

    public String getItemResult() 
    {
        return itemResult;
    }
    public void setItemDate(LocalDateTime itemDate) 
    {
        this.itemDate = itemDate;
    }

    public LocalDateTime getItemDate() 
    {
        return itemDate;
    }
    public void setItemScore(Long itemScore) 
    {
        this.itemScore = itemScore;
    }

    public Long getItemScore() 
    {
        return itemScore;
    }
    public void setItemState(String itemState) 
    {
        this.itemState = itemState;
    }

    public String getItemState() 
    {
        return itemState;
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
            .append("itemNo", getItemNo())
            .append("workNo", getWorkNo())
            .append("dataType", getDataType())
            .append("itemTitle", getItemTitle())
            .append("itemPoint", getItemPoint())
            .append("itemDesc", getItemDesc())
            .append("itemResult", getItemResult())
            .append("itemDate", getItemDate())
            .append("itemScore", getItemScore())
            .append("itemState", getItemState())
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
