package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.uuid.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysConfiginfoMapper;
import com.benet.system.domain.SysConfiginfo;
import com.benet.system.service.ISysConfiginfoService;

/**
 * 参数配置Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysConfiginfoServiceImpl implements ISysConfiginfoService 
{
    @Autowired
    private SysConfiginfoMapper sysConfiginfoMapper;

    /**
     * 查询所有参数配置列表
     *
     * @param appCode 应用编号
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getAllRecords(String appCode) {
        return sysConfiginfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询参数配置列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysConfiginfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询参数配置列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysConfiginfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询参数配置列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 参数配置集合
     */
    @Override
    public List<SysConfiginfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

        PagingModel model = new PagingModel();
        model.setPageIndex((pageIndex-1) * pageSize);
        model.setPageSize(pageSize);
        model.setCondition(condition);
        if (StringUtils.isEmpty(orderField)) {
            model.setOrderField("id");
        } else {
            model.setOrderField(orderField);
        }
        if (StringUtils.isEmpty(orderType)) {
            model.setOrderType("Asc");
        } else {
            model.setOrderType(orderType);
        }
        return sysConfiginfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询参数配置
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 参数配置
     */
    @Override
    public SysConfiginfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询参数配置
     *
     * @param appCode 应用编号
     * @param configKey 参数配置Key
     * @return 参数配置
     */
    @Override
    public SysConfiginfo getRecordByConfigKey(String appCode,String configKey) {
        if (StringUtils.isNotEmpty(configKey)) {
            return sysConfiginfoMapper.getRecordByConfigKey(appCode,configKey);
        }
        return null;
    }

    /**
     * 查询参数配置名称
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询参数配置计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysConfiginfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增参数配置
     *
     * @param appCode 应用编号
     * @param info 参数配置
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysConfiginfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysConfiginfoMapper.AddNewRecord(info);
    }

    /**
     * 更新参数配置
     *
     * @param appCode 应用编号
     * @param info 参数配置
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysConfiginfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysConfiginfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除参数配置
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除参数配置
     *
     * @param appCode 应用编号
     * @param nos 参数配置IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysConfiginfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除参数配置
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysConfiginfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除参数配置
     *
     * @param appCode 应用编号
     * @param no 参数配置ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysConfiginfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除参数配置
     *
     * @param appCode 应用编号
     * @param nos 参数配置IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysConfiginfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除参数配置
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysConfiginfoMapper.SoftDeleteByCondition(appCode,condition);
    }


    /**
     * 校验参数键名是否唯一
     *
     * @param appCode 应用编号
     * @param configKey 参数键名
     * @return 结果
     */
    @Override
    public int checkConfigKeyUnique(String appCode,String configKey){
        return sysConfiginfoMapper.checkConfigKeyUnique(appCode,configKey);
    }

    /**
     * 根据键名返回键值
     *
     * @param appCode 应用编号
     * @param configKey 参数键名
     * @return 参数键值
     */
    @Override
    public String getConfigValueByKey(String appCode,String configKey){
        return sysConfiginfoMapper.getConfigValueByKey(appCode,configKey);
    }


    /**
     * 保存参数配置
     *
     * @param appCode 应用编号
     * @param configKey 参数配置
     * @param configValue 参数配置
     * @param configType 参数配置
     * @return 结果
     */
    public int saveConfigValueByKey(String appCode,String configKey,String configValue,String configType){
        SysConfiginfo info=sysConfiginfoMapper.getRecordByConfigKey(appCode,configKey);
        if(info==null) {
            info=new SysConfiginfo();
            info.setConfigNo(UuidUtils.shortUUID());
            info.setConfigName(configKey);
            info.setConfigKey(configKey);
            info.setConfigValue(configValue);
            info.setConfigType(configType);
            info.setCheckState("1");
            info.setBranchNo(GlobalConfig.getBranchNo());
            info.setCreateBy("admin");
            info.setCreateTime(DateTimeUtils.getNowDate());
            info.setUpdateBy("admin");
            info.setUpdateTime(DateTimeUtils.getNowDate());
            info.setDeleteFlag("1");
            info.setComments("");
            info.setAppCode(appCode);
            info.setVersion(1L);

            return sysConfiginfoMapper.AddNewRecord(info);
        }else{
            info.setConfigValue(configValue);
            info.setUpdateTime(DateTimeUtils.getNowDate());
            return sysConfiginfoMapper.UpdateRecord(info);
        }
    }
}
