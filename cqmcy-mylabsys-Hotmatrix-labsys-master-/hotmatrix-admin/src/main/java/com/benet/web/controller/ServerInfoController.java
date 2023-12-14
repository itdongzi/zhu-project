package com.benet.web.controller;

import com.benet.common.core.controller.BaseController;
import com.benet.common.core.domain.AjaxResult;
import com.benet.framework.web.domain.Server;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author yoxking
 */
@Api(value = "web", tags = "服务器监控制器")
@RestController
@RequestMapping("/web")
public class ServerInfoController extends BaseController
{
    //@PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping("/getServerInfo")
    public AjaxResult getServerInfo() throws Exception
    {
        try {
            Server server = new Server();
            server.copyTo();

            return AjaxResult.success(server);
        }
        catch (Exception e){
            return AjaxResult.error();
        }
    }
}


