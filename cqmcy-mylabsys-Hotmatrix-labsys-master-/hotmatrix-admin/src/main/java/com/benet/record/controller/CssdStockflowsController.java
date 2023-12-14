package com.benet.record.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.json.JsonHelper;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.record.domain.CssdStockinfo;
import com.benet.record.domain.CssdStockitems;
import com.benet.record.service.ICssdStockinfoService;
import com.benet.record.service.ICssdStockitemsService;
import com.benet.record.vmodel.SflowTypeVo;
import com.benet.record.vmodel.StockItemVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.record.domain.CssdStockflows;
import com.benet.record.service.ICssdStockflowsService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 出入库操作Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "record/stockflows", tags = "出入库操作控制器")
@RestController
@RequestMapping("/record/stockflows")
public class CssdStockflowsController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdStockflowsService cssdStockflowsService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询出入库操作列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if (maps.containsKey("dataType")&&StringUtils.isNotEmpty(maps.get("dataType").toString())) {
            condition += "And data_type = '" + maps.get("dataType") + "' ";
        }
        if(maps.containsKey("sflowType")) {
            condition += "And sflow_type=" + maps.get("sflowType");
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStockflowsService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CssdStockflows> list = cssdStockflowsService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增出入库操作
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:addnew')")
    @Oplog(title = "出入库操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdStockflows cssdStockflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockflows.setSflowNo(UuidUtils.shortUUID());
        cssdStockflows.setCreateBy(loginUser.getUserNo());
        cssdStockflows.setUpdateBy(loginUser.getUserNo());
        int count=cssdStockflowsService.AddNewRecord(loginUser.getAppCode(),cssdStockflows);
        if(count>0){
            return success(cssdStockflows.getSflowNo());
        }
        else{
            return error();
        }
    }

    /**
     * 编辑出入库操作
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:update')")
    @Oplog(title = "出入库操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody CssdStockflows cssdStockflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStockflows.setUpdateBy(loginUser.getUserNo());
        int count=cssdStockflowsService.UpdateRecord(loginUser.getAppCode(), cssdStockflows);
        if(count>0){
            return success(cssdStockflows.getSflowNo());
        }
        else{
            return error();
        }
    }

    /**
     * 保存出入库操作
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:save')")
    @Oplog(title = "出入库操作", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStockflows cssdStockflows) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStockflowsService.getRecordByNo(loginUser.getAppCode(),cssdStockflows.getSflowNo()))) {
            cssdStockflows.setSflowNo(UuidUtils.shortUUID());
            cssdStockflows.setCreateBy(loginUser.getUserNo());
            cssdStockflows.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockflowsService.AddNewRecord(loginUser.getAppCode(),cssdStockflows));
        } else {
            cssdStockflows.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStockflowsService.UpdateRecord(loginUser.getAppCode(),cssdStockflows));
        }
    }

    /**
     * 删除出入库操作
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:delete')")
    @Oplog(title = "出入库操作", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStockflowsService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取出入库操作详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStockflowsService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出出入库操作列表
     */
    @PreAuthorize("@ps.hasPermit('record:stockflows:export')")
    @Oplog(title = "出入库操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("sflowType")) {
            condition += " sflow_type=" + maps.get("sflowType");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStockflowsService.getCountByCondition(loginUser.getAppCode(),condition);

        List<CssdStockflows> list = cssdStockflowsService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<CssdStockflows> util = new ExcelUtils<CssdStockflows>(CssdStockflows.class);
        return util.exportExcel(list, "CssdStockflows");
    }
}
