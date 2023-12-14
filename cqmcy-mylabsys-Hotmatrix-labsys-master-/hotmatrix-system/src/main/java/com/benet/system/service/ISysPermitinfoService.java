package com.benet.system.service;

import java.util.List;
import java.util.Set;

import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysPermitinfo;

/**
 * 菜单权限Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysPermitinfoService 
{
    /**
     * 查询所有菜单权限列表
     *
     * @param appCode 应用编号
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getAllRecords(String appCode);

    /**
     * 按分类查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 菜单权限
     */
    public SysPermitinfo getRecordByNo(String appCode,String no);

    /**
     * 查询菜单权限名称
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询菜单权限计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增菜单权限
     *
     * @param appCode 应用编号
     * @param info 菜单权限
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysPermitinfo info);

    /**
     * 更新菜单权限
     *
     * @param appCode 应用编号
     * @param info 菜单权限
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysPermitinfo info);

    /**
     * 硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param nos 菜单权限IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除菜单权限
     *
     * @param appCode 应用编号
     * @param nos 菜单权限IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除菜单权限
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);


    /**
     * 根据角色ID查询菜单
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getRecordsByRoleNo(String appCode,String roleNo);

    /**
     * 根据用户ID查询菜单
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getRecordsByUserNo(String appCode,String userNo);

    /**
     * 根据用户ID查询子菜单
     *
     * @param appCode 应用编号
     * @param parentNo 父ID
     * @param userNo 用户ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getChildrenByUserNo(String appCode,String parentNo,String userNo);

    /**
     * 根据用户ID查询建菜单树
     *
     * @param appCode 应用编号
     * @param parentNo 父ID
     * @param userNo 用户ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getPermitTreeByUserNo(String appCode,String parentNo,String userNo);

    /**
     * 根据角色ID查询菜单
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 菜单列表
     */
    public Set<String> getPermitCodesByRoleNo(String appCode,String roleNo);

    /**
     * 根据用户ID查询权限
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 权限列表
     */
    public Set<String> getPermitCodesByUserNo(String appCode,String userNo);

    /**
     * 校验菜单名称是否唯一
     *
     * @param appCode 应用编号
     * @param permitName 菜单名称
     * @param parentNo 父菜单ID
     * @return 结果
     */
    public int checkPermitNameUnique(String appCode,String permitName, String parentNo);

}
