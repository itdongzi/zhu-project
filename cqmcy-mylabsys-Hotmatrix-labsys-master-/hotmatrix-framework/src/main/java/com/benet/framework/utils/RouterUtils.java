package com.benet.framework.utils;

import com.benet.framework.web.vmodel.RouterVo;
import com.benet.system.domain.SysPermitinfo;

import java.util.LinkedList;
import java.util.List;

public class RouterUtils {

    /**
     * 构建前端路由所需要的菜单
     *
     * @param permitList 菜单列表
     * @return 路由列表
     */
    public static List<RouterVo> buildMenus(List<SysPermitinfo> permitList)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysPermitinfo permit : permitList)
        {
            RouterVo router = new RouterVo();
            router.setNo(permit.getPermitNo());
            router.setName(permit.getPermitName());
            router.setTitle(permit.getComments());
            router.setPath(permit.getPathUrl());
            router.setIcon(permit.getMenuIcon());
            router.setInvisible("0".equals(permit.getVisible()));
            router.setRedirect(("noredirect").equals(permit.getRedirect())?null:permit.getRedirect());
            router.setComponent(permit.getComponent());
            router.setChildren(null);

            List<SysPermitinfo> childMenus = permit.getChildren();
            if (childMenus!=null&&!childMenus.isEmpty() && childMenus.size() > 0 && "M".equals(permit.getPermitType()))
            {
                router.setChildren(buildMenus(childMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param permit 菜单信息
     * @return 路由地址
     */
    private static String getRouterPath(SysPermitinfo permit)
    {
        String routerPath = permit.getPathUrl();
        // 非外链并且是一级目录
        if ("0".equals(permit.getParentNo()) && "0".equals(permit.getLinkType()))
        {
            routerPath = "/" + permit.getPathUrl();
        }
        return routerPath;
    }
}
