package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsWorklog;

/**
 * 实训室工作/检查记录Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
public interface ILabsWorklogService 
{
    /**
     * 查询所有实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @return 实训室工作/检查记录集合
     */
    public List<LabsWorklog> getAllRecords(String appCode);

    /**
     * 按分类查询实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实训室工作/检查记录集合
     */
    public List<LabsWorklog> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实训室工作/检查记录集合
     */
    public List<LabsWorklog> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实训室工作/检查记录集合
     */
    public List<LabsWorklog> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 实训室工作/检查记录
     */
    public LabsWorklog getRecordByNo(String appCode,String no);

    /**
     * 查询实训室工作/检查记录名称
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询实训室工作/检查记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param info 实训室工作/检查记录
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsWorklog info);

    /**
     * 更新实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param info 实训室工作/检查记录
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsWorklog info);

    /**
     * 硬删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param nos 实训室工作/检查记录IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param nos 实训室工作/检查记录IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
