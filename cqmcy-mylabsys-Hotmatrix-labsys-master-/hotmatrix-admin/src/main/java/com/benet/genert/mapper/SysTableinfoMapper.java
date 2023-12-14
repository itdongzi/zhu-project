package com.benet.genert.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.genert.domain.SysTableinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 代码生成业务Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysTableinfoMapper 
{
    /**
     * 查询所有代码生成业务列表
     *
     * @param appCode 应用编号
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询代码生成业务列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo" ) String classNo);

    /**
     * 分页查询代码生成业务列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 代码生成业务集合
     */
    public List<SysTableinfo> getRecordsByPaging(@Param("appCode") String appCode, @Param("model" ) PagingModel model);

    /**
     * 查询代码生成业务
     *
     * @param appCode 应用编号
     * @param no 代码生成业务ID
     * @return 代码生成业务
     */
    public SysTableinfo getRecordByNo(@Param("appCode") String appCode,@Param("no" ) String no);

    /**
     * 查询代码生成业务
     *
     * @param appCode 应用编号
     * @param tableName 表名
     * @return 代码生成业务
     */
    public SysTableinfo getRecordByName(@Param("appCode") String appCode,@Param("tableName" ) String tableName);

    /**
     * 查询代码生成业务名称
     *
     * @param appCode 应用编号
     * @param no 代码生成业务ID
     * @return 名称
     */
    public String getRecordNameByNo( @Param("appCode") String appCode,@Param("no" ) String no);

    /**
     * 查询代码生成业务计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition" ) String condition);

    /**
     * 新增代码生成业务
     *
     * @param info 代码生成业务
     * @return 结果
     */
    public int AddNewRecord(@Param("info" ) SysTableinfo info);

    /**
     * 更新代码生成业务
     *
     * @param info 代码生成业务
     * @return 结果
     */
    public int UpdateRecord(@Param("info" ) SysTableinfo info);

    /**
     * 硬删除代码生成业务
     *
     * @param appCode 应用编号
     * @param no 代码生成业务ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no" ) String no);

    /**
     * 批量硬删除代码生成业务
     *
     * @param appCode 应用编号
     * @param nos 代码生成业务IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos" ) String[] nos);

    /**
     * 按条件硬删除代码生成业务
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition" ) String condition);

    /**
     * 软删除代码生成业务
     *
     * @param appCode 应用编号
     * @param no 代码生成业务ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no" ) String no);

    /**
     * 批量软删除代码生成业务
     *
     * @param appCode 应用编号
     * @param nos 代码生成业务IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos" ) String[] nos);

    /**
     * 按条件软删除代码生成业务
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition" ) String condition);

    /**
     * 查询据库列表
     *
     * @param condition 条件
     * @return 数据库表集合
     */
    public List<SysTableinfo> getDbTableList(@Param("condition" ) String condition);


    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    public List<SysTableinfo> getDbTableListByNames(@Param("tableNames" ) String[] tableNames);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    public SysTableinfo getDbTableByName(@Param("tableName" ) String tableName);

}
