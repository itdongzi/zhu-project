package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsRoominfoMapper;
import com.benet.labsys.domain.LabsRoominfo;
import com.benet.labsys.service.ILabsRoominfoService;

/**
 * 实验室信息Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsRoominfoServiceImpl implements ILabsRoominfoService 
{
    @Autowired
    private LabsRoominfoMapper labsRoominfoMapper;

    /**
     * 查询所有实验室信息列表
     *
     * @param appCode 应用编号
     * @return 实验室信息集合
     */
    @Override
    public List<LabsRoominfo> getAllRecords(String appCode) {
        return labsRoominfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实验室信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验室信息集合
     */
    @Override
    public List<LabsRoominfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsRoominfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    @Override
    public List<LabsRoominfo> getRecordsByCondition(String appCode,String condition) {
        if (StringUtils.isNotEmpty(condition)) {
            return labsRoominfoMapper.getRecordsByCondition(appCode,condition);
        }
        return null;
    }

    /**
     * 分页查询实验室信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验室信息集合
     */
    @Override
    public List<LabsRoominfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsRoominfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实验室信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验室信息集合
     */
    @Override
    public List<LabsRoominfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsRoominfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实验室信息
     *
     * @param appCode 应用编号
     * @param no 实验室信息ID
     * @return 实验室信息
     */
    @Override
    public LabsRoominfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoominfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室信息名称
     *
     * @param appCode 应用编号
     * @param no 实验室信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoominfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验室信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsRoominfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实验室信息
     *
     * @param appCode 应用编号
     * @param info 实验室信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsRoominfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsRoominfoMapper.AddNewRecord(info);
    }

    /**
     * 更新实验室信息
     *
     * @param appCode 应用编号
     * @param info 实验室信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsRoominfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsRoominfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实验室信息
     *
     * @param appCode 应用编号
     * @param no 实验室信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoominfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实验室信息
     *
     * @param appCode 应用编号
     * @param nos 实验室信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsRoominfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实验室信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsRoominfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实验室信息
     *
     * @param appCode 应用编号
     * @param no 实验室信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsRoominfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实验室信息
     *
     * @param appCode 应用编号
     * @param nos 实验室信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsRoominfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实验室信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsRoominfoMapper.SoftDeleteByCondition(appCode,condition);
    }


}
