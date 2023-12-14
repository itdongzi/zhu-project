package com.benet.record.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdSupplierinfo;

/**
 * 供应商信息Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdSupplierinfoService 
{
    /**
     * 查询所有供应商信息列表
     *
     * @param appCode 应用编号
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getAllRecords(String appCode);

    /**
     * 按分类查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 供应商信息集合
     */
    public List<CssdSupplierinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 供应商信息
     */
    public CssdSupplierinfo getRecordByNo(String appCode,String no);

    /**
     * 查询供应商信息名称
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询供应商信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增供应商信息
     *
     * @param appCode 应用编号
     * @param info 供应商信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdSupplierinfo info);

    /**
     * 更新供应商信息
     *
     * @param appCode 应用编号
     * @param info 供应商信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdSupplierinfo info);

    /**
     * 硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param nos 供应商信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除供应商信息
     *
     * @param appCode 应用编号
     * @param nos 供应商信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除供应商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
