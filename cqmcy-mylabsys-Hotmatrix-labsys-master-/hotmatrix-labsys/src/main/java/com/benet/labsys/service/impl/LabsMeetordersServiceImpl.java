package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsMeetordersMapper;
import com.benet.labsys.domain.LabsMeetorders;
import com.benet.labsys.service.ILabsMeetordersService;

/**
 * 会议室预约Service业务层处理
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Service
public class LabsMeetordersServiceImpl implements ILabsMeetordersService 
{
    @Autowired
    private LabsMeetordersMapper labsMeetordersMapper;

    /**
     * 查询所有会议室预约列表
     *
     * @param appCode 应用编号
     * @return 会议室预约集合
     */
    @Override
    public List<LabsMeetorders> getAllRecords(String appCode) {
        return labsMeetordersMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询会议室预约列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室预约集合
     */
    @Override
    public List<LabsMeetorders> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsMeetordersMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询会议室预约列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室预约集合
     */
    @Override
    public List<LabsMeetorders> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsMeetordersMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询会议室预约列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 会议室预约集合
     */
    @Override
    public List<LabsMeetorders> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsMeetordersMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询会议室预约
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 会议室预约
     */
    @Override
    public LabsMeetorders getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetordersMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室预约名称
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetordersMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室预约计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsMeetordersMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增会议室预约
     *
     * @param appCode 应用编号
     * @param info 会议室预约
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsMeetorders info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsMeetordersMapper.AddNewRecord(info);
    }

    /**
     * 更新会议室预约
     *
     * @param appCode 应用编号
     * @param info 会议室预约
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsMeetorders info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsMeetordersMapper.UpdateRecord(info);
    }

    /**
     * 硬删除会议室预约
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetordersMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除会议室预约
     *
     * @param appCode 应用编号
     * @param nos 会议室预约IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetordersMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除会议室预约
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsMeetordersMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除会议室预约
     *
     * @param appCode 应用编号
     * @param no 会议室预约ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetordersMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除会议室预约
     *
     * @param appCode 应用编号
     * @param nos 会议室预约IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetordersMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除会议室预约
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsMeetordersMapper.SoftDeleteByCondition(appCode,condition);
    }
}
