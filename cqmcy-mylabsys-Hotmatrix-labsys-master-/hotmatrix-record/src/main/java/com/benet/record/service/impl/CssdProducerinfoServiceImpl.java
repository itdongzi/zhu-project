package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdProducerinfoMapper;
import com.benet.record.domain.CssdProducerinfo;
import com.benet.record.service.ICssdProducerinfoService;

/**
 * 生产商信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdProducerinfoServiceImpl implements ICssdProducerinfoService 
{
    @Autowired
    private CssdProducerinfoMapper cssdProducerinfoMapper;

    /**
     * 查询所有生产商信息列表
     *
     * @param appCode 应用编号
     * @return 生产商信息集合
     */
    @Override
    public List<CssdProducerinfo> getAllRecords(String appCode) {
        return cssdProducerinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询生产商信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 生产商信息集合
     */
    @Override
    public List<CssdProducerinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdProducerinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询生产商信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 生产商信息集合
     */
    @Override
    public List<CssdProducerinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdProducerinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询生产商信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 生产商信息集合
     */
    @Override
    public List<CssdProducerinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdProducerinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询生产商信息
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 生产商信息
     */
    @Override
    public CssdProducerinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdProducerinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询生产商信息名称
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdProducerinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询生产商信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdProducerinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增生产商信息
     *
     * @param appCode 应用编号
     * @param info 生产商信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdProducerinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdProducerinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新生产商信息
     *
     * @param appCode 应用编号
     * @param info 生产商信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdProducerinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdProducerinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除生产商信息
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdProducerinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除生产商信息
     *
     * @param appCode 应用编号
     * @param nos 生产商信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdProducerinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除生产商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdProducerinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除生产商信息
     *
     * @param appCode 应用编号
     * @param no 生产商信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdProducerinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除生产商信息
     *
     * @param appCode 应用编号
     * @param nos 生产商信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdProducerinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除生产商信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdProducerinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
