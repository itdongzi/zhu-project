package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsAreainfoMapper;
import com.benet.labsys.domain.LabsAreainfo;
import com.benet.labsys.service.ILabsAreainfoService;

/**
 * 实验楼区域Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Service
public class LabsAreainfoServiceImpl implements ILabsAreainfoService 
{
    @Autowired
    private LabsAreainfoMapper labsAreainfoMapper;

    /**
     * 查询所有实验楼区域列表
     *
     * @param appCode 应用编号
     * @return 实验楼区域集合
     */
    @Override
    public List<LabsAreainfo> getAllRecords(String appCode) {
        return labsAreainfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询实验楼区域列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 实验楼区域集合
     */
    @Override
    public List<LabsAreainfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsAreainfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询实验楼区域列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 实验楼区域集合
     */
    @Override
    public List<LabsAreainfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsAreainfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询实验楼区域列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 实验楼区域集合
     */
    @Override
    public List<LabsAreainfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsAreainfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询实验楼区域
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 实验楼区域
     */
    @Override
    public LabsAreainfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAreainfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验楼区域名称
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAreainfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询实验楼区域计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsAreainfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增实验楼区域
     *
     * @param appCode 应用编号
     * @param info 实验楼区域
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsAreainfo info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsAreainfoMapper.AddNewRecord(info);
    }

    /**
     * 更新实验楼区域
     *
     * @param appCode 应用编号
     * @param info 实验楼区域
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsAreainfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsAreainfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除实验楼区域
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAreainfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除实验楼区域
     *
     * @param appCode 应用编号
     * @param nos 实验楼区域IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsAreainfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除实验楼区域
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsAreainfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除实验楼区域
     *
     * @param appCode 应用编号
     * @param no 实验楼区域ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsAreainfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除实验楼区域
     *
     * @param appCode 应用编号
     * @param nos 实验楼区域IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsAreainfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除实验楼区域
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsAreainfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
