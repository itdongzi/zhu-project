package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdStoreinfoMapper;
import com.benet.record.domain.CssdStoreinfo;
import com.benet.record.service.ICssdStoreinfoService;

/**
 * 库房信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdStoreinfoServiceImpl implements ICssdStoreinfoService 
{
    @Autowired
    private CssdStoreinfoMapper cssdStoreinfoMapper;

    /**
     * 查询所有库房信息列表
     *
     * @param appCode 应用编号
     * @return 库房信息集合
     */
    @Override
    public List<CssdStoreinfo> getAllRecords(String appCode) {
        return cssdStoreinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询库房信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 库房信息集合
     */
    @Override
    public List<CssdStoreinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdStoreinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询库房信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 库房信息集合
     */
    @Override
    public List<CssdStoreinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdStoreinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询库房信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 库房信息集合
     */
    @Override
    public List<CssdStoreinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdStoreinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 库房信息
     */
    @Override
    public CssdStoreinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStoreinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询库房信息名称
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStoreinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询库房信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdStoreinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增库房信息
     *
     * @param appCode 应用编号
     * @param info 库房信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdStoreinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdStoreinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新库房信息
     *
     * @param appCode 应用编号
     * @param info 库房信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdStoreinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdStoreinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStoreinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除库房信息
     *
     * @param appCode 应用编号
     * @param nos 库房信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStoreinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除库房信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdStoreinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除库房信息
     *
     * @param appCode 应用编号
     * @param no 库房信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdStoreinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除库房信息
     *
     * @param appCode 应用编号
     * @param nos 库房信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdStoreinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除库房信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdStoreinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
