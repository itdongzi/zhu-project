package com.benet.system.controller;

import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.domain.SysMenusinfo;
import com.benet.system.service.ISysMenusinfoService;
import com.benet.system.vmodel.MenuInfoVo;
import com.benet.system.vmodel.MenuMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoxking
 * @date 1/4/22.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/menusinfo")
public class MenusInfoController extends BaseController {

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private final ISysMenusinfoService menusinfoService;

    @GetMapping("/getNavmenuList")
    public AjaxResult getNavmenuList() {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMenusinfo> infoList = menusinfoService.getRecordsByUserType(loginUser.getAppCode(),"0", loginUser.getUserType());

        List<MenuInfoVo> menuList=new ArrayList<>();
        MenuInfoVo menuInfo=null;

        if(infoList!=null&&infoList.size()>0){
            for(SysMenusinfo info:infoList){
                menuInfo = new MenuInfoVo();
                menuInfo.setName(info.getMenuName());
                menuInfo.setCode(info.getMenuCode());
                menuInfo.setPath(info.getPathUrl());
                menuInfo.setComponent(info.getMenuName());
                menuInfo.setMeta(new MenuMeta(info.getMenuName(), "","", true));
                menuInfo.setChildren(null);

                menuList.add(menuInfo);
            }
        }

        return AjaxResult.success(menuList);
    }

    @GetMapping("/getMenuList")
    public AjaxResult getMenuList() {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<MenuInfoVo> data = buildMenuTree(loginUser.getAppCode(),"0",loginUser.getUserType());
        return AjaxResult.success(data);
    }

    private List<MenuInfoVo> buildMenuTree(String appCode, String parentNo,String userType) {

        List<MenuInfoVo> itemTree = null;
        MenuInfoVo item = null;
        MenuMeta meta=null;
        List<SysMenusinfo> infoList = menusinfoService.getRecordsByUserType(appCode, parentNo, userType);

        if (infoList != null && infoList.size() > 0) {
            itemTree = new ArrayList<>();
            for (SysMenusinfo info : infoList) {
                meta = new MenuMeta(info.getMenuName(), info.getMenuName(), info.getMenuIcon(), false);
                item = new MenuInfoVo();
                item.setMid(info.getMenuNo());
                item.setName(info.getMenuCode());
                item.setCode(info.getMenuCode());
                item.setPath(info.getPathUrl());
                item.setHidden(false);
                item.setRedirect("noRedirect");
                item.setAlwaysShow(true);
                item.setComponent(info.getComponent());
                item.setMeta(meta);
                item.setParentId(info.getParentNo());
                item.setChildren(buildMenuTree(appCode,info.getMenuNo(),userType));
                if(item.getChildren()==null){
                    item.setAlwaysShow(false);
                }

                itemTree.add(item);
            }
        }
        return itemTree;
    }
}
