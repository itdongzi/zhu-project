package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRenteclass;

/**
 * 租户类型Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysRenteclassService 
{
    /**
     * 查询所有租户类型列表
     *
     * @param appCode 应用编号
     * @return 租户类型集合
     */
    public List<SysRenteclass> getAllRecords(String appCode);

    /**
     * 按分类查询租户类型列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 租户类型集合
     */
    public List<SysRenteclass> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询租户类型列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 租户类型集合
     */
    public List<SysRenteclass> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询租户类型列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 租户类型集合
     */
    public List<SysRenteclass> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType);

    /**
     * 查询租户类型
     *
     * @param appCode 应用编号
     * @param no 租户类型ID
     * @return 租户类型
     */
    public SysRenteclass getRecordByNo(String appCode,String no);

    /**
     * 查询租户类型名称
     *
     * @param appCode 应用编号
     * @param no 租户类型ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询租户类型计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增租户类型
     *
     * @param appCode 应用编号
     * @param info 租户类型
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysRenteclass info);

    /**
     * 更新租户类型
     *
     * @param appCode 应用编号
     * @param info 租户类型
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysRenteclass info);

    /**
     * 硬删除租户类型
     *
     * @param appCode 应用编号
     * @param no 租户类型ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除租户类型
     *
     * @param appCode 应用编号
     * @param nos 租户类型IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除租户类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除租户类型
     *
     * @param appCode 应用编号
     * @param no 租户类型ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除租户类型
     *
     * @param appCode 应用编号
     * @param nos 租户类型IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除租户类型
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
