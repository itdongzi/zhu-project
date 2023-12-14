package com.benet.report.controller;

import com.benet.common.core.controller.BaseController;
import com.benet.common.core.pager.PageRequest;
import com.benet.common.core.pager.TableDataInfo;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.security.LoginUser;
import com.benet.framework.security.service.MyJwtokenService;
import com.benet.system.service.ISysLogininfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登录报表Controller
 *
 * @author yoxking
 * @date 2020-04-20
 */
@Api(value = "report/logindata", tags = "户登录报表控制器")
@RestController
@RequestMapping("/report/logindata")
public class RptLoginDataController extends BaseController
{

    @Autowired
    private MyJwtokenService tokenService;

    @Autowired
    private ISysLogininfoService logininfoService;

    /**
     * 首页
     */
    @PreAuthorize("@ps.hasPermit('report:logindata:index')")
    @GetMapping(value="/index")
    public ModelAndView index()
    {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }


    /**
     * 查询分支信息列表
     */
    //@PreAuthorize("@ps.hasPermit('report:logindata:list')")
    @PostMapping(value = "/list")
    public TableDataInfo list(@RequestBody PageRequest pRequest)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return getDataTable(null, 0);
    }
}
