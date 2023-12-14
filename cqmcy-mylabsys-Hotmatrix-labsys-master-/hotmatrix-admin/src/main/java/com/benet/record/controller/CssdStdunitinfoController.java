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
import com.benet.record.domain.CssdStdunitinfo;
import com.benet.record.service.ICssdStdunitinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 单位信息Controller
 * 
 * @author yoxking
 * @date 2020-12-13
 */
@Api(value = "record/stdunitinfo", tags = "单位信息控制器")
@RestController
@RequestMapping("/record/stdunitinfo")
public class CssdStdunitinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ICssdStdunitinfoService cssdStdunitinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询单位信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:list')")
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
        int count = cssdStdunitinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<CssdStdunitinfo> list = cssdStdunitinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询内容类型列表
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = cssdStdunitinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode());
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<CssdStdunitinfo> infoList = cssdStdunitinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (CssdStdunitinfo info : infoList) {
                item = new ItemObjectVo();
                item.setId(info.getUnitNo());
                item.setKey(info.getUnitNo());
                item.setTitle(info.getUnitName());
                item.setValue(info.getUnitNo());
                item.setChildren(null);

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增单位信息
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:addnew')")
    @Oplog(title = "单位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody CssdStdunitinfo cssdStdunitinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStdunitinfo.setUnitNo(UuidUtils.shortUUID());
        cssdStdunitinfo.setCreateBy(loginUser.getUserNo());
        cssdStdunitinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(cssdStdunitinfoService.AddNewRecord(loginUser.getAppCode(),cssdStdunitinfo));
    }

    /**
     * 编辑单位信息
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:update')")
    @Oplog(title = "单位信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody CssdStdunitinfo cssdStdunitinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        cssdStdunitinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStdunitinfoService.UpdateRecord(loginUser.getAppCode(),cssdStdunitinfo));
        }

    /**
     * 保存单位信息
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:save')")
    @Oplog(title = "单位信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody CssdStdunitinfo cssdStdunitinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(cssdStdunitinfoService.getRecordByNo(loginUser.getAppCode(),cssdStdunitinfo.getUnitNo()))) {
            cssdStdunitinfo.setUnitNo(UuidUtils.shortUUID());
            cssdStdunitinfo.setCreateBy(loginUser.getUserNo());
            cssdStdunitinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStdunitinfoService.AddNewRecord(loginUser.getAppCode(),cssdStdunitinfo));
        } else {
            cssdStdunitinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(cssdStdunitinfoService.UpdateRecord(loginUser.getAppCode(),cssdStdunitinfo));
        }
    }

    /**
     * 删除单位信息
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:delete')")
    @Oplog(title = "单位信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(cssdStdunitinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取单位信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(cssdStdunitinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 导出单位信息列表
     */
    @PreAuthorize("@ps.hasPermit('record:stdunitinfo:export')")
    @Oplog(title = "单位信息", businessType = BusinessType.EXPORT)
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
        int count = cssdStdunitinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<CssdStdunitinfo> list = cssdStdunitinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<CssdStdunitinfo> util = new ExcelUtils<CssdStdunitinfo>(CssdStdunitinfo.class);
        return util.exportExcel(list, "CssdStdunitinfo");
    }

}
