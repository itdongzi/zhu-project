package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysOperatelog;

/**
 * 操作日志记录Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysOperatelogService
{
    /**
     * 查询所有操作日志记录列表
     *
     * @param appCode 应用编号
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getAllRecords(String appCode);

    /**
     * 按分类查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 操作日志记录集合
     */
    public List<SysOperatelog> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 操作日志记录
     */
    public SysOperatelog getRecordByNo(String appCode,String no);

    /**
     * 查询操作日志记录名称
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询操作日志记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增操作日志记录
     *
     * @param appCode 应用编号
     * @param info 操作日志记录
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysOperatelog info);

    /**
     * 更新操作日志记录
     *
     * @param appCode 应用编号
     * @param info 操作日志记录
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysOperatelog info);

    /**
     * 硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
