package com.benet.system.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.system.mapper.SysRuserrotaMapper;
import com.benet.system.domain.SysRuserrota;
import com.benet.system.service.ISysRuserrotaService;

/**
 * 注册用户排班Service业务层处理
 * 
 * @author yoxking
 * @date 2020-10-27
 */
@Service
public class SysRuserrotaServiceImpl implements ISysRuserrotaService 
{
    @Autowired
    private SysRuserrotaMapper sysRuserrotaMapper;

    /**
     * 查询所有注册用户排班列表
     *
     * @param appCode 应用编号
     * @return 注册用户排班集合
     */
    @Override
    public List<SysRuserrota> getAllRecords(String appCode) {
        return sysRuserrotaMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 注册用户排班集合
     */
    @Override
    public List<SysRuserrota> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return sysRuserrotaMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 注册用户排班集合
     */
    @Override
    public List<SysRuserrota> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return sysRuserrotaMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询注册用户排班列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 注册用户排班集合
     */
    @Override
    public List<SysRuserrota> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return sysRuserrotaMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 注册用户排班
     */
    @Override
    public SysRuserrota getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRuserrotaMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询注册用户排班名称
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRuserrotaMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询注册用户排班计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return sysRuserrotaMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增注册用户排班
     *
     * @param appCode 应用编号
     * @param info 注册用户排班
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,SysRuserrota info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return sysRuserrotaMapper.AddNewRecord(info);
    }

    /**
     * 更新注册用户排班
     *
     * @param appCode 应用编号
     * @param info 注册用户排班
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,SysRuserrota info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return sysRuserrotaMapper.UpdateRecord(info);
    }

    /**
     * 硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRuserrotaMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param nos 注册用户排班IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRuserrotaMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除注册用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return sysRuserrotaMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param no 注册用户排班ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return sysRuserrotaMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param nos 注册用户排班IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return sysRuserrotaMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除注册用户排班
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return sysRuserrotaMapper.SoftDeleteByCondition(appCode,condition);
    }
}
