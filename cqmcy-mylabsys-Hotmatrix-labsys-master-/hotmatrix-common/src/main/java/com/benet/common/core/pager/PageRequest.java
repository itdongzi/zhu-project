package com.benet.common.core.pager;

import lombok.Data;

import java.util.Map;

/**
 * 分页请求数据
 *
 * @author yoxking
 */
@Data
public class PageRequest
{
    /** 当前记录起始索引 */
    private Integer pageIndex;
    /** 每页显示记录数 */
    private Integer pageSize;
    /**  页数 */
    private Integer pageTotal;
    /** 查询条件 */
    private String condition;
    /** 查询参数 */
    private Map dataParams;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Map getDataParams() {
        return dataParams;
    }

    public void setDataParams(Map dataParams) {
        this.dataParams = dataParams;
    }
}