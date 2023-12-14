package com.benet.web.controller;

import com.benet.common.annotation.Oplog;
import com.benet.common.core.domain.AjaxResult;
import com.benet.common.enums.BusinessType;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.service.ISysConfiginfoService;
import com.benet.web.vmodel.ConfigInfoVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配置请求处理
 *
 * @author yoxking
 */
@Api(value = "web", tags = "配置请求处理控制器")
@RestController
@RequestMapping("/web")
public class ConfigInfoController {

    @Autowired
    private ISysConfiginfoService sysConfiginfoService;

    @Autowired
    private MyJwtokenService tokenService;

    /**
     * 配置请求
     *
     */
    @GetMapping("/configInfo")
    public AjaxResult getConfigInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        ConfigInfoVo info=new ConfigInfoVo();
        info.setSiteName(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteName"));
        info.setSiteUrl(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteUrl"));
        info.setAppCode(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"AppCode"));
        info.setConnStr(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"ConnStr"));
        info.setSiteDesc(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"SiteDesc"));
        info.setRunState(sysConfiginfoService.getConfigValueByKey(loginUser.getAppCode(),"RunState"));

        return AjaxResult.success(info);
    }

    /**
     * 保存配置信息
     */
    @Oplog(title = "保存配置信息", businessType = BusinessType.SAVE)
    @PostMapping("/saveConfigInfo")
    public AjaxResult saveConfigInfo(@RequestBody ConfigInfoVo configInfo) {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"SiteName",configInfo.getSiteName(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"SiteUrl",configInfo.getSiteUrl(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"AppCode",configInfo.getAppCode(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"ConnStr",configInfo.getConnStr(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"SiteDesc",configInfo.getSiteDesc(),"Y");
        sysConfiginfoService.saveConfigValueByKey(loginUser.getAppCode(),"RunState",configInfo.getRunState(),"Y");

        return  AjaxResult.success();
    }
}
