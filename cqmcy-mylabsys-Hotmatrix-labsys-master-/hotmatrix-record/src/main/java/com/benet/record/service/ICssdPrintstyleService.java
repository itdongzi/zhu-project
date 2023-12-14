package com.benet.record.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.record.domain.CssdPrintstyle;

/**
 * 打印样式Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ICssdPrintstyleService 
{
    /**
     * 查询所有打印样式列表
     *
     * @param appCode 应用编号
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getAllRecords(String appCode);

    /**
     * 按分类查询打印样式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询打印样式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询打印样式列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 打印样式集合
     */
    public List<CssdPrintstyle> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 打印样式
     */
    public CssdPrintstyle getRecordByNo(String appCode,String no);

    /**
     * 查询打印样式名称
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询打印样式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增打印样式
     *
     * @param appCode 应用编号
     * @param info 打印样式
     * @return 结果
     */
    public int AddNewRecord(String appCode,CssdPrintstyle info);

    /**
     * 更新打印样式
     *
     * @param appCode 应用编号
     * @param info 打印样式
     * @return 结果
     */
    public int UpdateRecord(String appCode,CssdPrintstyle info);

    /**
     * 硬删除打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除打印样式
     *
     * @param appCode 应用编号
     * @param nos 打印样式IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除打印样式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除打印样式
     *
     * @param appCode 应用编号
     * @param nos 打印样式IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除打印样式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
