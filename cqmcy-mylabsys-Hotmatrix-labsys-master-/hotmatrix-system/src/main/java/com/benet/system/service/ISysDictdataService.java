package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDictdata;

/**
 * 字典数据Service接口
 * 
 * @author yoxking
 * @date 2020-04-23
 */
public interface ISysDictdataService 
{
    /**
     * 查询所有字典数据列表
     *
     * @param appCode 应用编号
     * @return 字典数据集合
     */
    public List<SysDictdata> getAllRecords(String appCode);

    /**
     * 按分类查询字典数据列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 字典数据集合
     */
    public List<SysDictdata> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询字典数据列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 字典数据集合
     */
    public List<SysDictdata> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询字典数据列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 字典数据集合
     */
    public List<SysDictdata> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 字典数据
     */
    public SysDictdata getRecordByNo(String appCode,String no);

    /**
     * 查询字典数据名称
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询字典数据计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增字典数据
     *
     * @param appCode 应用编号
     * @param info 字典数据
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysDictdata info);

    /**
     * 更新字典数据
     *
     * @param appCode 应用编号
     * @param info 字典数据
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysDictdata info);

    /**
     * 硬删除字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除字典数据
     *
     * @param appCode 应用编号
     * @param nos 字典数据IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除字典数据
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除字典数据
     *
     * @param appCode 应用编号
     * @param no 字典数据ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除字典数据
     *
     * @param appCode 应用编号
     * @param nos 字典数据IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除字典数据
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
