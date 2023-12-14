package com.benet.task.service.impl;

import java.util.List;
import com.benet.common.configure.GlobalConfig;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.task.domain.SysTaskelog;
import com.benet.task.mapper.SysTaskelogMapper;
import com.benet.task.service.ISysTaskelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 定时任务调度日志Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysTaskelogServiceImpl implements ISysTaskelogService
{
    @Autowired
    private SysTaskelogMapper sysTaskelogMapper;

    /**
     * 查询所有定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTaskelog> getAllRecords(String appCode) {
        return sysTaskelogMapper.getAllRecords(GlobalConfig.getAppCode());
    }

    /**
     * 按分类查询定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTaskelog> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysTaskelogMapper.getRecordsByClassNo(GlobalConfig.getAppCode(),classNo);
        }
        return null;
    }

    /**
     * 分页查询定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTaskelog> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysTaskelogMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
        }
        return null;
    }


    /**
     * 分页查询定时任务调度日志列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 定时任务调度日志集合
     */
    @Override
    public List<SysTaskelog> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return sysTaskelogMapper.getRecordsByPaging(GlobalConfig.getAppCode(),model);
    }

    /**
     * 查询定时任务调度日志
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 定时任务调度日志
     */
    @Override
    public SysTaskelog getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskelogMapper.getRecordByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度日志名称
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskelogMapper.getRecordNameByNo(GlobalConfig.getAppCode(),no);
        }
        return null;
    }

    /**
     * 查询定时任务调度日志计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysTaskelogMapper.getCountByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 新增定时任务调度日志
     *
     * @param appCode 应用编号
     * @param info 定时任务调度日志
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysTaskelog info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        info.setVersion(1L);
        return sysTaskelogMapper.AddNewRecord(info);
    }

    /**
     * 更新定时任务调度日志
     *
     * @param appCode 应用编号
     * @param info 定时任务调度日志
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysTaskelog info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(GlobalConfig.getAppCode());
        return sysTaskelogMapper.UpdateRecord(info);
    }

    /**
     * 硬删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskelogMapper.HardDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量硬删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTaskelogMapper.HardDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysTaskelogMapper.HardDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }

    /**
     * 软删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param no 定时任务调度日志ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysTaskelogMapper.SoftDeleteByNo(GlobalConfig.getAppCode(),no);
        }
        return 0;
    }

    /**
     * 批量软删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param nos 定时任务调度日志IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysTaskelogMapper.SoftDeleteByNos(GlobalConfig.getAppCode(),nos);
        }
        return 0;
    }

    /**
     * 按条件软删除定时任务调度日志
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysTaskelogMapper.SoftDeleteByCondition(GlobalConfig.getAppCode(),condition);
    }
}
