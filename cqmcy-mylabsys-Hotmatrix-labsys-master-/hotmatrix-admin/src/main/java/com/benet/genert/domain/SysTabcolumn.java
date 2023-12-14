package com.benet.genert.domain;

import com.benet.common.utils.string.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

/**
 * 代码生成业务字段对象 sys_tablefield
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysTabcolumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 字段编号 */
    @Excel(name = "字段编号")
    private String columnNo;

    /** 归属表编号 */
    @Excel(name = "归属表编号")
    private String tableNo;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String columnName;

    /** 字段描述 */
    @Excel(name = "字段描述")
    private String columnComment;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String columnType;

    /** JAVA类型 */
    @Excel(name = "JAVA类型")
    private String javaType;

    /** JAVA字段名 */
    @Excel(name = "JAVA字段名")
    private String javaField;

    /** 是否主键（1是） */
    @Excel(name = "是否主键", readConverterExp = "1=是")
    private String isPk;

    /** 是否自增（1是） */
    @Excel(name = "是否自增", readConverterExp = "1=是")
    private String isIncrement;

    /** 是否必填（1是） */
    @Excel(name = "是否必填", readConverterExp = "1=是")
    private String isRequired;

    /** 是否为插入字段（1是） */
    @Excel(name = "是否为插入字段", readConverterExp = "1=是")
    private String isInsert;

    /** 是否编辑字段（1是） */
    @Excel(name = "是否编辑字段", readConverterExp = "1=是")
    private String isEdit;

    /** 是否列表字段（1是） */
    @Excel(name = "是否列表字段", readConverterExp = "1=是")
    private String isList;

    /** 是否查询字段（1是） */
    @Excel(name = "是否查询字段", readConverterExp = "1=是")
    private String isQuery;

    /** 查询方式（等于、不等于、大于、小于、范围） */
    @Excel(name = "查询方式", readConverterExp = "等=于、不等于、大于、小于、范围")
    private String queryType;

    /** 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件） */
    @Excel(name = "显示类型", readConverterExp = "文=本框、文本域、下拉框、复选框、单选框、日期控件")
    private String htmlType;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

    /** 排序 */
    private Integer orderNo;

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

    public String getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(String columnNo) {
        this.columnNo = columnNo;
    }

    public void setTableNo(String tableNo)
    {
        this.tableNo = tableNo;
    }

    public String getTableNo() 
    {
        return tableNo;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setJavaType(String javaType)
    {
        this.javaType = javaType;
    }

    public String getJavaType() 
    {
        return javaType;
    }
    public void setJavaField(String javaField) 
    {
        this.javaField = javaField;
    }

    public String getJavaField() 
    {
        return javaField;
    }
    public void setIsPk(String isPk) 
    {
        this.isPk = isPk;
    }

    public String getIsPk() 
    {
        return isPk;
    }


    public boolean isPk()
    {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk)
    {
        return isPk != null && StringUtils.equals("1", isPk);
    }

    public void setIsIncrement(String isIncrement) 
    {
        this.isIncrement = isIncrement;
    }

    public String getIsIncrement() 
    {
        return isIncrement;
    }

    public boolean isIncrement()
    {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(String isIncrement)
    {
        return isIncrement != null && StringUtils.equals("1", isIncrement);
    }
    public void setIsRequired(String isRequired) 
    {
        this.isRequired = isRequired;
    }

    public String getIsRequired() 
    {
        return isRequired;
    }

    public boolean isRequired()
    {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(String isRequired)
    {
        return isRequired != null && StringUtils.equals("1", isRequired);
    }
    public void setIsInsert(String isInsert) 
    {
        this.isInsert = isInsert;
    }

    public String getIsInsert() 
    {
        return isInsert;
    }

    public boolean isInsert()
    {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(String isInsert)
    {
        return isInsert != null && StringUtils.equals("1", isInsert);
    }

    public void setIsEdit(String isEdit)
    {
        this.isEdit = isEdit;
    }

    public String getIsEdit()
    {
        return isEdit;
    }

    public boolean isEdit()
    {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(String isEdit)
    {
        return isEdit != null && StringUtils.equals("1", isEdit);
    }

    public void setIsList(String isList)
    {
        this.isList = isList;
    }

    public String getIsList() 
    {
        return isList;
    }

    public boolean isList()
    {
        return isList(this.isList);
    }

    public boolean isList(String isList)
    {
        return isList != null && StringUtils.equals("1", isList);
    }
    public void setIsQuery(String isQuery) 
    {
        this.isQuery = isQuery;
    }

    public String getIsQuery() 
    {
        return isQuery;
    }

    public boolean isQuery()
    {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(String isQuery)
    {
        return isQuery != null && StringUtils.equals("1", isQuery);
    }
    public void setQueryType(String queryType) 
    {
        this.queryType = queryType;
    }

    public String getQueryType() 
    {
        return queryType;
    }
    public void setHtmlType(String htmlType) 
    {
        this.htmlType = htmlType;
    }

    public String getHtmlType() 
    {
        return htmlType;
    }
    public void setDictType(String dictType) 
    {
        this.dictType = dictType;
    }

    public String getDictType() 
    {
        return dictType;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isSuperField()
    {
        return isSuperField(this.javaField);
    }

    public static boolean isSuperField(String javaField)
    {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime", "comments",
                // TreeEntity
                "parentName", "parentNo", "orderNo", "ancestors");
    }

    public boolean isUsableField()
    {
        return isUsableField(javaField);
    }

    public static boolean isUsableField(String javaField)
    {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtils.equalsAnyIgnoreCase(javaField, "parentNo", "orderNo");
    }

    public String readConverterExp()
    {
        String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(remarks))
        {
            for (String value : remarks.split(" "))
            {
                if (StringUtils.isNotEmpty(value))
                {
                    Object startStr = value.subSequence(0, 1);
                    String endStr = value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(",");
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
        else
        {
            return this.columnComment;
        }
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
            .append("columnNo", getColumnNo())
            .append("tableNo", getTableNo())
            .append("columnName", getColumnName())
            .append("columnComment", getColumnComment())
            .append("columnType", getColumnType())
            .append("javaType", getJavaType())
            .append("javaField", getJavaField())
            .append("isPk", getIsPk())
            .append("isIncrement", getIsIncrement())
            .append("isRequired", getIsRequired())
            .append("isInsert", getIsInsert())
            .append("isEdit", getIsEdit())
            .append("isList", getIsList())
            .append("isQuery", getIsQuery())
            .append("queryType", getQueryType())
            .append("htmlType", getHtmlType())
            .append("dictType", getDictType())
                .append("orderNo", getOrderNo())
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
