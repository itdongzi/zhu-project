package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsMeetinfoMapper;
import com.benet.labsys.domain.LabsMeetinfo;
import com.benet.labsys.service.ILabsMeetinfoService;

/**
 * 会议室信息Service业务层处理
 * 
 * @author yoxking
 * @date 2023-02-24 09:46:53
 */
@Service
public class LabsMeetinfoServiceImpl implements ILabsMeetinfoService 
{
    @Autowired
    private LabsMeetinfoMapper labsMeetinfoMapper;

    /**
     * 查询所有会议室信息列表
     *
     * @param appCode 应用编号
     * @return 会议室信息集合
     */
    @Override
    public List<LabsMeetinfo> getAllRecords(String appCode) {
        return labsMeetinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询会议室信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 会议室信息集合
     */
    @Override
    public List<LabsMeetinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsMeetinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询会议室信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 会议室信息集合
     */
    @Override
    public List<LabsMeetinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsMeetinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询会议室信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 会议室信息集合
     */
    @Override
    public List<LabsMeetinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsMeetinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询会议室信息
     *
     * @param appCode 应用编号
     * @param no 会议室信息ID
     * @return 会议室信息
     */
    @Override
    public LabsMeetinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室信息名称
     *
     * @param appCode 应用编号
     * @param no 会议室信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询会议室信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsMeetinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增会议室信息
     *
     * @param appCode 应用编号
     * @param info 会议室信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsMeetinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsMeetinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新会议室信息
     *
     * @param appCode 应用编号
     * @param info 会议室信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsMeetinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsMeetinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除会议室信息
     *
     * @param appCode 应用编号
     * @param no 会议室信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除会议室信息
     *
     * @param appCode 应用编号
     * @param nos 会议室信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除会议室信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsMeetinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除会议室信息
     *
     * @param appCode 应用编号
     * @param no 会议室信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsMeetinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除会议室信息
     *
     * @param appCode 应用编号
     * @param nos 会议室信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsMeetinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除会议室信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsMeetinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
