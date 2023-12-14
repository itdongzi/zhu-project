package com.benet.genert.domain;

import com.benet.common.constant.GenConstants;
import com.benet.common.utils.string.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 代码生成业务对象 sys_tableinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public class SysTableinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String tableNo;

    /** 表名称 */
    @Excel(name = "表名称")
    private String tableName;

    /** 表描述 */
    @Excel(name = "表描述")
    private String tableComment;

    /** 实体类名称 */
    @Excel(name = "实体类名称")
    private String className;

    /** 使用的模板（crud单表操作 tree树表操作） */
    @Excel(name = "使用的模板", readConverterExp = "c=rud单表操作,t=ree树表操作")
    private String templCategory;

    /** 生成包路径 */
    @Excel(name = "生成包路径")
    private String packageName;

    /** 生成模块名 */
    @Excel(name = "生成模块名")
    private String moduleName;

    /** 生成业务名 */
    @Excel(name = "生成业务名")
    private String businessName;

    /** 生成功能名 */
    @Excel(name = "生成功能名")
    private String functionName;

    /** 生成功能作者 */
    @Excel(name = "生成功能作者")
    private String functionAuthor;

    /** 主键信息 */
    private SysTabcolumn pkColumn;

    /** 表列信息 */
    private List<SysTabcolumn> columns;

    /** 其它生成选项 */
    @Excel(name = "其它生成选项")
    private String options;

    /** 树编码字段 */
    private String treeCode;

    /** 树父编码字段 */
    private String treeParentCode;

    /** 树名称字段 */
    private String treeName;

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
    public void setTableNo(String tableNo) 
    {
        this.tableNo = tableNo;
    }

    public String getTableNo() 
    {
        return tableNo;
    }
    public void setTableName(String tableName) 
    {
        this.tableName = tableName;
    }

    public String getTableName() 
    {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }

    public String getTemplCategory() {
        return templCategory;
    }

    public void setTemplCategory(String templCategory) {
        this.templCategory = templCategory;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getPackageName() 
    {
        return packageName;
    }
    public void setModuleName(String moduleName) 
    {
        this.moduleName = moduleName;
    }

    public String getModuleName() 
    {
        return moduleName;
    }
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    public void setFunctionName(String functionName) 
    {
        this.functionName = functionName;
    }

    public String getFunctionName() 
    {
        return functionName;
    }
    public void setFunctionAuthor(String functionAuthor) 
    {
        this.functionAuthor = functionAuthor;
    }

    public String getFunctionAuthor() 
    {
        return functionAuthor;
    }

    public SysTabcolumn getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(SysTabcolumn pkColumn) {
        this.pkColumn = pkColumn;
    }

    public List<SysTabcolumn> getColumns() {
        return columns;
    }

    public void setColumns(List<SysTabcolumn> columns) {
        this.columns = columns;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getTreeParentCode() {
        return treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode) {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public boolean isTree()
    {
        return isTree(this.templCategory);
    }

    public static boolean isTree(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud()
    {
        return isCrud(this.templCategory);
    }

    public static boolean isCrud(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperField(String javaField)
    {
        return isSuperField(this.templCategory, javaField);
    }

    public static boolean isSuperField(String tplCategory, String javaField)
    {
        if (isTree(tplCategory))
        {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
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
            .append("tableNo", getTableNo())
            .append("tableName", getTableName())
            .append("tableComment", getTableComment())
            .append("className", getClassName())
            .append("tplCategory", getTemplCategory())
            .append("packageName", getPackageName())
            .append("moduleName", getModuleName())
            .append("businessName", getBusinessName())
            .append("functionName", getFunctionName())
            .append("functionAuthor", getFunctionAuthor())
            .append("options", getOptions())
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
