package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDepartment;

/**
 * 部门信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysDepartmentService 
{
    /**
     * 查询所有部门信息列表
     *
     * @param appCode 应用编号
     * @return 部门信息集合
     */
    public List<SysDepartment> getAllRecords(String appCode);

    /**
     * 按分类查询部门信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询部门信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询部门信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 部门信息集合
     */
    public List<SysDepartment> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 部门信息
     */
    public SysDepartment getRecordByNo(String appCode,String no);

    /**
     * 查询部门信息名称
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询部门信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增部门信息
     *
     * @param appCode 应用编号
     * @param info 部门信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysDepartment info);

    /**
     * 更新部门信息
     *
     * @param appCode 应用编号
     * @param info 部门信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysDepartment info);

    /**
     * 硬删除部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除部门信息
     *
     * @param appCode 应用编号
     * @param nos 部门信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除部门信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除部门信息
     *
     * @param appCode 应用编号
     * @param nos 部门信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除部门信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
