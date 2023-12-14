package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysRuserrota;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 注册用户排班Mapper接口
 * 
 * @author yoxking
 * @date 2020-10-27
 */
@Mapper
public interface SysRuserrotaMapper 
{
    /**
     * 查询所有注册用户排班列表
     *
     * @param appCode 应用编号
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 注册用户排班集合
     */
    public List<SysRuserrota> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 注册用户排班
     */
    public SysRuserrota getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询注册用户排班名称
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询注册用户排班计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增注册用户排班
     *
     * @param info 注册用户排班
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysRuserrota info);

    /**
     * 更新注册用户排班
     *
     * @param info 注册用户排班
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysRuserrota info);

    /**
     * 硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param nos 注册用户排班IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param nos 注册用户排班IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

}
