package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdSupplierinfoMapper;
import com.benet.record.domain.CssdSupplierinfo;
import com.benet.record.service.ICssdSupplierinfoService;

/**
 * 供应商信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdSupplierinfoServiceImpl implements ICssdSupplierinfoService 
{
    @Autowired
    private CssdSupplierinfoMapper cssdSupplierinfoMapper;

    /**
     * 查询所有供应商信息列表
     *
     * @param appCode 应用编号
     * @return 供应商信息集合
     */
    @Override
    public List<CssdSupplierinfo> getAllRecords(String appCode) {
        return cssdSupplierinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 供应商信息集合
     */
    @Override
    public List<CssdSupplierinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdSupplierinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 供应商信息集合
     */
    @Override
    public List<CssdSupplierinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdSupplierinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询供应商信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 供应商信息集合
     */
    @Override
    public List<CssdSupplierinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdSupplierinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 供应商信息
     */
    @Override
    public CssdSupplierinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSupplierinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询供应商信息名称
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSupplierinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询供应商信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdSupplierinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增供应商信息
     *
     * @param appCode 应用编号
     * @param info 供应商信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdSupplierinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdSupplierinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新供应商信息
     *
     * @param appCode 应用编号
     * @param info 供应商信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdSupplierinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdSupplierinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSupplierinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param nos 供应商信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdSupplierinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除供应商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdSupplierinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除供应商信息
     *
     * @param appCode 应用编号
     * @param no 供应商信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdSupplierinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除供应商信息
     *
     * @param appCode 应用编号
     * @param nos 供应商信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdSupplierinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除供应商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdSupplierinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
