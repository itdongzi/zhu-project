package com.benet.web.controller;

import com.benet.common.constant.JwtConstants;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.framework.security.service.RUserLoginService;
import com.benet.framework.utils.RouterUtils;
import com.benet.system.domain.SysMenusinfo;
import com.benet.system.domain.SysPermitinfo;
import com.benet.system.domain.SysRuserinfo;
import com.benet.system.service.ISysDepartmentService;
import com.benet.system.service.ISysOrganizinfoService;
import com.benet.system.service.ISysRuserinfoService;
import com.benet.system.vmodel.MenuInfoVo;
import com.benet.system.vmodel.MenuMeta;
import com.benet.web.constant.UserType;
import com.benet.web.vmodel.LoginInfoVo;
import com.benet.web.vmodel.RuserInfoVo;
import com.benet.web.vmodel.TokenInfoVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private RUserLoginService loginService;


    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysRuserinfoService userinfoService;

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
            // 生成令牌
            String token = loginService.login(loginInfo.getUsername(), loginInfo.getPassword(), loginInfo.getTextcode(), loginInfo.getUuid());
            return AjaxResult.success(new TokenInfoVo(token));
        }
        catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
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
        SysRuserinfo info = userinfoService.getRecordByNo(loginUser.getAppCode(),loginUser.getUserNo());

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
}
