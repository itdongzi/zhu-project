package com.benet.system.controller;

import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.core.domain.TreeModel;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.PagerRequest;
import com.benet.common.core.pager.TableResponse;
import com.benet.common.utils.poi.ExcelUtils;
import com.benet.common.utils.string.StringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysConteeclass;
import com.benet.system.service.ISysConteeclassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yoxking
 * @date 2022-3-12.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/conteeclass")
public class ConteeclassController  extends BaseController {


    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private final ISysConteeclassService conteeclassService;

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
        int count = conteeclassService.getCountByCondition(loginUser.getAppCode(),condition);
        List<SysConteeclass> list = conteeclassService.getRecordsByPaging(loginUser.getAppCode(),pagerRequest.getPageIndex(), pagerRequest.getPageSize(), condition, "id", "Asc");

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
        List<SysConteeclass> infoList = conteeclassService.getRecordsByClassNo(appCode,parentNo);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysConteeclass info : infoList) {
                item = new TreeModel();
                item.setLabel(info.getClassName());
                item.setValue(info.getClassNo());
                item.setParentId(parentNo);
                item.setLeaf(false);
                item.setDisabled(false);
                List<TreeModel> temp=buildItemTree(appCode,info.getClassNo());
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
    public AjaxResult insert(@RequestBody SysConteeclass info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setClassNo(UuidUtils.shortUUID());
            info.setCreateBy(loginUser.getUserNo());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(conteeclassService.AddNewRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysConteeclass info) {

        if (info!=null) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            info.setUpdateBy(loginUser.getUserNo());
            return AjaxResult.success(conteeclassService.UpdateRecord(loginUser.getAppCode(),info));
        }else{
            return AjaxResult.error("操作出错");
        }
    }

    @PostMapping("/save")
    public AjaxResult save(@RequestBody SysConteeclass info) {

        if (info == null) {
            return AjaxResult.error("操作出错");
        } else {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

            if (info.getClassNo().equals("0")) {
                info.setClassNo(UuidUtils.shortUUID());
                info.setCreateBy(loginUser.getUserNo());
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(conteeclassService.AddNewRecord(loginUser.getAppCode(), info));
            } else {
                info.setUpdateBy(loginUser.getUserNo());
                return AjaxResult.success(conteeclassService.UpdateRecord(loginUser.getAppCode(), info));
            }
        }
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable("ids") String[] ids) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(conteeclassService.SoftDeleteByNos(loginUser.getAppCode(),ids));
    }

    @GetMapping(value = "/{id}")
    public AjaxResult detail(@PathVariable("id") String id) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(conteeclassService.getRecordByNo(loginUser.getAppCode(),id));
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
        int count = conteeclassService.getCountByCondition(loginUser.getAppCode(),condition);

        List<SysConteeclass> list = conteeclassService.getRecordsByPaging(loginUser.getAppCode(),1,count,condition,"id","Asc");
        ExcelUtils<SysConteeclass> util = new ExcelUtils<SysConteeclass>(SysConteeclass.class);
        return util.exportExcel(list, "SysConteeclass");
    }
}