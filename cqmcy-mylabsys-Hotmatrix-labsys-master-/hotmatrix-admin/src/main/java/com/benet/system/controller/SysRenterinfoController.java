package com.benet.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.utils.SecurityUtils;
import com.benet.system.vmodel.PermitTempl;
import com.benet.system.vmodel.RentObjectVo;
import com.benet.system.domain.*;
import com.benet.system.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.benet.common.annotation.Oplog;
import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.core.pager.TableDataInfo;

/**
 * 租户信息Controller
 * 
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "sys/renterinfo", tags = "租户信息控制器")
@RestController
@RequestMapping("/system/renterinfo")
public class SysRenterinfoController extends BaseController
{
    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRenteclassService sysRenteclassService;
    @Autowired
    private ISysRenterinfoService sysRenterinfoService;
    @Autowired
    private ISysDepartmentService departmentService;
    @Autowired
    private ISysOrganizinfoService organizinfoService;
    @Autowired
    private ISysSuserinfoService suserinfoService;
    @Autowired
    private ISysRoleinfoService roleinfoService;
    @Autowired
    private ISysPermitinfoService permitinfoService;
    @Autowired
    private ISysConfiginfoService configinfoService;
    @Autowired
    private ISysBranchinfoService branchinfoService;
    @Autowired
    private ISysMessageinfoService messageinfoService;
    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    /**
     * 查询租户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("rcnname")&&StringUtils.isNotEmpty(maps.get("rcnname").toString())) {
            condition += "And rcnname like '%" + maps.get("rcnname")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRenterinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRenterinfo> list = sysRenterinfoService.getRecordsByPaging(loginUser.getAppCode(),pRequest.getPageIndex(), pRequest.getPageSize(), condition, "id", "Asc");
        if(list!=null&&list.size()>0){
            for(SysRenterinfo info:list){
                info.setClassNo(sysRenteclassService.getRecordNameByNo(loginUser.getAppCode(),info.getClassNo()));
            }
        }
        return getDataTable(list, count);
    }

    /**
     * 新增租户信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:addnew')")
    @Oplog(title = "租户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insert(@RequestBody SysRenterinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppinfo.setRentNo(UuidUtils.shortUUID());
        sysAppinfo.setCreateBy(loginUser.getUserNo());
        sysAppinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRenterinfoService.AddNewRecord(loginUser.getAppCode(),sysAppinfo));
    }

    /**
     * 编辑租户信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:update')")
    @Oplog(title = "租户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@RequestBody SysRenterinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysAppinfo.setUpdateBy(loginUser.getUserNo());
        return toAjax(sysRenterinfoService.UpdateRecord(loginUser.getAppCode(),sysAppinfo));
    }

    /**
     * 保存租户信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:save')")
    @Oplog(title = "租户信息", businessType = BusinessType.SAVE)
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody SysRenterinfo sysAppinfo) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(sysRenterinfoService.getRecordByNo(loginUser.getAppCode(),sysAppinfo.getRentNo()))) {
            sysAppinfo.setRentNo(UuidUtils.shortUUID());
            sysAppinfo.setCreateBy(loginUser.getUserNo());
            sysAppinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRenterinfoService.AddNewRecord(loginUser.getAppCode(),sysAppinfo));
        } else {
            sysAppinfo.setUpdateBy(loginUser.getUserNo());
            return toAjax(sysRenterinfoService.UpdateRecord(loginUser.getAppCode(),sysAppinfo));
        }
    }

    /**
     * 删除租户信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:delete')")
    @Oplog(title = "租户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return toAjax(sysRenterinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    /**
     * 获取租户信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:detail')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(sysRenterinfoService.getRecordByNo(loginUser.getAppCode(),id));
    }

    /**
     * 获取租户信息详细信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRenterinfo info=sysRenterinfoService.getRecordByNo(loginUser.getAppCode(),id);
        if(info!=null){
            info.setClassNo(sysRenteclassService.getRecordNameByNo(loginUser.getAppCode(),info.getClassNo()));
        }
        return AjaxResult.success(info);
    }

    /**
     * 导出租户信息列表
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:export')")
    @Oplog(title = "租户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pRequest)
    {
        //String condition= pagerRequest.getCondition();
        String condition= "";
        Map maps = pRequest.getDataParams();
        if(maps.containsKey("RCnname")&&StringUtils.isNotEmpty(maps.get("RCnname").toString())) {
            condition += "And rcnname like '%" + maps.get("RCnname")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = sysRenterinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysRenterinfo> list = sysRenterinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysRenterinfo> util = new ExcelUtils<SysRenterinfo>(SysRenterinfo.class);
        return util.exportExcel(list, "SysAppinfo");
    }

    /**
     * 新增租户信息
     */
    @PreAuthorize("@ps.hasPermit('system:renterinfo:addnew')")
    @Oplog(title = "租户信息", businessType = BusinessType.INSERT)
    @PostMapping("/initialize")
    public AjaxResult initialize(@RequestBody RentObjectVo rentObject) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        String appCode=UuidUtils.shortUUID();
        SysRenterinfo sysAppinfo=new SysRenterinfo();

        sysAppinfo.setRentNo(appCode);
        sysAppinfo.setRcnname(rentObject.getRcnname());
        sysAppinfo.setRenname(rentObject.getRenname());
        sysAppinfo.setClassNo(rentObject.getClassNo());
        sysAppinfo.setOrderNo(1);
        sysAppinfo.setAppUrl(rentObject.getAppUrl());
        sysAppinfo.setAppVer(rentObject.getAppVer());
        sysAppinfo.setTelephone(rentObject.getTelephone());
        sysAppinfo.setEmail(rentObject.getEmail());
        sysAppinfo.setSummary(rentObject.getSummary());
        sysAppinfo.setEdogNo(rentObject.getEdogNo());
        sysAppinfo.setEdogType(rentObject.getEdogType());
        sysAppinfo.setRegistDate(rentObject.getRegistDate());
        sysAppinfo.setActiveDate(rentObject.getActiveDate());
        sysAppinfo.setActiveCount(rentObject.getActiveCount());
        sysAppinfo.setActiveCode(rentObject.getActiveCode());
        sysAppinfo.setCheckState(rentObject.getCheckState());
        sysAppinfo.setComments(rentObject.getComments());
        sysAppinfo.setAppCode(appCode);

        sysAppinfo.setCreateBy(loginUser.getUserNo());
        sysAppinfo.setUpdateBy(loginUser.getUserNo());
        if(sysRenterinfoService.AddNewRecord(loginUser.getAppCode(),sysAppinfo)>0) {

            String branchNo=initBranchInfo(appCode,rentObject.getRcnname(),rentObject.getTelephone(),rentObject.getEmail());
            String deptNo=initDepartment(appCode,branchNo,rentObject.getRcnname(),rentObject.getTelephone(),rentObject.getEmail());
            String orgzNo=initOrganizInfo(appCode,branchNo,rentObject.getRcnname());
            String suserNo=initSuserInfo(appCode,branchNo,rentObject.getUsername(),rentObject.getPassword(),deptNo,orgzNo,rentObject.getTelephone(),rentObject.getEmail());
            String roleNo=initRoleInfo(appCode,branchNo,suserNo);
            initPermitInfo(appCode,branchNo,roleNo);
            initConfigInfo(appCode,branchNo,rentObject.getRcnname());
            initMessageInfo(appCode,branchNo,suserNo);

            return toAjax(1);
        }
        return AjaxResult.error();
    }

    private String initBranchInfo(String appCode,String branchName,String tel,String email){

        SysBranchinfo info=new SysBranchinfo();
        String uuid=UuidUtils.shortUUID();
        info.setBranchNo(uuid);
        info.setBranchName("单位");
        info.setBranchCode(uuid);
        info.setBranchType("1");
        info.setOrderNo(1);
        info.setMaster("");
        info.setTelephone(tel);
        info.setEmail(email);
        info.setSummary("");
        info.setCheckState("1");
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(branchinfoService.AddNewRecord(appCode,info)>0){
            return uuid;
        }
        return "";
    }
    private String initDepartment(String appCode,String branchNo,String deptName,String tel,String email){

        SysDepartment info=new SysDepartment();
        String uuid=UuidUtils.shortUUID();
        info.setDeptNo(uuid);
        info.setDeptName(deptName);
        info.setParentNo("0");
        info.setOrderNo(1);
        info.setAncestors("");
        info.setLeader("");
        info.setTelephone(tel);
        info.setEmail(email);
        info.setCheckState("1");

        info.setBranchNo(branchNo);
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(departmentService.AddNewRecord(appCode,info)>0){
            return uuid;
        }
        return "";
    }
    private String initOrganizInfo(String appCode,String branchNo,String orgzName){

        SysOrganizinfo info=new SysOrganizinfo();
        String uuid=UuidUtils.shortUUID();
        info.setOrganizNo(uuid);
        info.setOrganizName(orgzName);
        info.setParentNo("0");
        info.setOrderNo(1);
        info.setCheckState("1");

        info.setBranchNo(branchNo);
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(organizinfoService.AddNewRecord(appCode,info)>0){
            return uuid;
        }
        return "";
    }
    private String initSuserInfo(String appCode,String branchNo,String userName,String password,String departNo,String organizNo,String tel,String email){

        SysSuserinfo info=new SysSuserinfo();
        String uuid=UuidUtils.shortUUID();
        info.setUserNo(uuid);
        info.setDeptNo(departNo);
        info.setOrgzNo(organizNo);
        info.setLoginName(userName);
        info.setPassword(SecurityUtils.encryptPassword(password));
        info.setUserCnname(userName);
        info.setUserEnname(userName);
        info.setUserType("00");
        info.setSex("1");
        info.setTelephone(tel);
        info.setEmail(email);
        info.setAvatar("/profile/avatar/2020/08/15/8cb17ab286568fa8dcf12c8fc398c421.jpeg");
        info.setSalt("");
        info.setLoginIp("");
        info.setLoginDate(DateTimeUtils.getNowDate());
        info.setCheckState("1");

        info.setBranchNo(branchNo);
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(suserinfoService.AddNewRecord(appCode,info)>0){
            return uuid;
        }
        return "";
    }
    private String initRoleInfo(String appCode,String branchNo,String suserNo){

        SysRoleinfo info=new SysRoleinfo();
        String uuid=UuidUtils.shortUUID();
        info.setRoleNo(uuid);
        info.setRoleName("系统管理员");
        info.setRoleCode("admin");
        info.setOrderNo(1);
        info.setDataScope("1");
        info.setCheckState("1");

        info.setBranchNo(branchNo);
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(roleinfoService.AddNewRecord(appCode,info)>0){
            roleinfoService.UpdateSusers(appCode,uuid,new String[]{suserNo});
            return uuid;
        }
        return "";
    }
    private String initPermitInfo(String appCode,String branchNo,String roleNo){

        List<String> permitNos=new ArrayList<>();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String pmtTmpl=configinfoService.getConfigValueByKey(loginUser.getAppCode(),"PmtTempl");

        List<PermitTempl> tmplList= JSON.parseArray(pmtTmpl,PermitTempl.class);

        if(tmplList!=null&&tmplList.size()>0){
            for(PermitTempl tmpl :tmplList){
                loadPermit(appCode,branchNo,"0",tmpl,permitNos);
            }
            roleinfoService.UpdatePermits(appCode,roleNo,permitNos.toArray(new String[permitNos.size()]));
        }
        return "";
    }
    private void loadPermit(String appCode,String branchNo,String parentNo,PermitTempl tmpl,List<String> permitNos){

        SysPermitinfo info=new SysPermitinfo();
        String uuid=UuidUtils.shortUUID();
        info.setPermitNo(uuid);
        info.setPermitName(tmpl.getPermitName());
        info.setPermitCode(tmpl.getPermitCode());
        info.setPermitType(tmpl.getPermitType());
        info.setParentNo(parentNo);
        info.setOrderNo(tmpl.getOrderNo());
        info.setLinkType(tmpl.getLinkType());
        info.setMenuIcon(tmpl.getMenuIcon());
        info.setPathUrl(tmpl.getPathUrl());
        info.setComponent(tmpl.getComponent());
        info.setRedirect(tmpl.getRedirect());
        info.setTarget(tmpl.getTarget());
        info.setVisible(tmpl.getVisible());
        info.setCheckState("1");

        info.setBranchNo(branchNo);
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(permitinfoService.AddNewRecord(appCode,info)>0){
            if(tmpl.getChildren()!=null&&tmpl.getChildren().size()>0){
                for(PermitTempl temp :tmpl.getChildren()){
                    loadPermit(appCode,branchNo,uuid,temp,permitNos);
                }
            }
            permitNos.add(uuid);
        }
    }
    private String initConfigInfo(String appCode,String branchNo,String siteName){

        configinfoService.saveConfigValueByKey(appCode,"SiteName",siteName,"Y");
        configinfoService.saveConfigValueByKey(appCode,"SiteUrl","http://","Y");
        configinfoService.saveConfigValueByKey(appCode,"AppCode",appCode,"Y");
        configinfoService.saveConfigValueByKey(appCode,"ConnStr","connString","Y");
        configinfoService.saveConfigValueByKey(appCode,"SiteDesc",siteName,"Y");
        configinfoService.saveConfigValueByKey(appCode,"RunState","1","Y");

        return "";
    }
    private String initMessageInfo(String appCode,String branchNo,String suserNo){

        SysMessageinfo info=new SysMessageinfo();
        String uuid=UuidUtils.shortUUID();
        info.setMssgNo(uuid);
        info.setMssgType("1");
        info.setMreceiver(suserNo);
        info.setMsender("system");
        info.setMtitle("欢迎使用热度空间");
        info.setMcontent("欢迎使用热度空间");
        info.setAttachfile("");
        info.setReadState("0");
        info.setReceiveTime(DateTimeUtils.getNowDate());
        info.setSendTime(DateTimeUtils.getNowDate());
        info.setCheckState("1");

        info.setBranchNo(branchNo);
        info.setCreateBy("");
        info.setUpdateBy("");
        info.setDeleteFlag("1");
        info.setComments("");
        info.setAppCode(appCode);

        if(messageinfoService.AddNewRecord(appCode,info)>0){
            return uuid;
        }
        return "";
    }

}
