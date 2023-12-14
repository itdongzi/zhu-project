package com.benet.system.mapper;

import com.benet.system.domain.SysSuserrole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户和角色关联Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysSuserroleMapper 
{
    /**
     * 查询用户和角色关联计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增用户和角色关联
     *
     * @param info 用户和角色关联
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysSuserrole info);

    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userNo 用户ID
     * @return 结果
     */
    public int HardDeleteByUserNo(@Param("appCode") String appCode,@Param("userNo") String userNo);

    /**
     * 通过角色ID删除用户和角色关联
     *
     * @param roleNo 角色ID
     * @return 结果
     */
    public int HardDeleteByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);
}
