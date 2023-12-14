package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsSectionsinfoMapper;
import com.benet.labsys.domain.LabsSectionsinfo;
import com.benet.labsys.service.ILabsSectionsinfoService;

/**
 * 教学节次时间Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsSectionsinfoServiceImpl implements ILabsSectionsinfoService 
{
    @Autowired
    private LabsSectionsinfoMapper labsSectionsinfoMapper;

    /**
     * 查询所有教学节次时间列表
     *
     * @param appCode 应用编号
     * @return 教学节次时间集合
     */
    @Override
    public List<LabsSectionsinfo> getAllRecords(String appCode) {
        return labsSectionsinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询教学节次时间列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 教学节次时间集合
     */
    @Override
    public List<LabsSectionsinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsSectionsinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询教学节次时间列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 教学节次时间集合
     */
    @Override
    public List<LabsSectionsinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsSectionsinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询教学节次时间列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 教学节次时间集合
     */
    @Override
    public List<LabsSectionsinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsSectionsinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询教学节次时间
     *
     * @param appCode 应用编号
     * @param no 教学节次时间ID
     * @return 教学节次时间
     */
    @Override
    public LabsSectionsinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSectionsinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询教学节次时间名称
     *
     * @param appCode 应用编号
     * @param no 教学节次时间ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSectionsinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询教学节次时间计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsSectionsinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增教学节次时间
     *
     * @param appCode 应用编号
     * @param info 教学节次时间
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsSectionsinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsSectionsinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新教学节次时间
     *
     * @param appCode 应用编号
     * @param info 教学节次时间
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsSectionsinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsSectionsinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除教学节次时间
     *
     * @param appCode 应用编号
     * @param no 教学节次时间ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSectionsinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除教学节次时间
     *
     * @param appCode 应用编号
     * @param nos 教学节次时间IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSectionsinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除教学节次时间
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsSectionsinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除教学节次时间
     *
     * @param appCode 应用编号
     * @param no 教学节次时间ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSectionsinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除教学节次时间
     *
     * @param appCode 应用编号
     * @param nos 教学节次时间IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSectionsinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除教学节次时间
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsSectionsinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
