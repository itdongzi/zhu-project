package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsContestsigninMapper;
import com.benet.labsys.domain.LabsContestsignin;
import com.benet.labsys.service.ILabsContestsigninService;

/**
 * 竞赛打卡信息Service业务层处理
 * 
 * @author yoxking
 * @date 2022-11-19 19:36:34
 */
@Service
public class LabsContestsigninServiceImpl implements ILabsContestsigninService 
{
    @Autowired
    private LabsContestsigninMapper labsContestsigninMapper;

    /**
     * 查询所有竞赛打卡信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛打卡信息集合
     */
    @Override
    public List<LabsContestsignin> getAllRecords(String appCode) {
        return labsContestsigninMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询竞赛打卡信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛打卡信息集合
     */
    @Override
    public List<LabsContestsignin> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsContestsigninMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询竞赛打卡信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛打卡信息集合
     */
    @Override
    public List<LabsContestsignin> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsContestsigninMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询竞赛打卡信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 竞赛打卡信息集合
     */
    @Override
    public List<LabsContestsignin> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsContestsigninMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param no 竞赛打卡信息ID
     * @return 竞赛打卡信息
     */
    @Override
    public LabsContestsignin getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestsigninMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询竞赛打卡信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛打卡信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestsigninMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询竞赛打卡信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsContestsigninMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param info 竞赛打卡信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsContestsignin info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsContestsigninMapper.AddNewRecord(info);
    }

    /**
     * 更新竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param info 竞赛打卡信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsContestsignin info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsContestsigninMapper.UpdateRecord(info);
    }

    /**
     * 硬删除竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param no 竞赛打卡信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestsigninMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛打卡信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsContestsigninMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsContestsigninMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param no 竞赛打卡信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestsigninMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛打卡信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsContestsigninMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除竞赛打卡信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsContestsigninMapper.SoftDeleteByCondition(appCode,condition);
    }
}
