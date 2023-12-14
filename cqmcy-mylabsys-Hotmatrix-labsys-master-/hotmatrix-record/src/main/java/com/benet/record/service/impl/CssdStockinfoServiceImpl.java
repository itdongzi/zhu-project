package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdStockinfoMapper;
import com.benet.record.domain.CssdStockinfo;
import com.benet.record.service.ICssdStockinfoService;

/**
 * 库存信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdStockinfoServiceImpl implements ICssdStockinfoService 
{
    @Autowired
    private CssdStockinfoMapper cssdStockinfoMapper;

    /**
     * 查询所有库存信息列表
     *
     * @param appCode 应用编号
     * @return 库存信息集合
     */
    @Override
    public List<CssdStockinfo> getAllRecords(String appCode) {
        return cssdStockinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询库存信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 库存信息集合
     */
    @Override
    public List<CssdStockinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdStockinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询库存信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 库存信息集合
     */
    @Override
    public List<CssdStockinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdStockinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询库存信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 库存信息集合
     */
    @Override
    public List<CssdStockinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdStockinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询库存信息
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 库存信息
     */
    @Override
    public CssdStockinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询库存信息名称
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询库存信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdStockinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增库存信息
     *
     * @param appCode 应用编号
     * @param info 库存信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdStockinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdStockinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新库存信息
     *
     * @param appCode 应用编号
     * @param info 库存信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdStockinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdStockinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除库存信息
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除库存信息
     *
     * @param appCode 应用编号
     * @param nos 库存信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStockinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除库存信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdStockinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除库存信息
     *
     * @param appCode 应用编号
     * @param no 库存信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除库存信息
     *
     * @param appCode 应用编号
     * @param nos 库存信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStockinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除库存信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdStockinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
