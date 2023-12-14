package com.benet.record.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdStockitems;

/**
 * 出入库单明细Service接口
 * 
 * @author yoxking
 * @date 2021-03-20
 */
public interface ICssdStockitemsService 
{
    /**
     * 查询所有出入库单明细列表
     *
     * @param appCode 应用编号
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getAllRecords(String appCode);

    /**
     * 按分类查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 出入库单明细集合
     */
    public List<CssdStockitems> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 出入库单明细
     */
    public CssdStockitems getRecordByNo(String appCode,String no);

    /**
     * 查询出入库单明细名称
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询出入库单明细计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增出入库单明细
     *
     * @param appCode 应用编号
     * @param info 出入库单明细
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdStockitems info);

    /**
     * 更新出入库单明细
     *
     * @param appCode 应用编号
     * @param info 出入库单明细
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdStockitems info);

    /**
     * 硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param nos 出入库单明细IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param nos 出入库单明细IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
