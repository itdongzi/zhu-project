package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysPermitinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单权限Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysPermitinfoMapper 
{
    /**
     * 查询所有菜单权限列表
     *
     * @param appCode 应用编号
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询菜单权限列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 菜单权限集合
     */
    public List<SysPermitinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 菜单权限
     */
    public SysPermitinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询菜单权限名称
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询菜单权限计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增菜单权限
     *
     * @param info 菜单权限
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysPermitinfo info);

    /**
     * 更新菜单权限
     *
     * @param info 菜单权限
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysPermitinfo info);

    /**
     * 硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param nos 菜单权限IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除菜单权限
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除菜单权限
     *
     * @param appCode 应用编号
     * @param no 菜单权限ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除菜单权限
     *
     * @param appCode 应用编号
     * @param nos 菜单权限IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除菜单权限
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);


    /**
     * 根据角色ID查询菜单
     *
     * @param roleNo 角色ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getRecordsByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);

    /**
     * 根据用户ID查询菜单
     *
     * @param userNo 用户ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getRecordsByUserNo(@Param("appCode") String appCode,@Param("userNo") String userNo);

    /**
     * 根据用户ID查询子菜单
     *
     * @param parentNo 父ID
     * @param userNo 用户ID
     * @return 菜单列表
     */
    public List<SysPermitinfo> getChildrenByUserNo(@Param("appCode") String appCode,@Param("parentNo") String parentNo,@Param("userNo") String userNo);


    /**
     * 根据角色ID查询权限标识
     *
     * @param roleNo 角色ID
     * @return 菜单列表
     */
    public List<String> getPermitCodesByRoleNo(@Param("appCode") String appCode,@Param("roleNo") String roleNo);

    /**
     * 根据用户ID查询权限标识
     *
     * @param userNo 用户ID
     * @return 权限列表
     */
    public List<String> getPermitCodesByUserNo(@Param("appCode") String appCode,@Param("userNo") String userNo);

    /**
     * 校验菜单名称是否唯一
     *
     * @param permitName 菜单名称
     * @param parentNo 父菜单ID
     * @return 结果
     */
    public int checkPermitNameUnique(@Param("appCode") String appCode,@Param("permitName") String permitName, @Param("parentNo") String parentNo);

}
