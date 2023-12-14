package com.benet.framework.manager.factory;

import java.util.TimerTask;

import com.benet.common.configure.GlobalConfig;
import com.benet.common.constant.PubConstants;
import com.benet.common.utils.date.DateTimeUtils;
import com.benet.common.utils.net.AddressUtils;
import com.benet.common.utils.net.IpnetUtils;
import com.benet.common.utils.spring.SpringUtils;
import com.benet.common.utils.uuid.UuidUtils;
import com.benet.common.utils.web.ServletUtils;
import com.benet.framework.utils.OplogUtils;
import com.benet.system.domain.SysLogininfo;
import com.benet.system.domain.SysOperatelog;
import com.benet.system.service.ISysLogininfoService;
import com.benet.system.service.ISysOperatelogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 异步工厂（产生任务用）
 * 
 * @author yoxking
 *
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 操作日志记录
     * 
     * @param opertLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperate(final SysOperatelog opertLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                opertLog.setOplogNo(UuidUtils.shortUUID());
                opertLog.setOpertLocation(AddressUtils.getRealAddressByIP(opertLog.getOpertIp()));
                SpringUtils.getBean(ISysOperatelogService.class).AddNewRecord(GlobalConfig.getAppCode(),opertLog);
            }
        };
    }

    /**
     * 记录登陆信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfo(final String username, final String status, final String message, final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpnetUtils.getHostIp();
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(OplogUtils.getBlock(ip));
                s.append(address);
                s.append(OplogUtils.getBlock(username));
                s.append(OplogUtils.getBlock(status));
                s.append(OplogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();

                // 封装对象
                SysLogininfo logininfo = new SysLogininfo();
                logininfo.setLoginNo(UuidUtils.shortUUID());
                logininfo.setLoginName(username);
                logininfo.setIpAddress(ip);
                logininfo.setLocation(address);
                logininfo.setBrowser(browser);
                logininfo.setOs(os);
                logininfo.setMessage(message);
                // 日志状态
                if (PubConstants.LOGIN_SUCCESS.equals(status) || PubConstants.LOGOUT.equals(status))
                {
                    logininfo.setStatus(PubConstants.SUCCESS);
                }
                else if (PubConstants.LOGIN_FAIL.equals(status))
                {
                    logininfo.setStatus(PubConstants.FAIL);
                }
                logininfo.setLoginTime(DateTimeUtils.getNowDate());
                logininfo.setBranchNo(GlobalConfig.getBranchNo());

                // 插入数据
                SpringUtils.getBean(ISysLogininfoService.class).AddNewRecord(GlobalConfig.getAppCode(),logininfo);
            }
        };
    }
}
