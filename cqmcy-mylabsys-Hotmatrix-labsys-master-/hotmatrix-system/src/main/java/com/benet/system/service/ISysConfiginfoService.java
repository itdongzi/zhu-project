package com.benet.system.service;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.system.domain.SysConfiginfo;

/**
 * 参数配置Service接口
 * 
 * @author yoxking
 * @date 2020-04-06
 */
public interface ISysConfiginfoService 
{
    /**
     * 查询所有参数配置列表
     *
     * @param appCode 应用编号
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getAllRecords(String appCode);

    /**
     * 按分类查询参数配置列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByClassNo(String appCode,String classNo);

    /**
     * 分页查询参数配置列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByPaging(String appCode,PagingModel model);

    /**
     * 分页查询参数配置列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页索引
     * @param pageSize 分页大小
     * @param condition 分页条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 参数配置集合
     */
    public List<SysConfiginfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType);

    /**
     * 查询参数配置
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 参数配置
     */
    public SysConfiginfo getRecordByNo(String appCode,String no);


    /**
     * 查询参数配置
     *
     * @param appCode 应用编号
     * @param configKey 参数配置ID
     * @return 参数配置
     */
    public SysConfiginfo getRecordByConfigKey(String appCode,String configKey);

    /**
     * 查询参数配置名称
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 名称
     */
    public String getRecordNameByNo(String appCode,String no);

    /**
     * 查询参数配置计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    public int getCountByCondition(String appCode,String condition);

    /**
     * 新增参数配置
     *
     * @param appCode 应用编号
     * @param info 参数配置
     * @return 结果
     */
    public int AddNewRecord(String appCode,SysConfiginfo info);

    /**
     * 更新参数配置
     *
     * @param appCode 应用编号
     * @param info 参数配置
     * @return 结果
     */
    public int UpdateRecord(String appCode,SysConfiginfo info);

    /**
     * 硬删除参数配置
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 结果
     */
    public int HardDeleteByNo(String appCode,String no);

    /**
     * 批量硬删除参数配置
     *
     * @param appCode 应用编号
     * @param nos 参数配置IDs
     * @return 结果
     */
    public int HardDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件硬删除参数配置
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int HardDeleteByCondition(String appCode,String condition);

    /**
     * 软删除参数配置
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 结果
     */
    public int SoftDeleteByNo(String appCode,String no);

    /**
     * 批量软删除参数配置
     *
     * @param appCode 应用编号
     * @param nos 参数配置IDs
     * @return 结果
     */
    public int SoftDeleteByNos(String appCode,String[] nos);

    /**
     * 按条件软删除参数配置
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    public int SoftDeleteByCondition(String appCode,String condition);


    /**
     * 校验参数键名是否唯一
     *
     * @param appCode 应用编号
     * @param configKey 参数键名
     * @return 结果
     */
    public int checkConfigKeyUnique(String appCode,String configKey);

    /**
     * 根据键名返回键值
     *
     * @param appCode 应用编号
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getConfigValueByKey(String appCode,String configKey);

    /**
     * 保存参数配置
     *
     * @param appCode 应用编号
     * @param configKey 参数配置
     * @param configValue 参数配置
     * @param configType 参数配置
     * @return 结果
     */
    public int saveConfigValueByKey(String appCode,String configKey,String configValue,String configType);
}
