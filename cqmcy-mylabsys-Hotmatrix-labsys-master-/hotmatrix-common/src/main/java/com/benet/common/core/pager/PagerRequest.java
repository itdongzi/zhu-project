package com.benet.common.core.pager;

import java.util.Map;

/**
 * 分页请求数据
 *
 * @author yoxking
 */
public class PagerRequest
{
    /** 当前记录起始索引 */
    private Integer current;
    /** 每页显示记录数 */
    private Integer pageSize;
    /** 每页显示记录数 */
    private Integer total;
    /** 查询条件 */
    private String condition;
    /** 查询参数 */
    private Map dataParams;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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