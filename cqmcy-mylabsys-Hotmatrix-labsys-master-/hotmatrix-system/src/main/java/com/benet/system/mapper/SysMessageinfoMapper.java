package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysMessageinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消息信息Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysMessageinfoMapper 
{
    /**
     * 查询所有消息信息列表
     *
     * @param appCode 应用编号
     * @return 消息信息集合
     */
    public List<SysMessageinfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询消息信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 消息信息集合
     */
    public List<SysMessageinfo> getRecordsByClassNo(@Param("appCode") String appCode,@Param("classNo") String classNo);

    /**
     * 分页查询消息信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 消息信息集合
     */
    public List<SysMessageinfo> getRecordsByPaging(@Param("appCode") String appCode,@Param("model") PagingModel model);

    /**
     * 查询消息信息
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 消息信息
     */
    public SysMessageinfo getRecordByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询消息信息名称
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 查询消息信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 新增消息信息
     *
     * @param info 消息信息
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysMessageinfo info);

    /**
     * 更新消息信息
     *
     * @param info 消息信息
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysMessageinfo info);

    /**
     * 硬删除消息信息
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量硬删除消息信息
     *
     * @param appCode 应用编号
     * @param nos 消息信息IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件硬删除消息信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

    /**
     * 软删除消息信息
     *
     * @param appCode 应用编号
     * @param no 消息信息ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode,@Param("no") String no);

    /**
     * 批量软删除消息信息
     *
     * @param appCode 应用编号
     * @param nos 消息信息IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode,@Param("nos") String[] nos);

    /**
     * 按条件软删除消息信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode,@Param("condition") String condition);

}
