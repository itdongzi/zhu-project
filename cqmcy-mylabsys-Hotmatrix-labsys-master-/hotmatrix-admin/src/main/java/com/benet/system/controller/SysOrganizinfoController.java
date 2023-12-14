package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysDepartment;
import com.benet.system.vmodel.ItemObjectVo;
import com.benet.system.vmodel.OrgnzInfoVo;
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
import com.benet.system.domain.SysOrganizinfo;
import com.benet.system.service.ISysOrganizinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 机构信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/organizinfo", tags = "机构信息控制器")
@RestController
@RequestMapping("/system/organizinfo")
public class SysOrganizinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysOrganizinfoService sysOrganizinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询机构信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("organizName")&&StringUtils.isNotEmpty(maps.get("organizName").toString())) {
            condition += "And organiz_name like '%" + maps.get("organizName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOrganizinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysOrganizinfo> list = sysOrganizinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        if(list!=null&&list.size()>0){
            for(SysOrganizinfo info:list){
                info.setParentNo(sysOrganizinfoService.getRecordNameByNo(loginUser.getAppCode(),info.getParentNo()));
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 查询部门信息树形列表
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOrganizinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<ItemObjectVo> list = buildItemTree(loginUser.getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<ItemObjectVo> buildItemTree(String appCode, String parentNo) {

        List<ItemObjectVo> itemTree = null;
        ItemObjectVo item = null;
        List<SysOrganizinfo> infoList = sysOrganizinfoService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysOrganizinfo info : infoList) {

                item = new ItemObjectVo();
                item.setId(info.getOrganizNo());
                item.setKey(info.getOrganizNo());
                item.setTitle(info.getOrganizName());
                item.setValue(info.getOrganizNo());
                item.setChildren(buildItemTree(appCode,info.getOrganizNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }
    
    /**
     * 新增机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:addnew')")
    @Oplog(title = "机构信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysOrganizinfo sysOrganizinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOrganizinfo.setOrganizNo(UuidUtils.shortUUID());
        sysOrganizinfo.setCreateBy(loginUser.getUserNo());
        sysOrganizinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysOrganizinfoService.AddNewRecord(loginUser.getAppCode(),sysOrganizinfo));
    }

    /**
     * 编辑机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:update')")
    @Oplog(title = "机构信息", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysOrganizinfo sysOrganizinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysOrganizinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysOrganizinfoService.UpdateRecord(loginUser.getAppCode(),sysOrganizinfo));
        }

    /**
     * 保存机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:save')")
    @Oplog(title = "机构信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysOrganizinfo sysOrganizinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysOrganizinfoService.getRecordByNo(loginUser.getAppCode(),sysOrganizinfo.getOrganizNo()))) {
            sysOrganizinfo.setOrganizNo(UuidUtils.shortUUID());
            sysOrganizinfo.setCreateBy(loginUser.getUserNo());
            sysOrganizinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysOrganizinfoService.AddNewRecord(loginUser.getAppCode(),sysOrganizinfo));
        } else {
            sysOrganizinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysOrganizinfoService.UpdateRecord(loginUser.getAppCode(),sysOrganizinfo));
        }
    }

    /**
     * 删除机构信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:delete')")
    @Oplog(title = "机构信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysOrganizinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取机构信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysOrganizinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取机构信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysOrganizinfo info=sysOrganizinfoService.getRecordByNo(loginUser.getAppCode(),id);
        if(info!=null){
            info.setParentNo(sysOrganizinfoService.getRecordNameByNo(loginUser.getAppCode(),info.getParentNo()));
        }
        return AjaxResult.success(info);
    }

    /**
     * 导出机构信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:organizinfo:export')")
    @Oplog(title = "机构信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("organizName")&&StringUtils.isNotEmpty(maps.get("organizName").toString())) {
            condition += "And organiz_name like '%" + maps.get("organizName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysOrganizinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysOrganizinfo> list = sysOrganizinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysOrganizinfo> util = new ExcelUtils<SysOrganizinfo>(SysOrganizinfo.class);
        return util.exportExcel(list, "SysOrganizinfo");
    }

}
