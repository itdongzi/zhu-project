package com.benet.record.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 出入库单明细对象 cssd_stockitems
 * 
 * @author yoxking
 * @date 2022-10-02 09:24:00
 */
public class CssdStockitems extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 明细编号 */
    @Excel(name = "明细编号")
    private String sitemNo;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String sflowNo;

    /** 器械id */
    @Excel(name = "器械id")
    private String equipNo;

    /** 类别（1器械物品 2耗材物品） */
    @Excel(name = "类别", readConverterExp = "1=器械物品,2=耗材物品")
    private String dataType;

    /** 设备数量 */
    @Excel(name = "设备数量")
    private Integer equipNum;

    /** 单价 */
    @Excel(name = "单价")
    private Double unitPrice;

    /** 含税单价 */
    @Excel(name = "含税单价")
    private Double taxPrice;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double allPrice;

    /** 生产批次号 */
    @Excel(name = "生产批次号")
    private String produceNumber;

    /** 生产日期 */
    @Excel(name = "生产日期")
    private String produceDate;

    /** 失效日期 */
    @Excel(name = "失效日期")
    private String expireDate;

    /** 自定义字段1 */
    @Excel(name = "自定义字段1")
    private String otherField1;

    /** 自定义字段2 */
    @Excel(name = "自定义字段2")
    private String otherField2;

    /** 自定义字段3 */
    @Excel(name = "自定义字段3")
    private String otherField3;

    /** 自定义字段4 */
    @Excel(name = "自定义字段4")
    private String otherField4;

    /** 自定义字段5 */
    @Excel(name = "自定义字段5")
    private String otherField5;

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
    public void setSitemNo(String sitemNo) 
    {
        this.sitemNo = sitemNo;
    }

    public String getSitemNo() 
    {
        return sitemNo;
    }
    public void setSflowNo(String sflowNo) 
    {
        this.sflowNo = sflowNo;
    }

    public String getSflowNo() 
    {
        return sflowNo;
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
    public void setUnitPrice(Double unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPrice() 
    {
        return unitPrice;
    }
    public void setTaxPrice(Double taxPrice) 
    {
        this.taxPrice = taxPrice;
    }

    public Double getTaxPrice() 
    {
        return taxPrice;
    }
    public void setAllPrice(Double allPrice) 
    {
        this.allPrice = allPrice;
    }

    public Double getAllPrice() 
    {
        return allPrice;
    }
    public void setProduceNumber(String produceNumber) 
    {
        this.produceNumber = produceNumber;
    }

    public String getProduceNumber() 
    {
        return produceNumber;
    }
    public void setProduceDate(String produceDate) 
    {
        this.produceDate = produceDate;
    }

    public String getProduceDate() 
    {
        return produceDate;
    }
    public void setExpireDate(String expireDate) 
    {
        this.expireDate = expireDate;
    }

    public String getExpireDate() 
    {
        return expireDate;
    }
    public void setOtherField1(String otherField1) 
    {
        this.otherField1 = otherField1;
    }

    public String getOtherField1() 
    {
        return otherField1;
    }
    public void setOtherField2(String otherField2) 
    {
        this.otherField2 = otherField2;
    }

    public String getOtherField2() 
    {
        return otherField2;
    }
    public void setOtherField3(String otherField3) 
    {
        this.otherField3 = otherField3;
    }

    public String getOtherField3() 
    {
        return otherField3;
    }
    public void setOtherField4(String otherField4) 
    {
        this.otherField4 = otherField4;
    }

    public String getOtherField4() 
    {
        return otherField4;
    }
    public void setOtherField5(String otherField5) 
    {
        this.otherField5 = otherField5;
    }

    public String getOtherField5() 
    {
        return otherField5;
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
            .append("sitemNo", getSitemNo())
            .append("sflowNo", getSflowNo())
            .append("equipNo", getEquipNo())
            .append("dataType", getDataType())
            .append("equipNum", getEquipNum())
            .append("unitPrice", getUnitPrice())
            .append("taxPrice", getTaxPrice())
            .append("allPrice", getAllPrice())
            .append("produceNumber", getProduceNumber())
            .append("produceDate", getProduceDate())
            .append("expireDate", getExpireDate())
            .append("otherField1", getOtherField1())
            .append("otherField2", getOtherField2())
            .append("otherField3", getOtherField3())
            .append("otherField4", getOtherField4())
            .append("otherField5", getOtherField5())
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
