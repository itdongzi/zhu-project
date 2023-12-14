package com.benet.system.mapper;

import com.benet.system.domain.SysSuserpost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户与岗位关联Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysSuserpostMapper 
{
    /**
     * 查询用户与岗位关联计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增用户与岗位关联
     *
     * @param info 用户与岗位关联
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysSuserpost info);


    /**
     * 通过角色ID删除用户和岗位关联
     *
     * @param userNo 用户ID
     * @return 结果
     */
    public int HardDeleteByUserNo(@Param("appCode") String appCode,@Param("userNo") String userNo);

    /**
     * 通过部门ID删除用户和岗位关联
     *
     * @param postNo 岗位ID
     * @return 结果
     */
    public int HardDeleteByPostNo(@Param("appCode") String appCode,@Param("postNo") String postNo);

}
