package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysLogininfo;

/**
 * 系统访问记录Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysLogininfoService
{
    /**
     * 查询所有系统访问记录列表
     *
     * @param appCode 应用编号
     * @return 系统访问记录集合
     */
    public List<SysLogininfo> getAllRecords(String appCode);

    /**
     * 按分类查询系统访问记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 系统访问记录集合
     */
    public List<SysLogininfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询系统访问记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 系统访问记录集合
     */
    public List<SysLogininfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询系统访问记录列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统访问记录集合
     */
    public List<SysLogininfo> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询系统访问记录
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 系统访问记录
     */
    public SysLogininfo getRecordByNo(String appCode,String no);

    /**
     * 查询系统访问记录名称
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询系统访问记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增系统访问记录
     *
     * @param appCode 应用编号
     * @param info 系统访问记录
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysLogininfo info);

    /**
     * 更新系统访问记录
     *
     * @param appCode 应用编号
     * @param info 系统访问记录
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysLogininfo info);

    /**
     * 硬删除系统访问记录
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除系统访问记录
     *
     * @param appCode 应用编号
     * @param nos 系统访问记录IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除系统访问记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除系统访问记录
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除系统访问记录
     *
     * @param appCode 应用编号
     * @param nos 系统访问记录IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除系统访问记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
