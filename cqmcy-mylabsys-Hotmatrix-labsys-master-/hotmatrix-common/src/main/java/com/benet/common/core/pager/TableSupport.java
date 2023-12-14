package com.benet.common.core.pager;


import com.benet.common.constant.PubConstants;
import com.benet.common.utils.web.ServletUtils;

/**
 * 表格数据处理
 *
 * @author yoxking
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PagingModel getPagingMode()
    {
        PagingModel pagingModel = new PagingModel();
        pagingModel.setPageIndex(ServletUtils.getParameterToInt(PubConstants.PAGE_NUM));
        pagingModel.setPageSize(ServletUtils.getParameterToInt(PubConstants.PAGE_SIZE));
        pagingModel.setOrderType("Asc");
        pagingModel.setOrderField("*");
        pagingModel.setCondition("");
        return pagingModel;
    }

    public static PagingModel buildPageRequest()
    {
        return getPagingMode();
    }
}