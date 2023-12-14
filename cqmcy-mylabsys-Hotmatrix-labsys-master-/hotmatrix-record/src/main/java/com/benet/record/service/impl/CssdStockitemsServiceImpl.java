package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdStockitemsMapper;
import com.benet.record.domain.CssdStockitems;
import com.benet.record.service.ICssdStockitemsService;

/**
 * 出入库单明细Service业务层处理
 * 
 * @author yoxking
 * @date 2021-03-20
 */
@Service
public class CssdStockitemsServiceImpl implements ICssdStockitemsService 
{
    @Autowired
    private CssdStockitemsMapper cssdStockitemsMapper;

    /**
     * 查询所有出入库单明细列表
     *
     * @param appCode 应用编号
     * @return 出入库单明细集合
     */
    @Override
    public List<CssdStockitems> getAllRecords(String appCode) {
        return cssdStockitemsMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 出入库单明细集合
     */
    @Override
    public List<CssdStockitems> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdStockitemsMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 出入库单明细集合
     */
    @Override
    public List<CssdStockitems> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdStockitemsMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询出入库单明细列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 出入库单明细集合
     */
    @Override
    public List<CssdStockitems> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdStockitemsMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 出入库单明细
     */
    @Override
    public CssdStockitems getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockitemsMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询出入库单明细名称
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockitemsMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询出入库单明细计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdStockitemsMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增出入库单明细
     *
     * @param appCode 应用编号
     * @param info 出入库单明细
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdStockitems info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdStockitemsMapper.AddNewRecord(info);
    }

    /**
     * 更新出入库单明细
     *
     * @param appCode 应用编号
     * @param info 出入库单明细
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdStockitems info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdStockitemsMapper.UpdateRecord(info);
    }

    /**
     * 硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockitemsMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param nos 出入库单明细IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStockitemsMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除出入库单明细
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdStockitemsMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param no 出入库单明细ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStockitemsMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param nos 出入库单明细IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStockitemsMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除出入库单明细
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdStockitemsMapper.SoftDeleteByCondition(appCode,condition);
    }
}
