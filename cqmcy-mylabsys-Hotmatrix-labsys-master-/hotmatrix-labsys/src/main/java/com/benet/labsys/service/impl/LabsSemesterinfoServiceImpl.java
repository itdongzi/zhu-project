package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsSemesterinfoMapper;
import com.benet.labsys.domain.LabsSemesterinfo;
import com.benet.labsys.service.ILabsSemesterinfoService;

/**
 * 学期学年信息Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:35
 */
@Service
public class LabsSemesterinfoServiceImpl implements ILabsSemesterinfoService 
{
    @Autowired
    private LabsSemesterinfoMapper labsSemesterinfoMapper;

    /**
     * 查询所有学期学年信息列表
     *
     * @param appCode 应用编号
     * @return 学期学年信息集合
     */
    @Override
    public List<LabsSemesterinfo> getAllRecords(String appCode) {
        return labsSemesterinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询学期学年信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 学期学年信息集合
     */
    @Override
    public List<LabsSemesterinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsSemesterinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询学期学年信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 学期学年信息集合
     */
    @Override
    public List<LabsSemesterinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsSemesterinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询学期学年信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 学期学年信息集合
     */
    @Override
    public List<LabsSemesterinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsSemesterinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询学期学年信息
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 学期学年信息
     */
    @Override
    public LabsSemesterinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSemesterinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询学期学年信息名称
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSemesterinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询学期学年信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsSemesterinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增学期学年信息
     *
     * @param appCode 应用编号
     * @param info 学期学年信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsSemesterinfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsSemesterinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新学期学年信息
     *
     * @param appCode 应用编号
     * @param info 学期学年信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsSemesterinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsSemesterinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除学期学年信息
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSemesterinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除学期学年信息
     *
     * @param appCode 应用编号
     * @param nos 学期学年信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSemesterinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除学期学年信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsSemesterinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除学期学年信息
     *
     * @param appCode 应用编号
     * @param no 学期学年信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsSemesterinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除学期学年信息
     *
     * @param appCode 应用编号
     * @param nos 学期学年信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsSemesterinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除学期学年信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsSemesterinfoMapper.SoftDeleteByCondition(appCode,condition);
    }

    @Override
    public LabsSemesterinfo getRecordByNameAndType(String appCode,String condition) {
        if (StringUtils.isNotEmpty(condition)) {
            return labsSemesterinfoMapper.getRecordByNameAndType(appCode,condition);
        }
        return null;
    }
}
