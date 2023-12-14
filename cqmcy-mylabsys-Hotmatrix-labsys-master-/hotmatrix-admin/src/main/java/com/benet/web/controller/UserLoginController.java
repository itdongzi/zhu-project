package com.benet.web.controller;

import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.security.service.SUserLoginService;
import com.benet.framework.utils.RouterUtils;
import com.benet.framework.web.vmodel.RouterVo;
import com.benet.system.service.*;
import com.benet.system.vmodel.RenterInfoVo;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.domain.SysRenterinfo;
import com.benet.system.domain.SysSuserinfo;
import com.benet.web.vmodel.LoginInfoVo;
import com.benet.web.vmodel.SuserInfoVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author yoxking
 */
@Api(value = "web", tags = "登录验证控制器")
@RestController
@RequestMapping("/web")
public class UserLoginController
{
    @Autowired
    private SUserLoginService loginService;

    @Autowired
    private ISysSuserinfoService userinfoService;
    @Autowired
    private ISysRenterinfoService renterinfoService;

    @Autowired
    private ISysRoleinfoService roleService;

    @Autowired
    private ISysPermitinfoService permitService;

    @Autowired
    private MyJwtokenService tokenService;

    /**
     * 登录方法
     * 
     * @param loginInfo 用户登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginInfoVo loginInfo)
    {
        try {
            AjaxResult ajax = AjaxResult.success();

            // 生成令牌
            String token = loginService.login(loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getTextcode(), loginInfo.getUuid());
            ajax.put(JwtConstants.TOKEN, token);
            return ajax;
        }
        catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }
    /**
     * 获取所有租户信息列表
     */
    @GetMapping(value = "/getRenters")
    public AjaxResult getRenters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysRenterinfo> list = renterinfoService.getAllRecords(loginUser.getAppCode());

        if(list!=null&&list.size()>0){
            List<RenterInfoVo> renterList=new ArrayList<>();
            RenterInfoVo renter=null;

            for(SysRenterinfo info:list){
                renter=new RenterInfoVo();
                renter.setId(info.getRentNo());
                renter.setKey(info.getAppCode());
                renter.setTitle(info.getRcnname());
                renter.setValue(info.getAppCode());

                renterList.add(renter);
            }
            return AjaxResult.success(renterList);
        }
        return AjaxResult.success(null);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysSuserinfo info = userinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());

        SuserInfoVo userInfo=new SuserInfoVo();
        userInfo.setUserNo(info.getUserNo());
        userInfo.setUserName(info.getLoginName());
        userInfo.setNickname(info.getUserCnname());
        userInfo.setUserType(info.getUserType().equals("1")?"管理员":"用户");
        userInfo.setSex(info.getSex().equals("1")?"男":"女");
        userInfo.setOrgzName(info.getOrgzNo());
        userInfo.setPostName(info.getPostNo());
        userInfo.setEmail(info.getEmail());
        userInfo.setPhone(info.getTelephone());
        userInfo.setAvatar(info.getAvatar());
        userInfo.setLoginDate(DateTimeUtils.formatTime(info.getLoginDate(),"MM-dd HH:mm"));
        userInfo.setResume(info.getComments());

        // 角色集合
        Set<String> roles = roleService.getRoleCodesByUserNo(loginUser.getAppCode(),loginUser.getUserNo());
        // 权限集合
        Set<String> permits = permitService.getPermitCodesByUserNo(loginUser.getAppCode(),loginUser.getUserNo());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userInfo);
        ajax.put("roles", roles);
        ajax.put("permits", permits);
        return ajax;
    }


    /**
     * 按父菜单ID获取路由信息
     *
     * @param parentId 父菜单ID
     * @return 路由信息
     */
    @GetMapping("/getRouters/{parentId}")
    public AjaxResult getRouters(@PathVariable("parentId") String parentId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysPermitinfo> permitTree = permitService.getPermitTreeByUserNo(loginUser.getAppCode(),parentId,loginUser.getUserNo());
        return AjaxResult.success(RouterUtils.buildMenus(permitTree));
    }

    /**
     * 获取顶部菜单信息
     *
     * @return 路由信息
     */
    @GetMapping("/getTopMenu")
    public AjaxResult getTopMenu()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysPermitinfo> menus = permitService.getChildrenByUserNo(loginUser.getAppCode(),"0",loginUser.getUserNo());
        List<RouterVo> v=RouterUtils.buildMenus(menus);
        return AjaxResult.success(v);
    }
}
