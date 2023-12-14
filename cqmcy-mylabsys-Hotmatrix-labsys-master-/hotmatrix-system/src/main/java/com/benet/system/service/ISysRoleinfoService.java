package com.benet.system.service;

import java.util.List;
import java.util.Set;

import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRoleinfo;

/**
 * 角色信息Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysRoleinfoService 
{
    /**
     * 查询所有角色信息列表
     *
     * @param appCode 应用编号
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getAllRecords(String appCode);

    /**
     * 按分类查询角色信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询角色信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询角色信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 角色信息
     */
    public SysRoleinfo getRecordByNo(String appCode,String no);

    /**
     * 查询角色信息名称
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询角色信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增角色信息
     *
     * @param appCode 应用编号
     * @param info 角色信息
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysRoleinfo info);

    /**
     * 更新角色信息
     *
     * @param appCode 应用编号
     * @param info 角色信息
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysRoleinfo info);


    /**
     * 更新角色用户信息
     *
     * @param appCode 应用编号
     * @param roleNo 角色编号
     * @param suerNos 用户列表信息
     * @return 结果
     */
    public int UpdateSusers(String appCode,String roleNo,String[] suerNos);

    /**
     * 更新角色权限信息
     *
     * @param appCode 应用编号
     * @param roleNo 角色编号
     * @param permitNos 权限列表信息
     * @return 结果
     */
    public int UpdatePermits(String appCode,String roleNo,String[] permitNos);

    /**
     * 硬删除角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除角色信息
     *
     * @param appCode 应用编号
     * @param nos 角色信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除角色信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除角色信息
     *
     * @param appCode 应用编号
     * @param nos 角色信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除角色信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);


    /**
     * 根据用户ID查询角色
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 角色列表
     */
    public List<SysRoleinfo> getRecordsByUserNo(String appCode,String userNo);


    /**
     * 根据用户ID查询角色标识
     *
     * @param appCode 应用编号
     * @param userNo 用户ID
     * @return 权限列表
     */
    public Set<String> getRoleCodesByUserNo(String appCode,String userNo);

    /**
     * 根据用户ID查询角色标识
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 用户列表
     */
    public List<String> getSuserNosByRoleNo(String appCode,String roleNo);


    /**
     * 根据用户ID查询角色标识
     *
     * @param appCode 应用编号
     * @param roleNo 角色ID
     * @return 权限列表
     */
    public List<String> getPermitNosByRoleNo(String appCode,String roleNo);


    /**
     * 校验角色名称是否唯一
     *
     * @param appCode 应用编号
     * @param roleName 角色名称
     * @return 角色信息
     */
    public int checkRoleNameUnique(String appCode,String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param appCode 应用编号
     * @param roleCode 角色权限
     * @return 角色信息
     */
    public int checkRoleCodeUnique(String appCode,String roleCode);
}
