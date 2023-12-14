package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysOperatelogMapper;
import com.benet.system.domain.SysOperatelog;
import com.benet.system.service.ISysOperatelogService;

/**
 * 操作日志记录Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysOperatelogServiceImpl implements ISysOperatelogService 
{
    @Autowired
    private SysOperatelogMapper sysOperatelogMapper;

    /**
     * 查询所有操作日志记录列表
     *
     * @param appCode 应用编号
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelog> getAllRecords(String appCode) {
        return sysOperatelogMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelog> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysOperatelogMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelog> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysOperatelogMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询操作日志记录列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 操作日志记录集合
     */
    @Override
    public List<SysOperatelog> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return sysOperatelogMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 操作日志记录
     */
    @Override
    public SysOperatelog getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询操作日志记录名称
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询操作日志记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysOperatelogMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增操作日志记录
     *
     * @param appCode 应用编号
     * @param info 操作日志记录
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysOperatelog info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysOperatelogMapper.AddNewRecord(info);
    }

    /**
     * 更新操作日志记录
     *
     * @param appCode 应用编号
     * @param info 操作日志记录
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysOperatelog info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysOperatelogMapper.UpdateRecord(info);
    }

    /**
     * 硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOperatelogMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除操作日志记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysOperatelogMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param no 操作日志记录ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysOperatelogMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param nos 操作日志记录IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysOperatelogMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除操作日志记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysOperatelogMapper.SoftDeleteByCondition(appCode,condition);
    }
}
