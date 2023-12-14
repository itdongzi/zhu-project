package com.benet.common.core.pager;

/**
 * 分页数据模型
 *
 * @author yoxking
 */
public class PagingModel {

    /** 当前页起始索引 */
    private Integer pageIndex;
    /** 每页显示记录数 */
    private Integer pageSize;
    /** 条件 */
    private String condition;
    /** 排序列 */
    private String orderField;
    /** 排序的方向 "desc" 或者 "asc". */
    private String orderType;

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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
