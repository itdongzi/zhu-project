package com.benet.system.controller;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.PagerRequest;
import com.benet.common.core.pager.TableResponse;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.core.controller.BaseController;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysMessageinfo;
import com.benet.system.service.ISysMessageinfoService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yoxking
 * @date 2022-3-12.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/messageinfo")
public class MessageInfoController extends BaseController {


    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private final ISysMessageinfoService messageinfoService;

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
        int count = messageinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysMessageinfo> list = messageinfoService.getRecordsByPaging(loginUser.getAppCode(),pagerRequest.getPageIndex(), pagerRequest.getPageSize(), condition, "id", "Asc");

        TableResponse tableResponse=new TableResponse(list,count);
        return AjaxResult.success(tableResponse);
    }

    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody SysMessageinfo info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setMssgNo(UuidUtils.shortUUID());
            info.setCreateBy(loginUser.getUserNo());
//            System.out.println(info.getSendTime());测试前端代码传入后台时提交的时间格式
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(messageinfoService.AddNewRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysMessageinfo info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(messageinfoService.UpdateRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/save")
    public AjaxResult save(@RequestBody SysMessageinfo info) {

        if (info == null) {
            return AjaxResult.error("操作出错");
        } else {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

            if (info.getMssgNo().equals("0")) {
                info.setMssgNo(UuidUtils.shortUUID());
                info.setCreateBy(loginUser.getUserNo());
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(messageinfoService.AddNewRecord(loginUser.getAppCode(), info));
            } else {
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(messageinfoService.UpdateRecord(loginUser.getAppCode(), info));
            }
        }
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(messageinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(messageinfoService.getRecordByNo(loginUser.getAppCode(),id));
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
        int count = messageinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysMessageinfo> list = messageinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysMessageinfo> util = new ExcelUtils<SysMessageinfo>(SysMessageinfo.class);
        return util.exportExcel(list, "SysMessageinfo");
    }
}