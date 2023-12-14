package com.benet.genert.utils;

import java.util.Arrays;

import com.benet.common.configure.GenertConfig;
import com.benet.common.constant.GenConstants;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.genert.domain.SysTabcolumn;
import com.benet.genert.domain.SysTableinfo;
import org.apache.commons.lang3.RegExUtils;

/**
 * 代码生成器 工具类
 * 
 * @author yoxking
 */
public class GeneratUtils
{
    /**
     * 初始化表信息
     */
    public static void initTable(SysTableinfo tableInfo, String opertName)
    {
        tableInfo.setTableNo(UuidUtils.shortUUID());
        tableInfo.setClassName(convertClassName(tableInfo.getTableName()));
        tableInfo.setPackageName(GenertConfig.getPackageName());
        tableInfo.setModuleName(getModuleName(GenertConfig.getPackageName()));
        tableInfo.setBusinessName(getBusinessName(tableInfo.getTableName()));
        tableInfo.setFunctionName(replaceText(tableInfo.getTableComment()));
        tableInfo.setFunctionAuthor(GenertConfig.getAuthor());
        tableInfo.setCreateBy(opertName);
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(SysTabcolumn tableField, SysTableinfo tableInfo)
    {
        String dataType = getDbType(tableField.getColumnType());
        String fieldName = tableField.getColumnName();
        tableField.setColumnNo(UuidUtils.shortUUID());
        tableField.setOrderNo(1);
        tableField.setTableNo(tableInfo.getTableNo());
        tableField.setCreateBy(tableInfo.getCreateBy());
        // 设置java字段名
        tableField.setJavaField(StringUtils.toCamelCase(fieldName));

        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType))
        {
            tableField.setJavaType(GenConstants.TYPE_STRING);
            // 字符串长度超过500设置为文本域
            Integer fieldLength = getFieldLength(tableField.getColumnType());
            String htmlType = fieldLength >= 500 ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            tableField.setHtmlType(htmlType);
        }
        else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType))
        {
            tableField.setJavaType(GenConstants.TYPE_DATE);
            tableField.setHtmlType(GenConstants.HTML_DATETIME);
        }
        else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType))
        {
            tableField.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点型
            String[] str = StringUtils.split(StringUtils.substringBetween(tableField.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0)
            {
                tableField.setJavaType(GenConstants.TYPE_DOUBLE);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10)
            {
                tableField.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else
            {
                tableField.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        // 插入字段（默认所有字段都需要插入）
        tableField.setIsInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, fieldName) && !tableField.isPk())
        {
            tableField.setIsEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, fieldName) && !tableField.isPk())
        {
            tableField.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, fieldName) && !tableField.isPk())
        {
            tableField.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(fieldName, "name"))
        {
            tableField.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(fieldName, "status"))
        {
            tableField.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(fieldName, "type")
                || StringUtils.endsWithIgnoreCase(fieldName, "sex"))
        {
            tableField.setHtmlType(GenConstants.HTML_SELECT);
        }
    }

    /**
     * 校验数组是否包含指定值
     * 
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue)
    {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取模块名
     * 
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        String moduleName = StringUtils.substring(packageName, lastIndex + 1, nameLength);
        return moduleName;
    }

    /**
     * 获取业务名
     * 
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName)
    {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        String businessName = StringUtils.substring(tableName, lastIndex + 1, nameLength);
        return businessName;
    }

    /**
     * 表名转换成Java类名
     * 
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(String tableName)
    {
        boolean autoRemovePre = GenertConfig.getAutoRemovePre();
        String tablePrefix = GenertConfig.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix))
        {
            String[] searchList = StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     * 
     * @param replacementm 替换值
     * @param searchList 替换列表
     * @return
     */
    public static String replaceFirst(String replacementm, String[] searchList)
    {
        String text = replacementm;
        for (String searchString : searchList)
        {
            if (replacementm.startsWith(searchString))
            {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 关键字替换
     * 
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public static String replaceText(String text)
    {
        return RegExUtils.replaceAll(text, "(?:表|基云)", "");
    }

    /**
     * 获取数据库类型字段
     * 
     * @param fieldType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String fieldType)
    {
        if (StringUtils.indexOf(fieldType, "(") > 0)
        {
            return StringUtils.substringBefore(fieldType, "(");
        }
        else
        {
            return fieldType;
        }
    }

    /**
     * 获取字段长度
     * 
     * @param fieldType 列类型
     * @return 截取后的列类型
     */
    public static Integer getFieldLength(String fieldType)
    {
        if (StringUtils.indexOf(fieldType, "(") > 0)
        {
            String length = StringUtils.substringBetween(fieldType, "(", ")");
            return Integer.valueOf(length);
        }
        else
        {
            return 0;
        }
    }
}