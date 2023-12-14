package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysMenusinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 控制台菜单信息Mapper接口
 * 
 * @author yoxking
 * @date 2022-09-08 14:13:52
 */
@Mapper
public interface SysMenusinfoMapper 
{
    /**
     * 查询所有控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 按分类查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param parentNo 父类编号
     * @param userType 用户类型
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByUserType(@Param("appCode") String appCode,@Param("parentNo") String parentNo,@Param("userType") String userType);


    /**
     * 分页查询控制台菜单信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 控制台菜单信息集合
     */
    public List<SysMenusinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 控制台菜单信息
     */
    public SysMenusinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);


    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param menuCode 控制台菜单信息Code
     * @return 控制台菜单信息
     */
    public SysMenusinfo getRecordByMenuCode(@Param("appCode") String appCode,@Param("menuCode") String menuCode);

    /**
     * 查询控制台菜单信息
     *
     * @param appCode 应用编号
     * @param menuCode 控制台菜单信息Code
     * @return 控制台菜单信息
     */
    public SysMenusinfo getRecordByCode(String appCode,String menuCode);

    /**
     * 查询控制台菜单信息名称
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询控制台菜单信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增控制台菜单信息
     *
     * @param info 控制台菜单信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysMenusinfo info);

    /**
     * 更新控制台菜单信息
     *
     * @param info 控制台菜单信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysMenusinfo info);

    /**
     * 硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param nos 控制台菜单信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param no 控制台菜单信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param nos 控制台菜单信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除控制台菜单信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
