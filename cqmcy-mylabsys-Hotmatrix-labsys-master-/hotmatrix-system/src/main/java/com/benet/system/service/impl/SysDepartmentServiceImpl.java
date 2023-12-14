package com.benet.system.service.impl;

import java.util.List;

import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysDepartmentMapper;
import com.benet.system.domain.SysDepartment;
import com.benet.system.service.ISysDepartmentService;

/**
 * 部门信息Service业务层处理
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Service
public class SysDepartmentServiceImpl implements ISysDepartmentService 
{
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    /**
     * 查询所有部门信息列表
     *
     * @param appCode 应用编号
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getAllRecords(String appCode) {
        return sysDepartmentMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询部门信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysDepartmentMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询部门信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysDepartmentMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询部门信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 部门信息集合
     */
    @Override
    public List<SysDepartment> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysDepartmentMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 部门信息
     */
    @Override
    public SysDepartment getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询部门信息名称
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询部门信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysDepartmentMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增部门信息
     *
     * @param appCode 应用编号
     * @param info 部门信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysDepartment info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysDepartmentMapper.AddNewRecord(info);
    }

    /**
     * 更新部门信息
     *
     * @param appCode 应用编号
     * @param info 部门信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysDepartment info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysDepartmentMapper.UpdateRecord(info);
    }

    /**
     * 硬删除部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除部门信息
     *
     * @param appCode 应用编号
     * @param nos 部门信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDepartmentMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除部门信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysDepartmentMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除部门信息
     *
     * @param appCode 应用编号
     * @param no 部门信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysDepartmentMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除部门信息
     *
     * @param appCode 应用编号
     * @param nos 部门信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysDepartmentMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除部门信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysDepartmentMapper.SoftDeleteByCondition(appCode,condition);
    }
}
