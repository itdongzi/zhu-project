package com.benet.labsys.service.impl;

import java.util.List;
import com.benet.common.core.pager.PagingModel;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benet.labsys.mapper.LabsContestresourceMapper;
import com.benet.labsys.domain.LabsContestresource;
import com.benet.labsys.service.ILabsContestresourceService;

/**
 * 竞赛作品信息Service业务层处理
 * 
 * @author yoxking
 * @date 2023-02-23 09:06:40
 */
@Service
public class LabsContestresourceServiceImpl implements ILabsContestresourceService 
{
    @Autowired
    private LabsContestresourceMapper labsContestresourceMapper;

    /**
     * 查询所有竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @return 竞赛作品信息集合
     */
    @Override
    public List<LabsContestresource> getAllRecords(String appCode) {
        return labsContestresourceMapper.getAllRecords(appCode);
    }

    /**
     * 按分类查询竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @param classNo 分类编号
     * @return 竞赛作品信息集合
     */
    @Override
    public List<LabsContestresource> getRecordsByClassNo(String appCode,String classNo) {
        if (StringUtils.isNotEmpty(classNo)) {
            return labsContestresourceMapper.getRecordsByClassNo(appCode,classNo);
        }
        return null;
    }

    /**
     * 分页查询竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @param model 分页模型
     * @return 竞赛作品信息集合
     */
    @Override
    public List<LabsContestresource> getRecordsByPaging(String appCode,PagingModel model) {
        if (StringUtils.isNotNull(model)) {
            model.setPageIndex((model.getPageIndex()-1)*model.getPageSize());
            return labsContestresourceMapper.getRecordsByPaging(appCode,model);
        }
        return null;
    }


    /**
     * 分页查询竞赛作品信息列表
     *
     * @param appCode 应用编号
     * @param pageIndex 当前页起始索引
     * @param pageSize 页面大小
     * @param condition 条件
     * @param orderField 排序列
     * @param orderType 排序类型
     * @return 竞赛作品信息集合
     */
    @Override
    public List<LabsContestresource> getRecordsByPaging(String appCode,int pageIndex,int pageSize,String condition,String orderField,String orderType) {

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
        return labsContestresourceMapper.getRecordsByPaging(appCode,model);
    }

    /**
     * 查询竞赛作品信息
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 竞赛作品信息
     */
    @Override
    public LabsContestresource getRecordByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestresourceMapper.getRecordByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询竞赛作品信息名称
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 名称
     */
    @Override
    public String getRecordNameByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestresourceMapper.getRecordNameByNo(appCode,no);
        }
        return null;
    }

    /**
     * 查询竞赛作品信息计数
     *
     * @param appCode 应用编号
     * @param condition 查询条件
     * @return 结果
     */
    @Override
    public int getCountByCondition(String appCode,String condition) {
        return labsContestresourceMapper.getCountByCondition(appCode,condition);
    }

    /**
     * 新增竞赛作品信息
     *
     * @param appCode 应用编号
     * @param info 竞赛作品信息
     * @return 结果
     */
    @Override
    public int AddNewRecord(String appCode,LabsContestresource info) {
        info.setCreateTime(DateTimeUtils.getNowDate());
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        info.setVersion(1L);
        return labsContestresourceMapper.AddNewRecord(info);
    }

    /**
     * 更新竞赛作品信息
     *
     * @param appCode 应用编号
     * @param info 竞赛作品信息
     * @return 结果
     */
    @Override
    public int UpdateRecord(String appCode,LabsContestresource info) {
        info.setUpdateTime(DateTimeUtils.getNowDate());
        info.setAppCode(appCode);
        return labsContestresourceMapper.UpdateRecord(info);
    }

    /**
     * 硬删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 结果
     */
    @Override
    public int HardDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestresourceMapper.HardDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量硬删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛作品信息IDs
     * @return 结果
     */
    @Override
    public int HardDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsContestresourceMapper.HardDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件硬删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int HardDeleteByCondition(String appCode,String condition) {
        return labsContestresourceMapper.HardDeleteByCondition(appCode,condition);
    }

    /**
     * 软删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param no 竞赛作品信息ID
     * @return 结果
     */
    @Override
    public int SoftDeleteByNo(String appCode,String no) {
        if (StringUtils.isNotEmpty(no)) {
            return labsContestresourceMapper.SoftDeleteByNo(appCode,no);
        }
        return 0;
    }

    /**
     * 批量软删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param nos 竞赛作品信息IDs
     * @return 结果
     */
    @Override
    public int SoftDeleteByNos(String appCode,String[] nos) {
        if (StringUtils.isNotEmpty(nos)) {
            return labsContestresourceMapper.SoftDeleteByNos(appCode,nos);
        }
        return 0;
    }

    /**
     * 按条件软删除竞赛作品信息
     *
     * @param appCode 应用编号
     * @param condition 条件
     * @return 结果
     */
    @Override
    public int SoftDeleteByCondition(String appCode,String condition) {
        return labsContestresourceMapper.SoftDeleteByCondition(appCode,condition);
    }
}
