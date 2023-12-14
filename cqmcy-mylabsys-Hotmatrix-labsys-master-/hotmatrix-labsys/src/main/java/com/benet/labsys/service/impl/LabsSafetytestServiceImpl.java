package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsSafetytestMapper;
import com.benet.labsys.domain.LabsSafetytest;
import com.benet.labsys.service.ILabsSafetytestService;

/**
 * 安全考核Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsSafetytestServiceImpl implements ILabsSafetytestService 
{
    @Autowired
    private LabsSafetytestMapper labsSafetytestMapper;

    /**
     * 查询所有安全考核列表
     *
     * @param appCode 应用编号
     * @return 安全考核集合
     */
    @Override
    public List<LabsSafetytest> getAllRecords(String appCode) {
        return labsSafetytestMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询安全考核列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 安全考核集合
     */
    @Override
    public List<LabsSafetytest> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsSafetytestMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询安全考核列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 安全考核集合
     */
    @Override
    public List<LabsSafetytest> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsSafetytestMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询安全考核列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 安全考核集合
     */
    @Override
    public List<LabsSafetytest> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsSafetytestMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询安全考核
     *
     * @param appCode 应用编号
     * @param no 安全考核ID
     * @return 安全考核
     */
    @Override
    public LabsSafetytest getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetytestMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全考核名称
     *
     * @param appCode 应用编号
     * @param no 安全考核ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetytestMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询安全考核计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsSafetytestMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增安全考核
     *
     * @param appCode 应用编号
     * @param info 安全考核
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsSafetytest info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsSafetytestMapper.AddNewRecord(info);
    }

    /**
     * 更新安全考核
     *
     * @param appCode 应用编号
     * @param info 安全考核
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsSafetytest info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsSafetytestMapper.UpdateRecord(info);
    }

    /**
     * 硬删除安全考核
     *
     * @param appCode 应用编号
     * @param no 安全考核ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetytestMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除安全考核
     *
     * @param appCode 应用编号
     * @param nos 安全考核IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetytestMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除安全考核
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsSafetytestMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除安全考核
     *
     * @param appCode 应用编号
     * @param no 安全考核ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSafetytestMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除安全考核
     *
     * @param appCode 应用编号
     * @param nos 安全考核IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSafetytestMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除安全考核
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsSafetytestMapper.SoftDeleteByCondition(appCode,condition);
    }
}
