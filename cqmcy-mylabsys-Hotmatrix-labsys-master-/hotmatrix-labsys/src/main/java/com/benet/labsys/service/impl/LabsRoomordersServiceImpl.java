package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsRoomordersMapper;
import com.benet.labsys.domain.LabsRoomorders;
import com.benet.labsys.service.ILabsRoomordersService;

/**
 * 实验室预约Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsRoomordersServiceImpl implements ILabsRoomordersService 
{
    @Autowired
    private LabsRoomordersMapper labsRoomordersMapper;

    /**
     * 查询所有实验室预约列表
     *
     * @param appCode 应用编号
     * @return 实验室预约集合
     */
    @Override
    public List<LabsRoomorders> getAllRecords(String appCode) {
        return labsRoomordersMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实验室预约列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室预约集合
     */
    @Override
    public List<LabsRoomorders> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsRoomordersMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实验室预约列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室预约集合
     */
    @Override
    public List<LabsRoomorders> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsRoomordersMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实验室预约列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室预约集合
     */
    @Override
    public List<LabsRoomorders> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsRoomordersMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实验室预约
     *
     * @param appCode 应用编号
     * @param no 实验室预约ID
     * @return 实验室预约
     */
    @Override
    public LabsRoomorders getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomordersMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室预约名称
     *
     * @param appCode 应用编号
     * @param no 实验室预约ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomordersMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室预约计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsRoomordersMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实验室预约
     *
     * @param appCode 应用编号
     * @param info 实验室预约
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsRoomorders info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsRoomordersMapper.AddNewRecord(info);
    }

    /**
     * 更新实验室预约
     *
     * @param appCode 应用编号
     * @param info 实验室预约
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsRoomorders info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsRoomordersMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实验室预约
     *
     * @param appCode 应用编号
     * @param no 实验室预约ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomordersMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实验室预约
     *
     * @param appCode 应用编号
     * @param nos 实验室预约IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsRoomordersMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实验室预约
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsRoomordersMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实验室预约
     *
     * @param appCode 应用编号
     * @param no 实验室预约ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoomordersMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实验室预约
     *
     * @param appCode 应用编号
     * @param nos 实验室预约IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsRoomordersMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实验室预约
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsRoomordersMapper.SoftDeleteByCondition(appCode,condition);
    }
}
