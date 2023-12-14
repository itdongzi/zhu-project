package com.benet.record.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 库存信息对象 cssd_stockinfo
 * 
 * @author yoxking
 * @date 2022-10-02 09:24:00
 */
public class CssdStockinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 库存id */
    @Excel(name = "库存id")
    private String stockNo;

    /** 库房id */
    @Excel(name = "库房id")
    private String storeNo;

    /** 器材编号 */
    @Excel(name = "器材编号")
    private String equipNo;

    /** 类别（1器械物品 2耗材物品） */
    @Excel(name = "类别", readConverterExp = "1=器械物品,2=耗材物品")
    private String dataType;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Integer equipNum;

    /** 生产批次号 */
    @Excel(name = "生产批次号")
    private String produceNumber;

    /** 生产日期 */
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime produceDate;

    /** 失效日期 */
    @Excel(name = "失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireDate;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 科室编号 */
    @Excel(name = "科室编号")
    private String branchNo;

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
    public void setStockNo(String stockNo) 
    {
        this.stockNo = stockNo;
    }

    public String getStockNo() 
    {
        return stockNo;
    }
    public void setStoreNo(String storeNo) 
    {
        this.storeNo = storeNo;
    }

    public String getStoreNo() 
    {
        return storeNo;
    }
    public void setEquipNo(String equipNo) 
    {
        this.equipNo = equipNo;
    }

    public String getEquipNo() 
    {
        return equipNo;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setEquipNum(Integer equipNum) 
    {
        this.equipNum = equipNum;
    }

    public Integer getEquipNum() 
    {
        return equipNum;
    }
    public void setProduceNumber(String produceNumber) 
    {
        this.produceNumber = produceNumber;
    }

    public String getProduceNumber() 
    {
        return produceNumber;
    }
    public void setProduceDate(LocalDateTime produceDate) 
    {
        this.produceDate = produceDate;
    }

    public LocalDateTime getProduceDate() 
    {
        return produceDate;
    }
    public void setExpireDate(LocalDateTime expireDate) 
    {
        this.expireDate = expireDate;
    }

    public LocalDateTime getExpireDate() 
    {
        return expireDate;
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
            .append("stockNo", getStockNo())
            .append("storeNo", getStoreNo())
            .append("equipNo", getEquipNo())
            .append("dataType", getDataType())
            .append("equipNum", getEquipNum())
            .append("produceNumber", getProduceNumber())
            .append("produceDate", getProduceDate())
            .append("expireDate", getExpireDate())
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
