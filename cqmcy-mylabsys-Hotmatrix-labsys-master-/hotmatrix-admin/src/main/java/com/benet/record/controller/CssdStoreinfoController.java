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
import com.benet.record.domain.CssdStoreinfo;
import com.benet.record.service.ICssdStoreinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 库房信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "record/storeinfo", tags = "库房信息控制器")
@RestController
@RequestMapping("/record/storeinfo")
public class CssdStoreinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdStoreinfoService cssdStoreinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询库房信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:list')")
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
        int count = cssdStoreinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CssdStoreinfo> list = cssdStoreinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStoreinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdStoreinfo> infoList = cssdStoreinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdStoreinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getStoreNo());
                item.setKey(info.getStoreNo());
                item.setTitle(info.getStoreName());
                item.setValue(info.getStoreNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增库房信息
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:addnew')")
    @Oplog(title = "库房信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdStoreinfo cssdStoreinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStoreinfo.setStoreNo(UuidUtils.shortUUID());
        cssdStoreinfo.setCreateBy(loginUser.getUserNo());
        cssdStoreinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(cssdStoreinfoService.AddNewRecord(loginUser.getAppCode(),cssdStoreinfo));
    }

    /**
     * 编辑库房信息
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:update')")
    @Oplog(title = "库房信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdStoreinfo cssdStoreinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStoreinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStoreinfoService.UpdateRecord(loginUser.getAppCode(),cssdStoreinfo));
        }

    /**
     * 保存库房信息
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:save')")
    @Oplog(title = "库房信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStoreinfo cssdStoreinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStoreinfoService.getRecordByNo(loginUser.getAppCode(),cssdStoreinfo.getStoreNo()))) {
            cssdStoreinfo.setStoreNo(UuidUtils.shortUUID());
            cssdStoreinfo.setCreateBy(loginUser.getUserNo());
            cssdStoreinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStoreinfoService.AddNewRecord(loginUser.getAppCode(),cssdStoreinfo));
        } else {
            cssdStoreinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStoreinfoService.UpdateRecord(loginUser.getAppCode(),cssdStoreinfo));
        }
    }

    /**
     * 删除库房信息
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:delete')")
    @Oplog(title = "库房信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStoreinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取库房信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStoreinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出库房信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:storeinfo:export')")
    @Oplog(title = "库房信息", businessType = BusinessType.EXPORT)
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
        int count = cssdStoreinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<CssdStoreinfo> list = cssdStoreinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<CssdStoreinfo> util = new ExcelUtils<CssdStoreinfo>(CssdStoreinfo.class);
        return util.exportExcel(list, "CssdStoreinfo");
    }

}
