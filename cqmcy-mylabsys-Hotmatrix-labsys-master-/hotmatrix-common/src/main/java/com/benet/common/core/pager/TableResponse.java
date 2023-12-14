package com.benet.common.core.pager;

import java.util.List;

/**
 * 表格响应数据
 *
 * @author yoxking
 */
public class TableResponse {


    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> list;


    /**
     * 表格数据对象
     */
    public TableResponse()
    {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableResponse(List<?> list, int total)
    {
        this.list = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
