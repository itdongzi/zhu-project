package com.benet.genert.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.genert.domain.SysTabcolumn;

/**
 * 代码生成业务字段Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysTabcolumnService
{
    /**
     * 查询所有代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @return 代码生成业务字段集合
     */
    public List<SysTabcolumn> getAllRecords(String appCode);

    /**
     * 按分类查询代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 代码生成业务字段集合
     */
    public List<SysTabcolumn> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 代码生成业务字段集合
     */
    public List<SysTabcolumn> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询代码生成业务字段列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 代码生成业务字段集合
     */
    public List<SysTabcolumn> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询代码生成业务字段
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 代码生成业务字段
     */
    public SysTabcolumn getRecordByNo(String appCode,String no);

    /**
     * 查询代码生成业务字段名称
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询代码生成业务字段计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增代码生成业务字段
     *
     * @param appCode 应用编号
     * @param info 代码生成业务字段
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysTabcolumn info);

    /**
     * 更新代码生成业务字段
     *
     * @param appCode 应用编号
     * @param info 代码生成业务字段
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysTabcolumn info);

    /**
     * 硬删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param no 代码生成业务字段ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param nos 代码生成业务字段IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除代码生成业务字段
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    public List<SysTabcolumn> getDbTableColumnsByName(String tableName);
}
