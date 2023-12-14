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
import com.benet.system.vmodel.PermitInfoVo;
import com.benet.system.vmodel.PermitTempl;
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
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.service.ISysPermitinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 菜单权限Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/permitinfo", tags = "菜单权限控制器")
@RestController
@RequestMapping("/system/permitinfo")
public class SysPermitinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysPermitinfoService sysPermitinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询菜单权限列表
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("permitName")&&StringUtils.isNotEmpty(maps.get("permitName").toString())) {
            condition += "And permit_name like '%" + maps.get("permitName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysPermitinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysPermitinfo> list = sysPermitinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询部门信息树形列表
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysPermitinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<PermitInfoVo> list = buildPermitTree(loginUser.getAppCode(),"0");
        //List<PermitTempl> list = buildPermitTree2(loginUser.getAppCode(),"0");
        //String temp= JSON.toJSONString(list);
        return getDataTable(list, count);
    }
    //临时方法
    private List<PermitTempl> buildPermitTree2(String appCode,String parentNo) {

        List<PermitTempl> permitTree = null;
        PermitTempl permit = null;
        List<SysPermitinfo> infoList = sysPermitinfoService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            permitTree = new ArrayList<>();
            for (SysPermitinfo info : infoList) {
                permit = new PermitTempl();
                permit.setPermitName(info.getPermitName());
                permit.setPermitCode(info.getPermitCode());
                permit.setPermitType(info.getPermitType());
                permit.setOrderNo(info.getOrderNo());
                permit.setLinkType(info.getLinkType());
                permit.setMenuIcon(info.getMenuIcon());
                permit.setPathUrl(info.getPathUrl());
                permit.setComponent(info.getComponent());
                permit.setRedirect(info.getRedirect());
                permit.setTarget(info.getTarget());
                permit.setVisible(info.getVisible());
                permit.setChildren(buildPermitTree2(appCode,info.getPermitNo()));
                permitTree.add(permit);
            }
        }
        return permitTree;
    }


    private List<PermitInfoVo> buildPermitTree(String appCode,String parentNo) {

        List<PermitInfoVo> permitTree = null;
        PermitInfoVo permit = null;
        List<SysPermitinfo> infoList = sysPermitinfoService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            permitTree = new ArrayList<>();
            for (SysPermitinfo info : infoList) {
                permit = new PermitInfoVo();
                permit.setId(info.getPermitNo());
                permit.setKey(info.getPermitNo());
                permit.setTitle(info.getPermitName());
                permit.setValue(info.getPermitNo());
                permit.setPermitNo(info.getPermitNo());
                permit.setPermitName(info.getPermitName());
                permit.setParentNo(info.getParentNo());
                permit.setOrderNo(info.getOrderNo());
                permit.setMenuIcon(info.getMenuIcon());
                permit.setPermitCode(info.getPermitCode());
                permit.setComponent(info.getComponent());
                permit.setComments(info.getComments());
                permit.setChildren(buildPermitTree(appCode,info.getPermitNo()));
                permitTree.add(permit);
            }
        }
        return permitTree;
    }


    /**
     * 新增菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:addnew')")
    @Oplog(title = "菜单权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysPermitinfo sysPermitinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysPermitinfo.setPermitNo(UuidUtils.shortUUID());
        sysPermitinfo.setCreateBy(loginUser.getUserNo());
        sysPermitinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysPermitinfoService.AddNewRecord(loginUser.getAppCode(),sysPermitinfo));
    }

    /**
     * 编辑菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:update')")
    @Oplog(title = "菜单权限", businessType = BusinessType.UPDATE)
    @PutMapping
        public AjaxResult update(@RequestBody SysPermitinfo sysPermitinfo) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysPermitinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysPermitinfoService.UpdateRecord(loginUser.getAppCode(),sysPermitinfo));
        }

    /**
     * 保存菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:save')")
    @Oplog(title = "菜单权限", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysPermitinfo sysPermitinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysPermitinfoService.getRecordByNo(loginUser.getAppCode(),sysPermitinfo.getPermitNo()))) {
            sysPermitinfo.setPermitNo(UuidUtils.shortUUID());
            sysPermitinfo.setCreateBy(loginUser.getUserNo());
            sysPermitinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysPermitinfoService.AddNewRecord(loginUser.getAppCode(),sysPermitinfo));
        } else {
            sysPermitinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysPermitinfoService.UpdateRecord(loginUser.getAppCode(),sysPermitinfo));
        }
    }

    /**
     * 删除菜单权限
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:delete')")
    @Oplog(title = "菜单权限", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysPermitinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取菜单权限详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysPermitinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取菜单权限详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysPermitinfo info=sysPermitinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出菜单权限列表
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:export')")
    @Oplog(title = "菜单权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("permitName")&&StringUtils.isNotEmpty(maps.get("permitName").toString())) {
            condition += "And permit_name like '%" + maps.get("permitName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysPermitinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysPermitinfo> list = sysPermitinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysPermitinfo> util = new ExcelUtils<SysPermitinfo>(SysPermitinfo.class);
        return util.exportExcel(list, "SysPermitinfo");
    }

}
