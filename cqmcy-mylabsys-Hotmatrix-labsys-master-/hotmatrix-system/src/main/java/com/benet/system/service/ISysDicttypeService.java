package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysDicttype;

/**
 * 字典类型Service接口
 * 
 * @author yoxking
 * @date 2020-04-23
 */
public interface ISysDicttypeService 
{
    /**
     * 查询所有字典类型列表
     *
     * @param appCode 应用编号
     * @return 字典类型集合
     */
    public List<SysDicttype> getAllRecords(String appCode);

    /**
     * 按分类查询字典类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询字典类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询字典类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 字典类型集合
     */
    public List<SysDicttype> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询字典类型
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 字典类型
     */
    public SysDicttype getRecordByNo(String appCode,String no);

    /**
     * 查询字典类型名称
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询字典类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增字典类型
     *
     * @param appCode 应用编号
     * @param info 字典类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysDicttype info);

    /**
     * 更新字典类型
     *
     * @param appCode 应用编号
     * @param info 字典类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysDicttype info);

    /**
     * 硬删除字典类型
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除字典类型
     *
     * @param appCode 应用编号
     * @param nos 字典类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除字典类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除字典类型
     *
     * @param appCode 应用编号
     * @param no 字典类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除字典类型
     *
     * @param appCode 应用编号
     * @param nos 字典类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除字典类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
