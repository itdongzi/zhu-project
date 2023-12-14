package com.benet.record.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.record.mapper.CssdPrintstyleMapper;
import com.benet.record.domain.CssdPrintstyle;
import com.benet.record.service.ICssdPrintstyleService;

/**
 * 打印样式Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class CssdPrintstyleServiceImpl implements ICssdPrintstyleService 
{
    @Autowired
    private CssdPrintstyleMapper cssdPrintstyleMapper;

    /**
     * 查询所有打印样式列表
     *
     * @param appCode 应用编号
     * @return 打印样式集合
     */
    @Override
    public List<CssdPrintstyle> getAllRecords(String appCode) {
        return cssdPrintstyleMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询打印样式列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 打印样式集合
     */
    @Override
    public List<CssdPrintstyle> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return cssdPrintstyleMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询打印样式列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 打印样式集合
     */
    @Override
    public List<CssdPrintstyle> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return cssdPrintstyleMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询打印样式列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 打印样式集合
     */
    @Override
    public List<CssdPrintstyle> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return cssdPrintstyleMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 打印样式
     */
    @Override
    public CssdPrintstyle getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPrintstyleMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询打印样式名称
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPrintstyleMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询打印样式计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return cssdPrintstyleMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增打印样式
     *
     * @param appCode 应用编号
     * @param info 打印样式
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,CssdPrintstyle info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return cssdPrintstyleMapper.AddNewRecord(info);
    }

    /**
     * 更新打印样式
     *
     * @param appCode 应用编号
     * @param info 打印样式
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,CssdPrintstyle info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return cssdPrintstyleMapper.UpdateRecord(info);
    }

    /**
     * 硬删除打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPrintstyleMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除打印样式
     *
     * @param appCode 应用编号
     * @param nos 打印样式IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdPrintstyleMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除打印样式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return cssdPrintstyleMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除打印样式
     *
     * @param appCode 应用编号
     * @param no 打印样式ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return cssdPrintstyleMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除打印样式
     *
     * @param appCode 应用编号
     * @param nos 打印样式IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return cssdPrintstyleMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除打印样式
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return cssdPrintstyleMapper.SoftDeleteByCondition(appCode,condition);
    }
}
