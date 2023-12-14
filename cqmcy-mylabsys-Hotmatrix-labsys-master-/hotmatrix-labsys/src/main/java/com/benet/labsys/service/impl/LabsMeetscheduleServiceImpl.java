package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsMeetscheduleMapper;
import com.benet.labsys.domain.LabsMeetschedule;
import com.benet.labsys.service.ILabsMeetscheduleService;

/**
 * 会议室排班Service业务层处理
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Service
public class LabsMeetscheduleServiceImpl implements ILabsMeetscheduleService 
{
    @Autowired
    private LabsMeetscheduleMapper labsMeetscheduleMapper;

    /**
     * 查询所有会议室排班列表
     *
     * @param appCode 应用编号
     * @return 会议室排班集合
     */
    @Override
    public List<LabsMeetschedule> getAllRecords(String appCode) {
        return labsMeetscheduleMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询会议室排班列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室排班集合
     */
    @Override
    public List<LabsMeetschedule> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsMeetscheduleMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询会议室排班列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室排班集合
     */
    @Override
    public List<LabsMeetschedule> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsMeetscheduleMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询会议室排班列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 会议室排班集合
     */
    @Override
    public List<LabsMeetschedule> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsMeetscheduleMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询会议室排班
     *
     * @param appCode 应用编号
     * @param no 会议室排班ID
     * @return 会议室排班
     */
    @Override
    public LabsMeetschedule getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetscheduleMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室排班名称
     *
     * @param appCode 应用编号
     * @param no 会议室排班ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetscheduleMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室排班计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsMeetscheduleMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增会议室排班
     *
     * @param appCode 应用编号
     * @param info 会议室排班
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsMeetschedule info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsMeetscheduleMapper.AddNewRecord(info);
    }

    /**
     * 更新会议室排班
     *
     * @param appCode 应用编号
     * @param info 会议室排班
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsMeetschedule info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsMeetscheduleMapper.UpdateRecord(info);
    }

    /**
     * 硬删除会议室排班
     *
     * @param appCode 应用编号
     * @param no 会议室排班ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetscheduleMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除会议室排班
     *
     * @param appCode 应用编号
     * @param nos 会议室排班IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetscheduleMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除会议室排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsMeetscheduleMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除会议室排班
     *
     * @param appCode 应用编号
     * @param no 会议室排班ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetscheduleMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除会议室排班
     *
     * @param appCode 应用编号
     * @param nos 会议室排班IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetscheduleMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除会议室排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsMeetscheduleMapper.SoftDeleteByCondition(appCode,condition);
    }
}
