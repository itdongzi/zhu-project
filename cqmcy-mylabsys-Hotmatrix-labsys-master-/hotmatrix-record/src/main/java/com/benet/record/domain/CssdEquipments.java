package com.benet.record.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;


/**
 * 器械信息对象 cssd_equipments
 * 
 * @author yoxking
 * @date 2022-10-02 09:23:59
 */
public class CssdEquipments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 器械id */
    @Excel(name = "器械id")
    private String equipNo;

    /** 器械中文名称 */
    @Excel(name = "器械中文名称")
    private String equipCname;

    /** 器械英文名称 */
    @Excel(name = "器械英文名称")
    private String equipEname;

    /** 器械照片 */
    @Excel(name = "器械照片")
    private String equipImage;

    /** 类别（1器械物品 2耗材物品） */
    @Excel(name = "类别", readConverterExp = "1=器械物品,2=耗材物品")
    private String dataType;

    /** 器械规格 */
    @Excel(name = "器械规格")
    private String equipSpec;

    /** 器械型号 */
    @Excel(name = "器械型号")
    private String equipModel;

    /** 器械类型 */
    @Excel(name = "器械类型")
    private String classNo;

    /** 生产商编号 */
    @Excel(name = "生产商编号")
    private String prodNo;

    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private String suppNo;

    /** 期限编号 */
    @Excel(name = "期限编号")
    private String termNo;

    /** 单位编号 */
    @Excel(name = "单位编号")
    private String unitNo;

    /** 单价 */
    @Excel(name = "单价")
    private Double unitPrice;

    /** 安全库存量 */
    @Excel(name = "安全库存量")
    private Double safeStock;

    /** 工作量 */
    @Excel(name = "工作量")
    private String workLoad;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNo;

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
    public void setEquipNo(String equipNo) 
    {
        this.equipNo = equipNo;
    }

    public String getEquipNo() 
    {
        return equipNo;
    }
    public void setEquipCname(String equipCname) 
    {
        this.equipCname = equipCname;
    }

    public String getEquipCname() 
    {
        return equipCname;
    }
    public void setEquipEname(String equipEname) 
    {
        this.equipEname = equipEname;
    }

    public String getEquipEname() 
    {
        return equipEname;
    }
    public void setEquipImage(String equipImage) 
    {
        this.equipImage = equipImage;
    }

    public String getEquipImage() 
    {
        return equipImage;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setEquipSpec(String equipSpec) 
    {
        this.equipSpec = equipSpec;
    }

    public String getEquipSpec() 
    {
        return equipSpec;
    }
    public void setEquipModel(String equipModel) 
    {
        this.equipModel = equipModel;
    }

    public String getEquipModel() 
    {
        return equipModel;
    }
    public void setClassNo(String classNo) 
    {
        this.classNo = classNo;
    }

    public String getClassNo() 
    {
        return classNo;
    }
    public void setProdNo(String prodNo) 
    {
        this.prodNo = prodNo;
    }

    public String getProdNo() 
    {
        return prodNo;
    }
    public void setSuppNo(String suppNo) 
    {
        this.suppNo = suppNo;
    }

    public String getSuppNo() 
    {
        return suppNo;
    }
    public void setTermNo(String termNo) 
    {
        this.termNo = termNo;
    }

    public String getTermNo() 
    {
        return termNo;
    }
    public void setUnitNo(String unitNo) 
    {
        this.unitNo = unitNo;
    }

    public String getUnitNo() 
    {
        return unitNo;
    }
    public void setUnitPrice(Double unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPrice() 
    {
        return unitPrice;
    }
    public void setSafeStock(Double safeStock) 
    {
        this.safeStock = safeStock;
    }

    public Double getSafeStock() 
    {
        return safeStock;
    }
    public void setWorkLoad(String workLoad) 
    {
        this.workLoad = workLoad;
    }

    public String getWorkLoad() 
    {
        return workLoad;
    }
    public void setOrderNo(Integer orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() 
    {
        return orderNo;
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
            .append("equipNo", getEquipNo())
            .append("equipCname", getEquipCname())
            .append("equipEname", getEquipEname())
            .append("equipImage", getEquipImage())
            .append("dataType", getDataType())
            .append("equipSpec", getEquipSpec())
            .append("equipModel", getEquipModel())
            .append("classNo", getClassNo())
            .append("prodNo", getProdNo())
            .append("suppNo", getSuppNo())
            .append("termNo", getTermNo())
            .append("unitNo", getUnitNo())
            .append("unitPrice", getUnitPrice())
            .append("safeStock", getSafeStock())
            .append("workLoad", getWorkLoad())
            .append("orderNo", getOrderNo())
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
