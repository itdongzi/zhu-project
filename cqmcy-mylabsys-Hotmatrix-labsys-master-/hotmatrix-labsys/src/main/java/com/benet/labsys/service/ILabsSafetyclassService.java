package com.benet.labsys.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.labsys.domain.LabsSafetyclass;

/**
 * 安全资料分类Service接口
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
public interface ILabsSafetyclassService 
{
    /**
     * 查询所有安全资料分类列表
     *
     * @param appCode 应用编号
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getAllRecords(String appCode);

    /**
     * 按分类查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询安全资料分类列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 安全资料分类集合
     */
    public List<LabsSafetyclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 安全资料分类
     */
    public LabsSafetyclass getRecordByNo(String appCode,String no);

    /**
     * 查询安全资料分类名称
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询安全资料分类计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增安全资料分类
     *
     * @param appCode 应用编号
     * @param info 安全资料分类
     * @return 结果
     */
    public int AddNewRecord(String appCode,LabsSafetyclass info);

    /**
     * 更新安全资料分类
     *
     * @param appCode 应用编号
     * @param info 安全资料分类
     * @return 结果
     */
    public int UpdateRecord(String appCode,LabsSafetyclass info);

    /**
     * 硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param nos 安全资料分类IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除安全资料分类
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param no 安全资料分类ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param nos 安全资料分类IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除安全资料分类
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
