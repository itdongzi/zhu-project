package com.benet.system.mapper;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysConfiginfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 参数配置Mapper接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Mapper
public interface SysConfiginfoMapper {
    /**
     * 查询所有参数配置列表
     *
     * @param appCode 应用编号
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getAllRecords(@Param("appCode") String appCode);

    /**
     * 按分类查询参数配置列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByClassNo(@Param("appCode") String appCode, @Param("classNo") String classNo);

    /**
     * 分页查询参数配置列表
     *
     * @param appCode 应用编号
     * @param model   分页模型
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByPaging(@Param("appCode") String appCode, @Param("model") PagingModel model);

    /**
     * 查询参数配置
     *
     * @param appCode 应用编号
     * @param no      参数配置ID
     * @return 参数配置
     */
    public SysConfiginfo getRecordByNo(@Param("appCode") String appCode, @Param("no") String no);


    /**
     * 查询参数配置
     *
     * @param appCode 应用编号
     * @param configKey      参数配置ID
     * @return 参数配置
     */
    public SysConfiginfo getRecordByConfigKey(@Param("appCode") String appCode, @Param("configKey") String configKey);

    /**
     * 查询参数配置名称
     *
     * @param appCode 应用编号
     * @param no      参数配置ID
     * @return 名称
     */
    public String getRecordNameByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 查询参数配置计数
     *
     * @param appCode   应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 新增参数配置
     *
     * @param info 参数配置
     * @return 结果
     */
    public int AddNewRecord(@Param("info") SysConfiginfo info);

    /**
     * 更新参数配置
     *
     * @param info 参数配置
     * @return 结果
     */
    public int UpdateRecord(@Param("info") SysConfiginfo info);

    /**
     * 硬删除参数配置
     *
     * @param appCode 应用编号
     * @param no      参数配置ID
     * @return 结果
     */
    public int HardDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量硬删除参数配置
     *
     * @param appCode 应用编号
     * @param nos     参数配置IDs
     * @return 结果
     */
    public int HardDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件硬删除参数配置
     *
     * @param appCode   应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);

    /**
     * 软删除参数配置
     *
     * @param appCode 应用编号
     * @param no      参数配置ID
     * @return 结果
     */
    public int SoftDeleteByNo(@Param("appCode") String appCode, @Param("no") String no);

    /**
     * 批量软删除参数配置
     *
     * @param appCode 应用编号
     * @param nos     参数配置IDs
     * @return 结果
     */
    public int SoftDeleteByNos(@Param("appCode") String appCode, @Param("nos") String[] nos);

    /**
     * 按条件软删除参数配置
     *
     * @param appCode   应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(@Param("appCode") String appCode, @Param("condition") String condition);


    /**
     * 校验参数键名是否唯一
     *
     * @param configKey  参数键名
     * @return 结果
     */
    public int checkConfigKeyUnique(@Param("appCode") String appCode, @Param("configKey") String configKey);

    /**
     * 根据键名返回键值
     *
     * @param configKey  参数键名
     * @return 参数键值
     */
    public String getConfigValueByKey(@Param("appCode") String appCode, @Param("configKey") String configKey);

}
