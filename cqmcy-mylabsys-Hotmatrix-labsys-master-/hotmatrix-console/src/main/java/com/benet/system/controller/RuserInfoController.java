package com.benet.system.controller;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.system.constant.RuserType;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PagerRequest;
import com.benet.common.core.pager.TableResponse;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.core.controller.BaseController;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.utils.SecurityUtils;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysRuserinfoService;
import com.benet.system.vmodel.PswdModelVo;
import com.benet.web.vmodel.RuserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yoxking
 * @date 1/4/22.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/ruserinfo")
public class RuserInfoController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private final ISysRuserinfoService ruserinfoService;



    @PostMapping("/list")
    public AjaxResult list(@RequestBody PageRequest pagerRequest) {

        String condition = "";
        Map maps = pagerRequest.getDataParams();
        if(maps.containsKey("roomName")&& StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = ruserinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRuserinfo> list = ruserinfoService.getRecordsByPaging(loginUser.getAppCode(),pagerRequest.getPageIndex(), pagerRequest.getPageSize(), condition, "id", "Asc");

        TableResponse tableResponse=new TableResponse(list,count);
        return AjaxResult.success(tableResponse);
    }

    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody SysRuserinfo info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setUserNo(UuidUtils.shortUUID());
            info.setPassword(SecurityUtils.encryptPassword("123"));//初始密码
            info.setCreateBy(loginUser.getUserNo());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(ruserinfoService.AddNewRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysRuserinfo info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(ruserinfoService.UpdateRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/save")
    public AjaxResult save(@RequestBody SysRuserinfo info) {

        if (info == null) {
            return AjaxResult.error("操作出错");
        } else {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

            if (info.getUserNo().equals("0")) {
                info.setUserNo(UuidUtils.shortUUID());
                info.setCreateBy(loginUser.getUserNo());
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(ruserinfoService.AddNewRecord(loginUser.getAppCode(), info));
            } else {
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(ruserinfoService.UpdateRecord(loginUser.getAppCode(), info));
            }
        }
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(ruserinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRuserinfo recordByNo = ruserinfoService.getRecordByNo(loginUser.getAppCode(), id);

        return AjaxResult.success(recordByNo);
    }

    @PostMapping("/export")
    public AjaxResult export(@RequestBody PageRequest pagerRequest) {

        String condition = "";
        Map maps = pagerRequest.getDataParams();
        if(maps.containsKey("roomName")&& StringUtils.isNotEmpty(maps.get("roomName").toString())) {
            condition += "And room_name like '%" + maps.get("roomName")+"%'";
        }
        if(StringUtils.isNotEmpty(condition)){
            condition=condition.substring(3);
        }

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = ruserinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysRuserinfo> list = ruserinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysRuserinfo> util = new ExcelUtils<SysRuserinfo>(SysRuserinfo.class);
        return util.exportExcel(list, "SysRuserinfo");
    }

    @GetMapping("/listExperts")
    public AjaxResult listExperts() {

        String condition= "user_type='"+ RuserType.EXPERT +"'";
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        int count = ruserinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysRuserinfo> list =ruserinfoService.getRecordsByPaging(loginUser.getAppCode(),1,10,condition,"id","asc");

        TableResponse tableResponse=new TableResponse(list,count);
        return AjaxResult.success(tableResponse);
    }

    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(@RequestBody PswdModelVo psword) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (!SecurityUtils.matchesPassword(psword.getOldPsword(), loginUser.getPassword())) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(psword.getNewPsword(), loginUser.getPassword())) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }

        if (ruserinfoService.resetUserPassword(loginUser.getUserNo(),SecurityUtils.encryptPassword(psword.getNewPsword())) > 0) {
            // 更新缓存用户密码
            loginUser.setPassword(SecurityUtils.encryptPassword(psword.getNewPsword()));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.success();
    }

    @GetMapping(value = "/getUserProfile")
    public AjaxResult getUserProfile() {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRuserinfo info = ruserinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());

        RuserInfoVo userInfo=new RuserInfoVo();
        userInfo.setUserNo(info.getUserNo());
        userInfo.setUserName(info.getLoginName());
        userInfo.setNickname(info.getUserCnname());
        userInfo.setUserType(info.getUserType());
        userInfo.setSex(info.getSex().equals("1")?"男":"女");
        userInfo.setOrgzName(info.getOrgzNo());
        userInfo.setPostName(info.getPostNo());
        userInfo.setEmail(info.getEmail());
        userInfo.setPhone(info.getTelephone());
        userInfo.setAvatar(info.getAvatar());
        userInfo.setLoginDate(DateTimeUtils.formatTime(info.getLoginDate(),"MM-dd HH:mm"));
        userInfo.setResume(info.getComments());
        userInfo.setRoles(new ArrayList<String>());
        userInfo.setPermissions(new ArrayList<String>());

        return AjaxResult.success(userInfo);
    }

    @PostMapping(value = "/uploadAvatar")
    public AjaxResult uploadAvatar() {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRuserinfo info = ruserinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());

        return AjaxResult.success(info);
    }

    @PostMapping(value = "/updateProfile")
    public AjaxResult updateProfile() {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysRuserinfo info = ruserinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());

        return AjaxResult.success(info);
    }
}
