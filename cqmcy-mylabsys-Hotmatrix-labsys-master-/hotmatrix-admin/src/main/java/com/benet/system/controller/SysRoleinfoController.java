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
import com.benet.system.vmodel.RolePermitVo;
import com.benet.system.vmodel.RoleSusersVo;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.domain.SysSuserinfo;
import com.benet.system.service.ISysPermitinfoService;
import com.benet.system.service.ISysSuserinfoService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.benet.system.domain.SysRoleinfo;
import com.benet.system.service.ISysRoleinfoService;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 角色信息Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "system/roleinfo", tags = "角色信息控制器")
@RestController
@RequestMapping("/system/roleinfo")
public class SysRoleinfoController extends BaseController {
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysSuserinfoService sysSuserinfoService;

    @Autowired
    private ISysRoleinfoService sysRoleinfoService;

    @Autowired
    private ISysPermitinfoService sysPermitinfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:index')")
    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 查询角色信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roleName")&&StringUtils.isNotEmpty(maps.get("roleName").toString())) {
            condition += "And role_name like '%" + maps.get("roleName")+"%'";
        }
        if(maps.containsKey("roleCode")&&StringUtils.isNotEmpty(maps.get("roleCode").toString())) {
            condition += "And role_code like '%" + maps.get("roleCode")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRoleinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRoleinfo> list = sysRoleinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        return getDataTable(list, count);
    }

    /**
     * 新增角色信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:addnew')")
    @Oplog(title = "角色信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRoleinfo sysRoleinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRoleinfo.setRoleNo(UuidUtils.shortUUID());
        sysRoleinfo.setCreateBy(loginUser.getUserNo());
        sysRoleinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRoleinfoService.AddNewRecord(loginUser.getAppCode(),sysRoleinfo));
    }

    /**
     * 编辑角色信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:update')")
    @Oplog(title = "角色信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysRoleinfo sysRoleinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysRoleinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRoleinfoService.UpdateRecord(loginUser.getAppCode(),sysRoleinfo));
    }

    /**
     * 保存角色信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:save')")
    @Oplog(title = "角色信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRoleinfo sysRoleinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRoleinfoService.getRecordByNo(loginUser.getAppCode(),sysRoleinfo.getRoleNo()))) {
            sysRoleinfo.setRoleNo(UuidUtils.shortUUID());
            sysRoleinfo.setCreateBy(loginUser.getUserNo());
            sysRoleinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRoleinfoService.AddNewRecord(loginUser.getAppCode(),sysRoleinfo));
        } else {
            sysRoleinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRoleinfoService.UpdateRecord(loginUser.getAppCode(),sysRoleinfo));
        }
    }

    /**
     * 删除角色信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:delete')")
    @Oplog(title = "角色信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRoleinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取角色信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysRoleinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取角色信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRoleinfo info=sysRoleinfoService.getRecordByNo(loginUser.getAppCode(),id);
        return AjaxResult.success(info);
    }

    /**
     * 导出角色信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:export')")
    @Oplog(title = "角色信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("roleName")&&StringUtils.isNotEmpty(maps.get("roleName").toString())) {
            condition += "And role_name like '%" + maps.get("roleName")+"%'";
        }
        if(maps.containsKey("roleCode")&&StringUtils.isNotEmpty(maps.get("roleCode").toString())) {
            condition += "And role_code like '%" + maps.get("roleCode")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRoleinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysRoleinfo> list = sysRoleinfoService.getRecordsByPaging(loginUser.getAppCode(),1, count, condition, "id", "Asc");
        ExcelUtils<SysRoleinfo> util = new ExcelUtils<SysRoleinfo>(SysRoleinfo.class);
        return util.exportExcel(list, "SysRoleinfo");
    }

    /**
     * 查询权限树形列表
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:list')")
    @GetMapping(value = "/permittree/{roleNo}")
    public AjaxResult permittree(@PathVariable("roleNo") String roleNo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult result=AjaxResult.success();
        result.put("pmttreeData",buildPermitTree(loginUser.getAppCode(),"0"));
        result.put("checkedKeys",getPermitKeys(roleNo));

        return AjaxResult.success(result);
    }

    private List<RolePermitVo> buildPermitTree(String appCode,String parentNo) {

        List<RolePermitVo> permitTree = null;
        RolePermitVo permit = null;
        List<SysPermitinfo> infoList = sysPermitinfoService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            permitTree = new ArrayList<>();
            for (SysPermitinfo info : infoList) {
                permit = new RolePermitVo();
                permit.setKey(info.getPermitNo());
                permit.setTitle(info.getPermitName());
                permit.setDesc(info.getComments());
                permit.setChildren(buildPermitTree(appCode,info.getPermitNo()));
                permitTree.add(permit);
            }
        }
        return permitTree;
    }

    private List<String> getPermitKeys(String roleNo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return sysRoleinfoService.getPermitNosByRoleNo(loginUser.getAppCode(),roleNo);
    }


    /**
     * 查询用户树形列表
     */
    @PreAuthorize("@ps.hasPermit('system:permitinfo:list')")
    @GetMapping(value = "/suersource/{roleNo}")
    public AjaxResult suersource(@PathVariable("roleNo") String roleNo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult result=AjaxResult.success();
        result.put("sourceData",buildSuersData(loginUser.getAppCode()));
        result.put("targetKeys",getSuserKeys(loginUser.getAppCode(),roleNo));

        return AjaxResult.success(result);
    }

    private List<RoleSusersVo> buildSuersData(String appCode) {

        List<RoleSusersVo> susersData = null;
        RoleSusersVo ssuer = null;
        List<SysSuserinfo> infoList = sysSuserinfoService.getAllRecords(appCode);

        if (infoList != null && infoList.size() > 0) {
            susersData = new ArrayList<>();
            for (SysSuserinfo info : infoList) {
                ssuer = new RoleSusersVo();
                ssuer.setKey(info.getUserNo());
                ssuer.setTitle(info.getUserCnname());
                ssuer.setDesc(info.getComments());
                ssuer.setChosen(false);
                susersData.add(ssuer);
            }
        }
        return susersData;
    }

    private List<String> getSuserKeys(String appCode,String roleNo) {

        return sysRoleinfoService.getSuserNosByRoleNo(appCode,roleNo);
    }

    /**
     * 保存角色权限信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:update')")
    @Oplog(title = "更新角色用户信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateSusers")
    public AjaxResult updateSusers(@RequestParam("roleNo") String roleNo,@RequestParam("suserNos") String[] suserNos) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRoleinfoService.UpdateSusers(loginUser.getAppCode(),roleNo,suserNos));
    }

    /**
     * 保存角色权限信息
     */
    @PreAuthorize("@ps.hasPermit('system:roleinfo:update')")
    @Oplog(title = "更新角色权限信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updatePermits")
    public AjaxResult updatePermits(@RequestParam("roleNo") String roleNo,@RequestParam("permitNos") String[] permitNos) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRoleinfoService.UpdatePermits(loginUser.getAppCode(),roleNo,permitNos));
    }

}
