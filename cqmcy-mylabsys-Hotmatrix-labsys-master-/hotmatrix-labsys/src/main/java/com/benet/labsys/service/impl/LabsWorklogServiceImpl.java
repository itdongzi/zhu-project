package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsWorklogMapper;
import com.benet.labsys.domain.LabsWorklog;
import com.benet.labsys.service.ILabsWorklogService;

/**
 * 实训室工作/检查记录Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsWorklogServiceImpl implements ILabsWorklogService 
{
    @Autowired
    private LabsWorklogMapper labsWorklogMapper;

    /**
     * 查询所有实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @return 实训室工作/检查记录集合
     */
    @Override
    public List<LabsWorklog> getAllRecords(String appCode) {
        return labsWorklogMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实训室工作/检查记录集合
     */
    @Override
    public List<LabsWorklog> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsWorklogMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实训室工作/检查记录集合
     */
    @Override
    public List<LabsWorklog> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsWorklogMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实训室工作/检查记录列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实训室工作/检查记录集合
     */
    @Override
    public List<LabsWorklog> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsWorklogMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 实训室工作/检查记录
     */
    @Override
    public LabsWorklog getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsWorklogMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实训室工作/检查记录名称
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsWorklogMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实训室工作/检查记录计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsWorklogMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param info 实训室工作/检查记录
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsWorklog info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsWorklogMapper.AddNewRecord(info);
    }

    /**
     * 更新实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param info 实训室工作/检查记录
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsWorklog info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsWorklogMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsWorklogMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param nos 实训室工作/检查记录IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsWorklogMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsWorklogMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param no 实训室工作/检查记录ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsWorklogMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param nos 实训室工作/检查记录IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsWorklogMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实训室工作/检查记录
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsWorklogMapper.SoftDeleteByCondition(appCode,condition);
    }
}
