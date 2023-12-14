package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysLogininfoMapper;
import com.benet.system.domain.SysLogininfo;
import com.benet.system.service.ISysLogininfoService;

/**
 * 系统访问记录Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysLogininfoServiceImpl implements ISysLogininfoService
{
    @Autowired
    private SysLogininfoMapper sysLogininfoMapper;

    /**
     * 查询所有系统访问记录列表
     *
     * @param appCode 应用编号
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfo> getAllRecords(String appCode) {
        return sysLogininfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询系统访问记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysLogininfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询系统访问记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysLogininfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询系统访问记录列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统访问记录集合
     */
    @Override
    public List<SysLogininfo> getRecordsByPaging(String appCode,int pageIndex, int pageSize, String condition, String orderField, String orderType) {

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
        return sysLogininfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询系统访问记录
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 系统访问记录
     */
    @Override
    public SysLogininfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询系统访问记录名称
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询系统访问记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysLogininfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增系统访问记录
     *
     * @param appCode 应用编号
     * @param info 系统访问记录
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysLogininfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysLogininfoMapper.AddNewRecord(info);
    }

    /**
     * 更新系统访问记录
     *
     * @param appCode 应用编号
     * @param info 系统访问记录
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysLogininfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysLogininfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除系统访问记录
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除系统访问记录
     *
     * @param appCode 应用编号
     * @param nos 系统访问记录IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysLogininfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除系统访问记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysLogininfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除系统访问记录
     *
     * @param appCode 应用编号
     * @param no 系统访问记录ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysLogininfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除系统访问记录
     *
     * @param appCode 应用编号
     * @param nos 系统访问记录IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysLogininfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除系统访问记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysLogininfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
