package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysSuserrotaMapper;
import com.benet.system.domain.SysSuserrota;
import com.benet.system.service.ISysSuserrotaService;

/**
 * 系统用户排班Service业务层处理
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Service
public class SysSuserrotaServiceImpl implements ISysSuserrotaService 
{
    @Autowired
    private SysSuserrotaMapper sysSuserrotaMapper;

    /**
     * 查询所有系统用户排班列表
     *
     * @param appCode 应用编号
     * @return 系统用户排班集合
     */
    @Override
    public List<SysSuserrota> getAllRecords(String appCode) {
        return sysSuserrotaMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询系统用户排班列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 系统用户排班集合
     */
    @Override
    public List<SysSuserrota> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysSuserrotaMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询系统用户排班列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 系统用户排班集合
     */
    @Override
    public List<SysSuserrota> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysSuserrotaMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询系统用户排班列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 系统用户排班集合
     */
    @Override
    public List<SysSuserrota> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysSuserrotaMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询系统用户排班
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 系统用户排班
     */
    @Override
    public SysSuserrota getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserrotaMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询系统用户排班名称
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserrotaMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询系统用户排班计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysSuserrotaMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增系统用户排班
     *
     * @param appCode 应用编号
     * @param info 系统用户排班
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysSuserrota info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysSuserrotaMapper.AddNewRecord(info);
    }

    /**
     * 更新系统用户排班
     *
     * @param appCode 应用编号
     * @param info 系统用户排班
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysSuserrota info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysSuserrotaMapper.UpdateRecord(info);
    }

    /**
     * 硬删除系统用户排班
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserrotaMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除系统用户排班
     *
     * @param appCode 应用编号
     * @param nos 系统用户排班IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysSuserrotaMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除系统用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysSuserrotaMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除系统用户排班
     *
     * @param appCode 应用编号
     * @param no 系统用户排班ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysSuserrotaMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除系统用户排班
     *
     * @param appCode 应用编号
     * @param nos 系统用户排班IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysSuserrotaMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除系统用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysSuserrotaMapper.SoftDeleteByCondition(appCode,condition);
    }
}
