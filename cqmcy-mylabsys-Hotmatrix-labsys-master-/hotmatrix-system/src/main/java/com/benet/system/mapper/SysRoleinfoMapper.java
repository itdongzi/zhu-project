package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRoleinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysRoleinfoMapper 
{
    /**
     * 查询所有角色信息列表
     *
     * @param appCode 应用编号
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询角色信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询角色信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 角色信息集合
     */
    public List<SysRoleinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 角色信息
     */
    public SysRoleinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询角色信息名称
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询角色信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增角色信息
     *
     * @param info 角色信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRoleinfo info);

    /**
     * 更新角色信息
     *
     * @param info 角色信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysRoleinfo info);

    /**
     * 硬删除角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除角色信息
     *
     * @param appCode 应用编号
     * @param nos 角色信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除角色信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除角色信息
     *
     * @param appCode 应用编号
     * @param no 角色信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除角色信息
     *
     * @param appCode 应用编号
     * @param nos 角色信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除角色信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);


    /**
     * 根据用户ID查询角色
     *
     * @param userNo 用户ID
     * @return 角色列表
     */
    public List<SysRoleinfo> getRecordsByUserNo(@Param("appCode") String appCode,@Param("userNo") String userNo);

    /**
     * 根据用户ID查询角色标识
     *
     * @param userNo 用户ID
     * @return 角色列表
     */
    public List<String> getRoleCodesByUserNo(@Param("appCode") String appCode,@Param("userNo") String userNo);

    /**
     * 根据用户ID查询角色标识
     *
     * @param roleNo 角色ID
     * @return 用户列表
     */
    public List<String> getSuserNosByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);


    /**
     * 根据用户ID查询角色标识
     *
     * @param roleNo 角色ID
     * @return 权限列表
     */
    public List<String> getPermitNosByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    public int checkRoleNameUnique(@Param("appCode") String appCode,@Param("roleName") String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleCode 角色权限
     * @return 角色信息
     */
    public int checkRoleCodeUnique(@Param("appCode") String appCode,@Param("roleCode") String roleCode);
}
