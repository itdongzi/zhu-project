package com.benet.record.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 出入库单据主对象 cssd_stockflows
 * 
 * @author yoxking
 * @date 2022-10-02 09:24:00
 */
public class CssdStockflows extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 流水id */
    @Excel(name = "流水id")
    private String sflowNo;

    /** 类别（1器械物品 2耗材物品） */
    @Excel(name = "类别", readConverterExp = "1=器械物品,2=耗材物品")
    private String dataType;

    /** 单据类型（11入库 12出库 13盘点 14报损） */
    @Excel(name = "单据类型", readConverterExp = "1=1入库,1=2出库,1=3盘点,1=4报损")
    private String sflowType;

    /** 库房id */
    @Excel(name = "库房id")
    private String storeNo;

    /** 供应商id */
    @Excel(name = "供应商id")
    private String suppeNo;

    /** 初始票据号 */
    @Excel(name = "初始票据号")
    private String defaultNumber;

    /** 票据号 */
    @Excel(name = "票据号")
    private String sflowNumber;

    /** 出入库时间 */
    @Excel(name = "出入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime opertTime;

    /** 采购/领料-经手人id */
    @Excel(name = "采购/领料-经手人id")
    private Long opertUser;

    /** 变动金额(收款/付款) */
    @Excel(name = "变动金额(收款/付款)")
    private Double changeAmount;

    /** 合计金额 */
    @Excel(name = "合计金额")
    private Double totalPrice;

    /** 付款类型(现金、记账等) */
    @Excel(name = "付款类型(现金、记账等)")
    private String payType;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 业务员（可以多个） */
    @Excel(name = "业务员", readConverterExp = "可=以多个")
    private String salesUser;

    /** 优惠率 */
    @Excel(name = "优惠率")
    private Double discount;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private Double discountMoney;

    /** 优惠后金额 */
    @Excel(name = "优惠后金额")
    private Double discountLastMoney;

    /** 结算天数 */
    @Excel(name = "结算天数")
    private Integer accountDay;

    /** 关联订单号 */
    @Excel(name = "关联订单号")
    private String linkNumber;

    /** 科室编号 */
    @Excel(name = "科室编号")
    private String branchNo;

    /** 状态，1未审核、2已审核、3已转采购|销售 */
    @Excel(name = "状态，1未审核、2已审核、3已转采购|销售")
    private String checkState;

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
    public void setSflowNo(String sflowNo) 
    {
        this.sflowNo = sflowNo;
    }

    public String getSflowNo() 
    {
        return sflowNo;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setSflowType(String sflowType) 
    {
        this.sflowType = sflowType;
    }

    public String getSflowType() 
    {
        return sflowType;
    }
    public void setStoreNo(String storeNo) 
    {
        this.storeNo = storeNo;
    }

    public String getStoreNo() 
    {
        return storeNo;
    }
    public void setSuppeNo(String suppeNo) 
    {
        this.suppeNo = suppeNo;
    }

    public String getSuppeNo() 
    {
        return suppeNo;
    }
    public void setDefaultNumber(String defaultNumber) 
    {
        this.defaultNumber = defaultNumber;
    }

    public String getDefaultNumber() 
    {
        return defaultNumber;
    }
    public void setSflowNumber(String sflowNumber) 
    {
        this.sflowNumber = sflowNumber;
    }

    public String getSflowNumber() 
    {
        return sflowNumber;
    }
    public void setOpertTime(LocalDateTime opertTime) 
    {
        this.opertTime = opertTime;
    }

    public LocalDateTime getOpertTime() 
    {
        return opertTime;
    }
    public void setOpertUser(Long opertUser) 
    {
        this.opertUser = opertUser;
    }

    public Long getOpertUser() 
    {
        return opertUser;
    }
    public void setChangeAmount(Double changeAmount) 
    {
        this.changeAmount = changeAmount;
    }

    public Double getChangeAmount() 
    {
        return changeAmount;
    }
    public void setTotalPrice(Double totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() 
    {
        return totalPrice;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setBillType(String billType) 
    {
        this.billType = billType;
    }

    public String getBillType() 
    {
        return billType;
    }
    public void setSalesUser(String salesUser) 
    {
        this.salesUser = salesUser;
    }

    public String getSalesUser() 
    {
        return salesUser;
    }
    public void setDiscount(Double discount) 
    {
        this.discount = discount;
    }

    public Double getDiscount() 
    {
        return discount;
    }
    public void setDiscountMoney(Double discountMoney) 
    {
        this.discountMoney = discountMoney;
    }

    public Double getDiscountMoney() 
    {
        return discountMoney;
    }
    public void setDiscountLastMoney(Double discountLastMoney) 
    {
        this.discountLastMoney = discountLastMoney;
    }

    public Double getDiscountLastMoney() 
    {
        return discountLastMoney;
    }
    public void setAccountDay(Integer accountDay) 
    {
        this.accountDay = accountDay;
    }

    public Integer getAccountDay() 
    {
        return accountDay;
    }
    public void setLinkNumber(String linkNumber) 
    {
        this.linkNumber = linkNumber;
    }

    public String getLinkNumber() 
    {
        return linkNumber;
    }
    public void setBranchNo(String branchNo) 
    {
        this.branchNo = branchNo;
    }

    public String getBranchNo() 
    {
        return branchNo;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
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
            .append("sflowNo", getSflowNo())
            .append("dataType", getDataType())
            .append("sflowType", getSflowType())
            .append("storeNo", getStoreNo())
            .append("suppeNo", getSuppeNo())
            .append("defaultNumber", getDefaultNumber())
            .append("sflowNumber", getSflowNumber())
            .append("opertTime", getOpertTime())
            .append("opertUser", getOpertUser())
            .append("changeAmount", getChangeAmount())
            .append("totalPrice", getTotalPrice())
            .append("payType", getPayType())
            .append("billType", getBillType())
            .append("salesUser", getSalesUser())
            .append("discount", getDiscount())
            .append("discountMoney", getDiscountMoney())
            .append("discountLastMoney", getDiscountLastMoney())
            .append("accountDay", getAccountDay())
            .append("linkNumber", getLinkNumber())
            .append("branchNo", getBranchNo())
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
