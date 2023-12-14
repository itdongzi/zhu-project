package com.benet.system.mapper;

import com.benet.system.domain.SysRolepermit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色和菜单关联Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysRolepermitMapper 
{
    /**
     * 查询角色和菜单关联计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增角色和菜单关联
     *
     * @param info 角色和菜单关联
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRolepermit info);

    /**
     * 通过角色ID删除角色和菜单关联
     *
     * @param roleNo 角色ID
     * @return 结果
     */
    public int HardDeleteByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);

    /**
     * 通过权限ID删除角色和菜单关联
     *
     * @param permitNo 权限ID
     * @return 结果
     */
    public int HardDeleteByPermitNo(@Param("appCode") String appCode,@Param("permitNo") String permitNo);

}
