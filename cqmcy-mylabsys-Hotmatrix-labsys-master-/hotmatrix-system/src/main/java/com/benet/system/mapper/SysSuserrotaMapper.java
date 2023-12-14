package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysSuserrota;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户排班Mapper接口
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Mapper
public interface SysSuserrotaMapper 
{
    /**
     * 查询所有系统用户排班列表
     *
     * @param appCode 应用编号
     * @return 系统用户排班集合
     */
    public List<SysSuserrota> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询系统用户排班列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 系统用户排班集合
     */
    public List<SysSuserrota> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询系统用户排班列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 系统用户排班集合
     */
    public List<SysSuserrota> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询系统用户排班
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 系统用户排班
     */
    public SysSuserrota getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询系统用户排班名称
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询系统用户排班计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增系统用户排班
     *
     * @param info 系统用户排班
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysSuserrota info);

    /**
     * 更新系统用户排班
     *
     * @param info 系统用户排班
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysSuserrota info);

    /**
     * 硬删除系统用户排班
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除系统用户排班
     *
     * @param appCode 应用编号
     * @param nos 系统用户排班IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除系统用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除系统用户排班
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除系统用户排班
     *
     * @param appCode 应用编号
     * @param nos 系统用户排班IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除系统用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
