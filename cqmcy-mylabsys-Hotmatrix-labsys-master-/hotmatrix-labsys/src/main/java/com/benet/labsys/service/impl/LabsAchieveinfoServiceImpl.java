package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsAchieveinfoMapper;
import com.benet.labsys.domain.LabsAchieveinfo;
import com.benet.labsys.service.ILabsAchieveinfoService;

/**
 * 实训成果Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Service
public class LabsAchieveinfoServiceImpl implements ILabsAchieveinfoService 
{
    @Autowired
    private LabsAchieveinfoMapper labsAchieveinfoMapper;

    /**
     * 查询所有实训成果列表
     *
     * @param appCode 应用编号
     * @return 实训成果集合
     */
    @Override
    public List<LabsAchieveinfo> getAllRecords(String appCode) {
        return labsAchieveinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实训成果列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实训成果集合
     */
    @Override
    public List<LabsAchieveinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsAchieveinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实训成果列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实训成果集合
     */
    @Override
    public List<LabsAchieveinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsAchieveinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实训成果列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实训成果集合
     */
    @Override
    public List<LabsAchieveinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsAchieveinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 实训成果
     */
    @Override
    public LabsAchieveinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAchieveinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实训成果名称
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAchieveinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实训成果计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsAchieveinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实训成果
     *
     * @param appCode 应用编号
     * @param info 实训成果
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsAchieveinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsAchieveinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新实训成果
     *
     * @param appCode 应用编号
     * @param info 实训成果
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsAchieveinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsAchieveinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAchieveinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实训成果
     *
     * @param appCode 应用编号
     * @param nos 实训成果IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsAchieveinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实训成果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsAchieveinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实训成果
     *
     * @param appCode 应用编号
     * @param no 实训成果ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAchieveinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实训成果
     *
     * @param appCode 应用编号
     * @param nos 实训成果IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsAchieveinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实训成果
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsAchieveinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
