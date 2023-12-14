package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benet.system.vmodel.MenuInfoVo;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
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
import com.benet.system.domain.SysMenusinfo;
import com.benet.system.service.ISysMenusinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;

/**
 * 控制台菜单信息Controller
 * 
 * @author yoxking
 * @date 2022-09-08 14:13:52
 */
@Api(value = "system/menusinfo", tags = "控制台菜单信息控制器")
@RestController
@RequestMapping("/system/menusinfo")
public class SysMenusinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysMenusinfoService menusinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询控制台菜单信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("menuName")&&StringUtils.isNotEmpty(maps.get("menuName").toString())) {
            condition += "And menu_name like '%" + maps.get("menuName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = menusinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysMenusinfo> list = menusinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 查询控制台菜单信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:list')")
    @GetMapping(value = "/tree")
    public TableDataInfo tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = menusinfoService.getCountByCondition(loginUser.getAppCode(),"");
        List<MenuInfoVo> list = buildItemTree(loginUser.getAppCode(),"0");
        return getDataTable(list, count);
    }

    private List<MenuInfoVo> buildItemTree(String appCode, String parentNo) {

        List<MenuInfoVo> itemTree = null;
        MenuInfoVo item = null;
        List<SysMenusinfo> infoList = menusinfoService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysMenusinfo info : infoList) {
                item = new MenuInfoVo();
                item.setId(info.getMenuNo());
                item.setKey(info.getMenuNo());
                item.setTitle(info.getMenuName());
                item.setValue(info.getMenuNo());
                item.setMenuNo(info.getMenuNo());
                item.setMenuName(info.getMenuName());
                item.setMenuCode(info.getMenuCode());
                item.setMenuType(info.getMenuType());
                item.setMenuIcon(info.getMenuIcon());
                item.setMenuImage(info.getMenuImage());
                item.setParentNo(info.getParentNo());
                item.setOrderNo(info.getOrderNo());
                item.setPathUrl(info.getPathUrl());
                item.setComments(info.getComments());
                item.setChildren(buildItemTree(appCode,info.getMenuNo()));

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    /**
     * 新增控制台菜单信息
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:addnew')")
    @Oplog(title = "控制台菜单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysMenusinfo coctMenusinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctMenusinfo.setMenuNo(UuidUtils.shortUUID());
        coctMenusinfo.setCreateBy(loginUser.getUserNo());
        coctMenusinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(menusinfoService.AddNewRecord(loginUser.getAppCode(),coctMenusinfo));
    }

    /**
     * 编辑控制台菜单信息
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:update')")
    @Oplog(title = "控制台菜单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysMenusinfo coctMenusinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        coctMenusinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(menusinfoService.UpdateRecord(loginUser.getAppCode(), coctMenusinfo));
    }

    /**
     * 保存控制台菜单信息
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:save')")
    @Oplog(title = "控制台菜单信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysMenusinfo coctMenusinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(menusinfoService.getRecordByNo(loginUser.getAppCode(),coctMenusinfo.getMenuNo()))) {
            coctMenusinfo.setMenuNo(UuidUtils.shortUUID());
            coctMenusinfo.setCreateBy(loginUser.getUserNo());
            coctMenusinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(menusinfoService.AddNewRecord(loginUser.getAppCode(),coctMenusinfo));
        } else {
            coctMenusinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(menusinfoService.UpdateRecord(loginUser.getAppCode(),coctMenusinfo));
        }
    }

    /**
     * 删除控制台菜单信息
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:delete')")
    @Oplog(title = "控制台菜单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(menusinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取控制台菜单信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(menusinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取控制台菜单信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysMenusinfo info=menusinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出控制台菜单信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:menusinfo:export')")
    @Oplog(title = "控制台菜单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("menuName")&&StringUtils.isNotEmpty(maps.get("menuName").toString())) {
            condition += "And menu_name like '%" + maps.get("menuName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = menusinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysMenusinfo> list = menusinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysMenusinfo> util = new ExcelUtils<SysMenusinfo>(SysMenusinfo.class);
        return util.exportExcel(list, "SysMenusinfo");
    }
}