package com.benet.system.mapper;

import com.benet.system.domain.SysRoledept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色和部门关联Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysRoledeptMapper 
{
    /**
     * 查询角色和部门关联计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增角色和部门关联
     *
     * @param info 角色和部门关联
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRoledept info);

    /**
     * 通过角色ID删除角色和部门关联
     *
     * @param roleNo 角色ID
     * @return 结果
     */
    public int HardDeleteByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);

    /**
     * 通过部门ID删除角色和部门关联
     *
     * @param deptNo 部门ID
     * @return 结果
     */
    public int HardDeleteByDeptNo(@Param("appCode") String appCode,@Param("deptNo") String deptNo);
}
