package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRotaclass;

/**
 * 值班类型Service接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
public interface ISysRotaclassService 
{
    /**
     * 查询所有值班类型列表
     *
     * @param appCode 应用编号
     * @return 值班类型集合
     */
    public List<SysRotaclass> getAllRecords(String appCode);

    /**
     * 按分类查询值班类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 值班类型集合
     */
    public List<SysRotaclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询值班类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 值班类型集合
     */
    public List<SysRotaclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询值班类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 值班类型集合
     */
    public List<SysRotaclass> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 值班类型
     */
    public SysRotaclass getRecordByNo(String appCode,String no);

    /**
     * 查询值班类型名称
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询值班类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增值班类型
     *
     * @param appCode 应用编号
     * @param info 值班类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysRotaclass info);

    /**
     * 更新值班类型
     *
     * @param appCode 应用编号
     * @param info 值班类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysRotaclass info);

    /**
     * 硬删除值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除值班类型
     *
     * @param appCode 应用编号
     * @param nos 值班类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除值班类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除值班类型
     *
     * @param appCode 应用编号
     * @param no 值班类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除值班类型
     *
     * @param appCode 应用编号
     * @param nos 值班类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除值班类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
