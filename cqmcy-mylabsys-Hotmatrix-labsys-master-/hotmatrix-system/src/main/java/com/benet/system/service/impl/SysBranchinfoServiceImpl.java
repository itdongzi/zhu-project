package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysBranchinfoMapper;
import com.benet.system.domain.SysBranchinfo;
import com.benet.system.service.ISysBranchinfoService;

/**
 * 分支信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysBranchinfoServiceImpl implements ISysBranchinfoService 
{
    @Autowired
    private SysBranchinfoMapper sysBranchinfoMapper;

    /**
     * 查询所有分支信息列表
     *
     * @param appCode 应用编号
     * @return 分支信息集合
     */
    @Override
    public List<SysBranchinfo> getAllRecords(String appCode) {
        return sysBranchinfoMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询分支信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 分支信息集合
     */
    @Override
    public List<SysBranchinfo> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysBranchinfoMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询分支信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 分支信息集合
     */
    @Override
    public List<SysBranchinfo> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysBranchinfoMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询分支信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 分支信息集合
     */
    @Override
    public List<SysBranchinfo> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysBranchinfoMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询分支信息
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 分支信息
     */
    @Override
    public SysBranchinfo getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysBranchinfoMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询分支信息名称
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysBranchinfoMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询分支信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysBranchinfoMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增分支信息
     *
     * @param appCode 应用编号
     * @param info 分支信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysBranchinfo info) {
            info.setCreateTime(DateTimeUtils.getNowDate());
            info.setUpdateTime(DateTimeUtils.getNowDate());
            info.setAppCode(appCode);
            info.setVersion(1L);
            return sysBranchinfoMapper.AddNewRecord(info);
    }

    /**
     * 更新分支信息
     *
     * @param appCode 应用编号
     * @param info 分支信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysBranchinfo info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysBranchinfoMapper.UpdateRecord(info);
    }

    /**
     * 硬删除分支信息
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysBranchinfoMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除分支信息
     *
     * @param appCode 应用编号
     * @param nos 分支信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysBranchinfoMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除分支信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysBranchinfoMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除分支信息
     *
     * @param appCode 应用编号
     * @param no 分支信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysBranchinfoMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除分支信息
     *
     * @param appCode 应用编号
     * @param nos 分支信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysBranchinfoMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除分支信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysBranchinfoMapper.SoftDeleteByCondition(appCode,condition);
    }
}
