package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysMenusinfo;

/**
 * 控制台菜单信息Service接口
 * 
 * @author yoxking
 * @date 2022-09-08 14:13:52
 */
public interface ISysMenusinfoService 
{
    /**
     * 查询所有控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getAllRecords(String appCode);

    /**
     * 按分类查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 按分类查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param parentNo 父类编号
     * @param userType 用户类型
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByUserType(String appCode,String parentNo,String userType);

    /**
     * 分页查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 控制台菜单信息
     */
    public SysMenusinfo getRecordByNo(String appCode,String no);

    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param menuCode 控制台菜单信息Code
     * @return 控制台菜单信息
     */
    public SysMenusinfo getRecordByMenuCode(String appCode,String menuCode);

    /**
     * 查询控制台菜单信息名称
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询控制台菜单信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增控制台菜单信息
     *
     * @param appCode 应用编号
     * @param info 控制台菜单信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysMenusinfo info);

    /**
     * 更新控制台菜单信息
     *
     * @param appCode 应用编号
     * @param info 控制台菜单信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysMenusinfo info);

    /**
     * 硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param nos 控制台菜单信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param nos 控制台菜单信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);
}
