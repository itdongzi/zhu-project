package com.benet.genert.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.benet.common.constant.GenConstants;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.genert.domain.SysTabcolumn;
import com.benet.genert.domain.SysTableinfo;
import org.apache.velocity.VelocityContext;
import com.alibaba.fastjson.JSONObject;

public class VelocityUtils
{
    /** 项目空间路径 */
    private static final String PROJECT_PATH = "main/java";

    /** mybatis空间路径 */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 设置模板变量信息
     * 
     * @return 模板列表
     */
    public static VelocityContext prepareContext(SysTableinfo tableInfo)
    {
        String moduleName = tableInfo.getModuleName();
        String businessName = tableInfo.getBusinessName();
        String packageName = tableInfo.getPackageName();
        String tplCategory = tableInfo.getTemplCategory();
        String functionName = tableInfo.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", tableInfo.getTemplCategory());
        velocityContext.put("tableName", tableInfo.getTableName());
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", tableInfo.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(tableInfo.getClassName()));
        velocityContext.put("moduleName", tableInfo.getModuleName());
        velocityContext.put("BusinessName", StringUtils.capitalize(tableInfo.getBusinessName()));
        velocityContext.put("businessName", tableInfo.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", tableInfo.getFunctionAuthor());
        velocityContext.put("datetime", DateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
        velocityContext.put("pkColumn", tableInfo.getPkColumn());
        velocityContext.put("importList", getImportList(tableInfo.getColumns()));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns", tableInfo.getColumns());
        velocityContext.put("table", tableInfo);
        if (GenConstants.TPL_TREE.equals(tplCategory))
        {
            setTreeVelocityContext(velocityContext, tableInfo);
        }
        return velocityContext;
    }

    public static void setTreeVelocityContext(VelocityContext context, SysTableinfo tableInfo)
    {
        String options = tableInfo.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeCode = getTreecode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("treeParentCode", treeParentCode);
        context.put("treeName", treeName);
        context.put("expandColumn", getExpandColumn(tableInfo));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
        {
            context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME))
        {
            context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
        }
    }

    /**
     * 获取模板信息
     * 
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory)
    {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/mapper.java.vm");
        //templates.add("vm/java/mapper.reactive.java.vm");
        templates.add("vm/java/service.java.vm");
        //templates.add("vm/java/service.reactive.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        //templates.add("vm/java/serviceImpl.reactive.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        templates.add("vm/sql/sql.vm");
        templates.add("vm/js/api.js.vm");
        if (GenConstants.TPL_CRUD.equals(tplCategory))
        {
            templates.add("vm/vue/Index.vue.vm");
        }
        else if (GenConstants.TPL_TREE.equals(tplCategory))
        {
            templates.add("vm/vue/Index-tree.vue.vm");
        }
        templates.add("vm/vue/List.vue.vm");
        templates.add("vm/vue/Edit.vue.vm");
        templates.add("vm/vue/Detail.vue.vm");
        templates.add("vm/vuee/Index.vuee.vm");
        templates.add("vm/vuee/List.vuee.vm");
        templates.add("vm/vuee/Edit.vuee.vm");
        templates.add("vm/vuee/Detail.vuee.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, SysTableinfo tableInfo)
    {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = tableInfo.getPackageName();
        // 模块名
        String moduleName = tableInfo.getModuleName();
        // 大写类名
        String className = tableInfo.getClassName();
        // 业务名称
        String businessName = tableInfo.getBusinessName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;
        String vuePath = "vue";
        String vueePath = "vuee";

        if (template.contains("domain.java.vm"))
        {
            fileName = StringUtils.format("{}/domain/{}.java", javaPath, className);
        }
        else if (template.contains("mapper.java.vm"))
        {
            fileName = StringUtils.format("{}/mapper/{}Mapper.java", javaPath, className);
        }
        else if (template.contains("mapper.reactive.java.vm"))
        {
            fileName = StringUtils.format("{}/mapper-reactive/{}Mapper.java", javaPath, className);
        }
        else if (template.contains("service.java.vm"))
        {
            fileName = StringUtils.format("{}/service/I{}Service.java", javaPath, className);
        }
        else if (template.contains("service.reactive.java.vm"))
        {
            fileName = StringUtils.format("{}/service-reactive/I{}Service.java", javaPath, className);
        }
        else if (template.contains("serviceImpl.java.vm"))
        {
            fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        }
        else if (template.contains("serviceImpl.reactive.java.vm"))
        {
            fileName = StringUtils.format("{}/service-reactive/impl/{}ServiceImpl.java", javaPath, className);
        }
        else if (template.contains("controller.java.vm"))
        {
            fileName = StringUtils.format("{}/controller/{}Controller.java", javaPath, className);
        }
        else if (template.contains("mapper.xml.vm"))
        {
            fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
        }
        else if (template.contains("sql.vm"))
        {
            fileName = businessName + "Menu.sql";
        }
        else if (template.contains("api.js.vm"))
        {
            fileName = StringUtils.format("{}/api/{}/{}.js", vuePath, moduleName, businessName);
        }
        else if (template.contains("Index.vue.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Index.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("Index-tree.vue.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Index-tree.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("List.vue.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/List.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("Edit.vue.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Edit.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("Detail.vue.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Detail.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("Index.vuee.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Index.vue", vueePath, moduleName, businessName);
        }
        else if (template.contains("List.vuee.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/List.vue", vueePath, moduleName, businessName);
        }
        else if (template.contains("Edit.vuee.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Edit.vue", vueePath, moduleName, businessName);
        }
        else if (template.contains("Detail.vuee.vm"))
        {
            fileName = StringUtils.format("{}/views/{}/{}/Detail.vue", vueePath, moduleName, businessName);
        }
        return fileName;
    }

    /**
     * 获取包前缀
     * 
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        String basePackage = StringUtils.substring(packageName, 0, lastIndex);
        return basePackage;
    }

    /**
     * 根据列类型获取导入包
     * 
     * @param columns 列集合
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<SysTabcolumn> columns)
    {
        HashSet<String> importList = new HashSet<String>();
        for (SysTabcolumn column : columns)
        {
            if (!column.isSuperField() && GenConstants.TYPE_DATE.equals(column.getJavaType()))
            {
                importList.add("java.time.LocalDateTime");
            }
            else if (!column.isSuperField() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType()))
            {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取权限前缀
     * 
     * @param moduleName 模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName)
    {
        return StringUtils.format("{}:{}", moduleName, businessName);

    }

    /**
     * 获取树编码
     * 
     * @param paramsObj 生成其他选项
     * @return 树编码
     */
    public static String getTreecode(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_CODE))
        {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE));
        }
        return "";
    }

    /**
     * 获取树父编码
     * 
     * @param paramsObj 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
        {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        return "";
    }

    /**
     * 获取树名称
     * 
     * @param paramsObj 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_NAME))
        {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME));
        }
        return "";
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     * 
     * @param tableInfo 业务表对象
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(SysTableinfo tableInfo)
    {
        String options = tableInfo.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeName = paramsObj.getString(GenConstants.TREE_NAME);
        int num = 0;
        for (SysTabcolumn column : tableInfo.getColumns())
        {
            if (column.isList())
            {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName))
                {
                    break;
                }
            }
        }
        return num;
    }
}