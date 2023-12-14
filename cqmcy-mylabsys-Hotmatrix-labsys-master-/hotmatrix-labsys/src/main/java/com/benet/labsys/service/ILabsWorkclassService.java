package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsWorkclass;

/**
 * 实验室检查类型Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
public interface ILabsWorkclassService 
{
    /**
     * 查询所有实验室检查类型列表
     *
     * @param appCode 应用编号
     * @return 实验室检查类型集合
     */
    public List<LabsWorkclass> getAllRecords(String appCode);

    /**
     * 按分类查询实验室检查类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室检查类型集合
     */
    public List<LabsWorkclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询实验室检查类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室检查类型集合
     */
    public List<LabsWorkclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询实验室检查类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室检查类型集合
     */
    public List<LabsWorkclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询实验室检查类型
     *
     * @param appCode 应用编号
     * @param no 实验室检查类型ID
     * @return 实验室检查类型
     */
    public LabsWorkclass getRecordByNo(String appCode,String no);

    /**
     * 查询实验室检查类型名称
     *
     * @param appCode 应用编号
     * @param no 实验室检查类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询实验室检查类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增实验室检查类型
     *
     * @param appCode 应用编号
     * @param info 实验室检查类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsWorkclass info);

    /**
     * 更新实验室检查类型
     *
     * @param appCode 应用编号
     * @param info 实验室检查类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsWorkclass info);

    /**
     * 硬删除实验室检查类型
     *
     * @param appCode 应用编号
     * @param no 实验室检查类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除实验室检查类型
     *
     * @param appCode 应用编号
     * @param nos 实验室检查类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除实验室检查类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除实验室检查类型
     *
     * @param appCode 应用编号
     * @param no 实验室检查类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除实验室检查类型
     *
     * @param appCode 应用编号
     * @param nos 实验室检查类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除实验室检查类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
