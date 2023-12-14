package com.benet.record.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.vmodel.ItemObjectVo;
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
import com.benet.record.domain.CssdPrintsinfo;
import com.benet.record.service.ICssdPrintsinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 打印机信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "record/printsinfo", tags = "打印机信息控制器")
@RestController
@RequestMapping("/record/printsinfo")
public class CssdPrintsinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdPrintsinfoService cssdPrintsinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询打印机信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPrintsinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CssdPrintsinfo> list = cssdPrintsinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPrintsinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdPrintsinfo> infoList = cssdPrintsinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdPrintsinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getPrintNo());
                item.setKey(info.getPrintNo());
                item.setTitle(info.getPrintName());
                item.setValue(info.getPrintNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增打印机信息
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:addnew')")
    @Oplog(title = "打印机信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdPrintsinfo cssdPrintsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdPrintsinfo.setPrintNo(UuidUtils.shortUUID());
        cssdPrintsinfo.setCreateBy(loginUser.getUserNo());
        cssdPrintsinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(cssdPrintsinfoService.AddNewRecord(loginUser.getAppCode(),cssdPrintsinfo));
    }

    /**
     * 编辑打印机信息
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:update')")
    @Oplog(title = "打印机信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdPrintsinfo cssdPrintsinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdPrintsinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdPrintsinfoService.UpdateRecord(loginUser.getAppCode(),cssdPrintsinfo));
        }

    /**
     * 保存打印机信息
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:save')")
    @Oplog(title = "打印机信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdPrintsinfo cssdPrintsinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdPrintsinfoService.getRecordByNo(loginUser.getAppCode(),cssdPrintsinfo.getPrintNo()))) {
            cssdPrintsinfo.setPrintNo(UuidUtils.shortUUID());
            cssdPrintsinfo.setCreateBy(loginUser.getUserNo());
            cssdPrintsinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdPrintsinfoService.AddNewRecord(loginUser.getAppCode(),cssdPrintsinfo));
        } else {
            cssdPrintsinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdPrintsinfoService.UpdateRecord(loginUser.getAppCode(),cssdPrintsinfo));
        }
    }

    /**
     * 删除打印机信息
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:delete')")
    @Oplog(title = "打印机信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdPrintsinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取打印机信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdPrintsinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出打印机信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:printsinfo:export')")
    @Oplog(title = "打印机信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("name")) {
            condition += " name=" + maps.get("name");
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdPrintsinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<CssdPrintsinfo> list = cssdPrintsinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<CssdPrintsinfo> util = new ExcelUtils<CssdPrintsinfo>(CssdPrintsinfo.class);
        return util.exportExcel(list, "CssdPrintsinfo");
    }

}
