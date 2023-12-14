package com.benet.system.controller;

import com.alibaba.fastjson.JSON;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.domain.TreeModel;
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
import com.benet.system.domain.SysOrganizinfo;
import com.benet.system.service.ISysOrganizinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yoxking
 * @date 2022-5-12.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/organizinfo")
public class OrganizInfoController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private final ISysOrganizinfoService organizinfoService;

    @PostMapping(value = "/list")
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
        int count = organizinfoService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysOrganizinfo> list = organizinfoService.getRecordsByPaging(loginUser.getAppCode(),pagerRequest.getPageIndex(), pagerRequest.getPageSize(), condition, "id", "Asc");

        TableResponse tableResponse=new TableResponse(list,count);
        return AjaxResult.success(tableResponse);
    }

    @GetMapping(value = "/tree")
    public AjaxResult tree()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<TreeModel> data = buildItemTree(loginUser.getAppCode(),"0");
        return AjaxResult.success(data);
    }

    private List<TreeModel> buildItemTree(String appCode, String parentNo) {

        List<TreeModel> itemTree = null;
        TreeModel item = null;
        List<SysOrganizinfo> infoList = organizinfoService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysOrganizinfo info : infoList) {
                item = new TreeModel();
                item.setLabel(info.getOrganizName());
                item.setValue(info.getOrganizNo());
                item.setParentId(parentNo);
                item.setLeaf(false);
                item.setDisabled(false);
                List<TreeModel> temp=buildItemTree(appCode,info.getOrganizNo());
                item.setChildren(temp);
                if(temp==null){
                    item.setLeaf(true);
                }

                itemTree.add(item);
            }
        }
        return itemTree;
    }

    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody SysOrganizinfo info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setOrganizNo(UuidUtils.shortUUID());
            info.setCreateBy(loginUser.getUserNo());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(organizinfoService.AddNewRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysOrganizinfo info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(organizinfoService.UpdateRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/save")
    public AjaxResult save(@RequestBody SysOrganizinfo info) {

        if (info == null) {
            return AjaxResult.error("操作出错");
        } else {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

            if (info.getOrganizNo().equals("0")) {
                info.setOrganizNo(UuidUtils.shortUUID());
                info.setCreateBy(loginUser.getUserNo());
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(organizinfoService.AddNewRecord(loginUser.getAppCode(), info));
            } else {
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(organizinfoService.UpdateRecord(loginUser.getAppCode(), info));
            }
        }
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(organizinfoService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(organizinfoService.getRecordByNo(loginUser.getAppCode(),id));
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
        int count = organizinfoService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysOrganizinfo> list = organizinfoService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysOrganizinfo> util = new ExcelUtils<SysOrganizinfo>(SysOrganizinfo.class);
        return util.exportExcel(list, "SysOrganizinfo");
    }
}
